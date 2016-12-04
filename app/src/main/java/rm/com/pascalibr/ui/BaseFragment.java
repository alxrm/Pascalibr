package rm.com.pascalibr.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by alex
 */
public abstract class BaseFragment extends Fragment {

  protected Unbinder unbinder;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  @Override public void onResume() {
    super.onResume();
    updateTitle(getTitle());
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  @NonNull abstract String getTitle();

  @Nullable protected final MainActivity getOwner() {
    return (MainActivity) getActivity();
  }

  protected final void navigateTo(@NonNull BaseFragment fragment) {
    final MainActivity owner = getOwner();

    if (owner != null) owner.navigateTo(fragment);
  }

  final void updateTitle(String title) {
    final MainActivity owner = getOwner();

    if (owner != null) owner.setTitle(title);
  }
}