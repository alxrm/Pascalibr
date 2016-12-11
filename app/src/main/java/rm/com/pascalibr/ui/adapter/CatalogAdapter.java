package rm.com.pascalibr.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.CatalogEntry;
import rm.com.pascalibr.ui.holder.BaseHolder;
import rm.com.pascalibr.ui.holder.CatalogEntryHolder;

/**
 * класс, соединяющий данные из списка статей с элементами интерфейса
 */
public final class CatalogAdapter extends BaseAdapter<CatalogEntry, CatalogEntryHolder> {

  private BaseHolder.OnClickListener<CatalogEntry> clickListener;

  /**
   * метод, где создаётся пустой элемент списка статей, который позже будет просто привязан к
   * модели
   *
   * @param parent сам список выступает родительским элементом
   * @param viewType тип элемента списка в списке статей тип всегда один, поэтому здесь этот параметр не нужен
   * @return возвращает пустой элемент интерфейса списка статей
   */
  @Override public CatalogEntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CatalogEntryHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false));
  }

  /**
   * метод привязки данных к представлению, дополнительно здесь ещё устанавливается слушатель нажатий на элемент списка, чтобы можно было открыть страницу с нужной статьёй
   *
   * @param holder элемент интерфейса, часть списка
   * @param position позиция в списке, по которой нужно брать модель
   */
  @Override public void onBindViewHolder(CatalogEntryHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    holder.setOnClickListener(clickListener);
  }

  /**
   * метод установки слушателя нажатий на элементы списка
   *
   * @param clickListener экземпляр класса, реализующего интерфейс слушателя нажатий
   */
  public final void setOnClickListener(BaseHolder.OnClickListener<CatalogEntry> clickListener) {
    this.clickListener = clickListener;
  }
}
