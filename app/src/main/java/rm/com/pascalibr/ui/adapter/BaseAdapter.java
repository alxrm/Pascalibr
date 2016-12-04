package rm.com.pascalibr.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rm.com.pascalibr.ui.holder.BaseHolder;

/**
 * Created by alex
 */

abstract class BaseAdapter<M, VH extends BaseHolder<M>> extends RecyclerView.Adapter<VH> {

  protected List<M> data = new ArrayList<>();

  @Override public void onBindViewHolder(VH holder, int position) {
    if (holder != null) holder.bind(data.get(position));
  }

  @Override public int getItemCount() {
    return data.size();
  }

  public final void updateData(List<M> data) {
    this.data = data;
    notifyDataSetChanged();
  }
}
