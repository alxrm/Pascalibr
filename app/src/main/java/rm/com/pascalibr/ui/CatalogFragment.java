package rm.com.pascalibr.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindString;
import java.util.List;
import javax.inject.Inject;
import rm.com.pascalibr.PascalibrApplication;
import rm.com.pascalibr.R;
import rm.com.pascalibr.data.CatalogProvider;
import rm.com.pascalibr.data.ProviderListener;
import rm.com.pascalibr.model.CatalogEntry;
import rm.com.pascalibr.ui.adapter.CatalogAdapter;
import rm.com.pascalibr.ui.holder.BaseHolder;

/**
 * Created by alex
 */
public final class CatalogFragment extends BaseContentFragment
    implements ProviderListener<List<CatalogEntry>>, BaseHolder.OnClickListener<CatalogEntry>,
    MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener {

  @BindString(R.string.path_catalog) String providerSource;
  @BindString(R.string.app_name) String title;

  @Inject CatalogProvider provider;
  @Inject CatalogAdapter adapter;

  @NonNull public static CatalogFragment newInstance() {
    return new CatalogFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((PascalibrApplication) getActivity().getApplication()).injector().inject(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_content, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    toggleContent(false);

    adapter.setOnClickListener(this);
    content.setAdapter(adapter);
    provider.provide(providerSource, this);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_catalog, menu);
    super.onCreateOptionsMenu(menu, inflater);

    final MenuItem searchItem = menu.findItem(R.id.action_search);
    final SearchView searchView = (SearchView) searchItem.getActionView();

    MenuItemCompat.setOnActionExpandListener(searchItem, this);
    searchView.setOnQueryTextListener(this);
  }

  @Override public void onProvide(@NonNull List<CatalogEntry> payload) {
    toggleContent(true);
    adapter.updateData(payload);
  }

  @Override public void onItemClick(@NonNull CatalogEntry item) {
    navigateTo(ArticleFragment.newInstance(item.name, item.fileName));
  }

  @Override public boolean onMenuItemActionExpand(MenuItem item) {
    return true;
  }

  @Override public boolean onMenuItemActionCollapse(MenuItem item) {
    updateQuery("");
    return true;
  }

  @Override public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override public boolean onQueryTextChange(String newText) {
    updateQuery(newText);
    return true;
  }

  private void updateQuery(String nextQuery) {
    provider.find(nextQuery, this);
  }

  @NonNull @Override String getTitle() {
    return title;
  }

  @Override boolean hasBackButton() {
    return false;
  }
}
