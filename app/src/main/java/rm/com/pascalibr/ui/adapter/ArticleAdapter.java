package rm.com.pascalibr.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import rm.com.pascalibr.model.DescriptionEntry;
import rm.com.pascalibr.ui.holder.CodeEntryHolder;
import rm.com.pascalibr.ui.holder.DescriptionEntryHolder;
import rm.com.pascalibr.ui.holder.SubtitleEntryHolder;
import rm.com.pascalibr.ui.holder.TextEntryHolder;
import rm.com.pascalibr.util.Converters;

import static rm.com.pascalibr.util.Constants.ENTRY_CODE_KEY;
import static rm.com.pascalibr.util.Constants.ENTRY_SUBTITLE_KEY;
import static rm.com.pascalibr.util.Constants.ENTRY_TEXT_KEY;

/**
 * класс, соединяющий данные из статьи(описание части языка) с элементами интерфейса
 */
public final class ArticleAdapter extends BaseAdapter<DescriptionEntry, DescriptionEntryHolder> {

  /**
   * метод для различия элементов списка по позиции
   *
   * в статье может содержаться 3 вида элементов списка: Подзаголовки, Текст Описания и Код
   *
   * @param position позиция элемента, который нужно распознать
   * @return возвращается одна из числовых констант, представляющих тип элемента списка:
   * {@code Constants.ENTRY_TEXT_KEY}, {@code Constants.ENTRY_SUBTITLE_KEY} и
   * {@code Constants.ENTRY_CODE_KEY}
   */
  @Override public int getItemViewType(int position) {
    return Converters.entryKeyOf(data.get(position).type);
  }

  /**
   * метод, где создаётся пустой элемент списка нужного типа, который позже будет просто привязан к
   * модели
   *
   * @param parent сам список выступает родительским элементом
   * @param viewType тип элемента списка(одна из констант)
   * @return возвращает нужный элемент интерфейса статьи или выбрасывает исключение, если элемент
   * неизвестен
   */
  @Override public DescriptionEntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(Converters.holderLayoutOf(viewType), parent, false);

    switch (viewType) {
      case ENTRY_TEXT_KEY:
        return new TextEntryHolder(itemView);
      case ENTRY_SUBTITLE_KEY:
        return new SubtitleEntryHolder(itemView);
      case ENTRY_CODE_KEY:
        return new CodeEntryHolder(itemView);
      default:
        throw new IllegalStateException("Unknown entry type");
    }
  }
}
