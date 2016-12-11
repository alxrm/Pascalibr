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
 * экран отображения списка статей
 *
 * реализует разные интерфейсы:
 *
 * {@link ProviderListener}, {@link BaseHolder.OnClickListener},
 * {@link MenuItemCompat.OnActionExpandListener}, {@link SearchView.OnQueryTextListener}
 */
public final class CatalogFragment extends BaseContentFragment
    implements ProviderListener<List<CatalogEntry>>, BaseHolder.OnClickListener<CatalogEntry>,
    MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener {

  @BindString(R.string.path_catalog) String providerSource;
  @BindString(R.string.app_name) String title;

  @Inject CatalogProvider provider;
  @Inject CatalogAdapter adapter;

  /**
   * метод создания нового окна со списком статей
   *
   * @return новый экземпляр окна
   */
  @NonNull public static CatalogFragment newInstance() {
    return new CatalogFragment();
  }

  /**
   * метод, вызываемый при создании экрана, здесь инициализируются классы для получения данных и
   * привязки их к визуальной части
   *
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((PascalibrApplication) getActivity().getApplication()).injector().inject(this);
  }

  /**
   * метод, вызываемый при создании интерфейса экрана
   *
   * @param inflater системный класс для преобразования XML вёрстки в объект интерфейса в коде
   * @param container родительский элемент, в котором отрисовывается окно
   * @param savedInstanceState сохранённое состояние, не используется здесь
   * @return только что созданный объект с интерфейсом экрана
   */
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_content, container, false);
  }

  /**
   * здесь происходит запрос на чтение данных из файла, подключения адаптера {@link CatalogAdapter}
   * и настройки слушателя нажатий на элемент в списке статей
   *
   * @param view корневой элемент, в котором отрисовываются элементы экрана
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    toggleContent(false);

    adapter.setOnClickListener(this);
    content.setAdapter(adapter);
    provider.provide(providerSource, this);
  }

  /**
   * метод вызываемый при создании меню экрана, здесь происходит привязка к событию ввода в
   * поисковую строку
   *
   * @param menu меню экрана
   * @param inflater системный инструмент, для преобразования XML в объект меню
   */
  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_catalog, menu);
    super.onCreateOptionsMenu(menu, inflater);

    final MenuItem searchItem = menu.findItem(R.id.action_search);
    final SearchView searchView = (SearchView) searchItem.getActionView();

    MenuItemCompat.setOnActionExpandListener(searchItem, this);
    searchView.setOnQueryTextListener(this);
  }

  /**
   * метод, вызываемый в момент, когда данные прочитаны
   *
   * @param payload сам результат, экземпляр типа данных результата,
   * в данном случае это список статей
   */
  @Override public void onProvide(@NonNull List<CatalogEntry> payload) {
    toggleContent(true);
    adapter.updateData(payload);
  }

  /**
   * метод, вызываемый в момент, когда пользователь выбрал одну из статей в списке, при нажатии,
   * открывается экран с содержимым статьи
   *
   * @param item элемент списка статей
   */
  @Override public void onItemClick(@NonNull CatalogEntry item) {
    navigateTo(ArticleFragment.newInstance(item.name, item.fileName));
  }

  /**
   * метод, вызываемый при открытии поисковой строки в верхнем баре
   *
   * @param item иконка с лупой
   * @return возвращается флаг {@code true} в случае, если нужно открыть поисковую строку
   */
  @Override public boolean onMenuItemActionExpand(MenuItem item) {
    return true;
  }

  /**
   * метод, вызываемый при закрытии поисковой строки в верхнем баре,
   * при закрытии поисковой запрос очищается и выводятся все элементы списка статей
   *
   * @param item иконка с лупой
   * @return возвращается флаг {@code true} в случае, если нужно закрыть поисковую строку
   */
  @Override public boolean onMenuItemActionCollapse(MenuItem item) {
    updateQuery("");
    return true;
  }

  /**
   * метод, вызываемый при нажатии Enter при вводе в поисковую строку, здесь ничего не нужно
   * делать,
   * поисковая выдача показывается каждый раз, когда обновляется строка запроса
   */
  @Override public boolean onQueryTextSubmit(String query) {
    return false;
  }

  /**
   * метод, вызываемый при изменении поискового запроса, здесь происходит запрос на поиск по
   * элементам списка
   */
  @Override public boolean onQueryTextChange(String newText) {
    updateQuery(newText);
    return true;
  }

  /**
   * метод запроса на поиск по элементам списка
   *
   * @param nextQuery поисковой запрос
   */
  private void updateQuery(String nextQuery) {
    provider.find(nextQuery, this);
  }

  /**
   * метод получения заголовка экрана
   *
   * @return текст заголовка
   */
  @NonNull @Override String getTitle() {
    return title;
  }

  /**
   * метод включения/выключения кнопки назад в верхнем баре
   *
   * @return здесь возвращается {@code false}, потому что в списке статей кнопка назад не нужна
   */
  @Override boolean hasBackButton() {
    return false;
  }
}
