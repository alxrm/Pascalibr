package rm.com.pascalibr.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindString;
import butterknife.BindView;
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
public final class CatalogFragment extends BaseFragment
    implements ProviderListener<List<CatalogEntry>>, BaseHolder.OnClickListener<CatalogEntry> {

  @BindView(R.id.catalog_loader) ProgressBar loader;
  @BindView(R.id.catalog_list) RecyclerView catalog;
  @BindString(R.string.path_catalog) String providerSource;

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
    return inflater.inflate(R.layout.fragment_catalog, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter.setOnClickListener(this);
    catalog.setAdapter(adapter);
    provider.retrieve(providerSource, this);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_catalog, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @NonNull @Override final String getTitle() {
    return "Справочник";
  }

  @Override public final void onProvide(@NonNull List<CatalogEntry> payload) {
    loader.setVisibility(View.GONE);
    adapter.updateData(payload);
  }

  @Override public final void onItemClick(@NonNull CatalogEntry item) {
  }
}
