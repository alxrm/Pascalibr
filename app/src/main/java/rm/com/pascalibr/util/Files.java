package rm.com.pascalibr.util;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * класс с утилитами для чтения файлов
 */
public final class Files {

  private Files() {
  }

  /**
   * метод для чтения файла из данных приложения
   *
   * @param assets доступ к файлам приложения
   * @param path путь к файлу
   * @return возвращает содержимое файла в виде строки
   */
  @Nullable public static String readText(@NonNull AssetManager assets, @NonNull String path) {
    Conditions.checkNotNull(assets, "Assets are null");
    Conditions.checkNotNull(path, "Path is null");

    try {
      return readText(assets.open(path));
    } catch (IOException e) {
      Logger.e(e);
      return null;
    }
  }

  /**
   * метод для чтения данных из входного потока
   *
   * @param stream поток данных
   * @return возвращает данные из потока в виде строки, строка может быть {@code null}
   */
  @Nullable public static String readText(@NonNull InputStream stream) {
    try {
      return streamToString(stream);
    } catch (IOException e) {
      Logger.e(e);
    } finally {
      try {
        stream.close();
      } catch (IOException e) {
        Logger.e(e);
      }
    }

    return null;
  }

  /**
   * метод для чтения данных из входного потока,
   * в отличии от предыдущего, в этом методе нет обработки ошибок,
   * поэтому он может выбросить исключение
   *
   * @param stream поток данных
   * @return возвращает данные из потока в виде строки, строка точно не является {@code null}
   * @throws IOException в случае, если что-то пошло не так при чтении файла,
   * может быть выброшено исключение, метод об этом уведомляет вызывающую сторону,
   * требуя, чтобы исключение было обработано
   */
  @NonNull public static String streamToString(@NonNull InputStream stream) throws IOException {
    Conditions.checkNotNull(stream, "Stream cannot be null");

    final StringBuilder builder = new StringBuilder();
    final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

    String line;

    while ((line = reader.readLine()) != null) builder.append(line);

    return builder.toString();
  }
}
