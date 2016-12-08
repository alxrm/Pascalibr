package rm.com.pascalibr.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rm.com.pascalibr.R;

/**
 * Created by alex
 */
public abstract class BaseContentFragment extends Fragment {

  @BindView(R.id.content_loader) ProgressBar loader;
  @BindView(R.id.content_list) RecyclerView content;

  protected Unbinder unbinder;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    final Bundle args = getArguments();

    if (args != null) {
      unwrapArguments(args);
    }
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onResume() {
    super.onResume();
    updateAppbar();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  @NonNull abstract String getTitle();

  abstract boolean hasBackButton();

  protected void unwrapArguments(@NonNull Bundle args) {
  }

  protected void onBackPressed() {
    final MainActivity owner = getOwner();

    if (owner != null) owner.onBackPressed();
  }

  @Nullable protected final MainActivity getOwner() {
    return (MainActivity) getActivity();
  }

  protected final void toggleContent(boolean show) {
    content.setVisibility(show ? View.VISIBLE : View.GONE);
    loader.setVisibility(show ? View.GONE : View.VISIBLE);
  }

  protected final void navigateTo(@NonNull BaseContentFragment fragment) {
    final MainActivity owner = getOwner();

    if (owner != null) owner.navigateTo(fragment);
  }

  final void updateAppbar() {
    final MainActivity owner = getOwner();
    final ActionBar supportBar = owner != null ? owner.getSupportActionBar() : null;

    if (supportBar != null) {
      supportBar.setTitle(getTitle());
      supportBar.setDisplayHomeAsUpEnabled(hasBackButton());
    }
  }
}