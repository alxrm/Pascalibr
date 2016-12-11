package rm.com.pascalibr.ui.holder;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.CatalogEntry;
import rm.com.pascalibr.util.Converters;

/**
 * элемент списка статей
 */
public final class CatalogEntryHolder extends BaseHolder<CatalogEntry> {

  @BindView(R.id.item_catalog_name) TextView name;
  @BindView(R.id.item_catalog_type) TextView type;
  @BindView(R.id.item_catalog_icon) TextView icon;

  public CatalogEntryHolder(View itemView) {
    super(itemView);
  }

  /**
   * метод привязки статьи из списка, здесь же происходит подключение слушателя нажатий
   *
   * @param model данные, которые должны быть привязаны и отрисованы
   */
  @Override public void bind(@NonNull final CatalogEntry model) {
    name.setText(model.name);
    type.setText(model.type);

    icon.setText(Converters.lettersOf(model.type));
    icon.getBackground()
        .setColorFilter(
            new PorterDuffColorFilter(Converters.colorOf(model.type), PorterDuff.Mode.MULTIPLY));

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (clickListener != null) clickListener.onItemClick(model);
      }
    });
  }
}
