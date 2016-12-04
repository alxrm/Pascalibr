package rm.com.pascalibr.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by alex
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

  public BaseHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  abstract public void bind(@NonNull T model);
}
