package rm.com.pascalibr.util;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import rm.com.pascalibr.model.Article;
import rm.com.pascalibr.model.DescriptionEntry;

import static rm.com.pascalibr.util.Constants.ENTRY_KEYS;
import static rm.com.pascalibr.util.Constants.ENTRY_LAYOUTS;
import static rm.com.pascalibr.util.Constants.ENTRY_SUBTITLE;
import static rm.com.pascalibr.util.Constants.ENTRY_TEXT;
import static rm.com.pascalibr.util.Constants.TYPE_COLORS;

/**
 * класс с утилитами для преобразования данных
 */
public final class Converters {

  private Converters() {
  }

  /**
   * метод для получения букв в иконке элемента статьи по типу конструкции языка из справочника
   *
   * @param type тип конструкции языка
   * @return строку для иконки (например "Зарезервированное слово" -> "ЗС")
   */
  @NonNull public static String lettersOf(@NonNull String type) {
    final StringBuilder resultContainer = new StringBuilder();
    final String[] words = type.split(" ");
    final int lettersCount =
        Math.min(words.length, 2); // максимальное количество букв в иконке == 2

    for (int i = 0; i < lettersCount; i++) {
      resultContainer.append(words[i].toUpperCase().charAt(0));
    }

    return resultContainer.toString();
  }

  /**
   * метод для получения цвета иконки в элементе статьи по типу конструкции языка из справочника
   *
   * @param type тип конструкции языка
   * @return сам цвет, число в шестнадцатиричном формате
   */
  public static int colorOf(@NonNull String type) {
    return TYPE_COLORS.get(type);
  }

  /**
   * метод для получения константы для отрисовки по типу элемента статьи
   *
   * @param type тип элемента статьи
   * @return одна из констант
   */
  public static int entryKeyOf(@NonNull String type) {
    return ENTRY_KEYS.get(type);
  }

  /**
   * метод для получения вёрстки нужного элемента статьи по одной из специальных констант
   *
   * @param key одна из специальных констант для отрисовки
   * @return id файла с вёрсткой
   */
  public static int holderLayoutOf(int key) {
    return ENTRY_LAYOUTS.get(key);
  }

  /**
   * метод сжатия нескольких элементов статьи с текстом, в один
   *
   * так как иногда бывает, что в элементах статьи может быть несколько текстовых блоков подряд, их
   * нужно собрать в один, чтобы это выглядело нормально
   *
   * @param payload экземпляр статьи
   * @return возвращает обновлённый список элементов статьи, со сжатыми текстовыми блоками
   */
  @NonNull public static ArrayList<DescriptionEntry> descriptionSetOf(@NonNull Article payload) {
    final ArrayList<DescriptionEntry> result = new ArrayList<>();
    final int size = payload.description.size();
    final int lastIndex = size - 1;

    result.add(new DescriptionEntry(ENTRY_SUBTITLE, payload.title.titleText));

    int index = 0;

    while (index < size) {
      final DescriptionEntry curEntry = payload.description.get(index);
      final StringBuilder accumulator = new StringBuilder(curEntry.data);

      if (!curEntry.type.equals(ENTRY_TEXT) || index == lastIndex) {
        result.add(curEntry);
        index += 1;
        continue;
      }

      DescriptionEntry next;
      index += 1;

      while (index < size && (next = payload.description.get(index)).type.equals(ENTRY_TEXT)) {
        accumulator.append("\n\n").append(next.data);
        index += 1;
      }

      result.add(new DescriptionEntry(ENTRY_TEXT, accumulator.toString()));
    }

    return result;
  }
}
