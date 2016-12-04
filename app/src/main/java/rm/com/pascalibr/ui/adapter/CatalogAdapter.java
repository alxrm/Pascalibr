package rm.com.pascalibr.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.CatalogEntry;
import rm.com.pascalibr.ui.holder.CatalogEntryHolder;

/**
 * Created by alex
 */
public final class CatalogAdapter extends BaseAdapter<CatalogEntry, CatalogEntryHolder> {

  @Override public CatalogEntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CatalogEntryHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false));
  }
}
