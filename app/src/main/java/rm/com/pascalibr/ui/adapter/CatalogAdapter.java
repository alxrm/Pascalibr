package rm.com.pascalibr.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.CatalogEntry;
import rm.com.pascalibr.ui.holder.BaseHolder;
import rm.com.pascalibr.ui.holder.CatalogEntryHolder;

/**
 * Created by alex
 */
public final class CatalogAdapter extends BaseAdapter<CatalogEntry, CatalogEntryHolder> {

  private BaseHolder.OnClickListener<CatalogEntry> clickListener;

  @Override public CatalogEntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CatalogEntryHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false));
  }

  @Override public void onBindViewHolder(CatalogEntryHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    holder.setOnClickListener(clickListener);
  }

  public final void setOnClickListener(BaseHolder.OnClickListener<CatalogEntry> clickListener) {
    this.clickListener = clickListener;
  }
}
