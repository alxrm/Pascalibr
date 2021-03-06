package rm.com.pascalibr.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rm.com.pascalibr.R;

/**
 * базовый класс экрана(фрагмента) приложения, отрисовывающий данные и индикатор загрузки, на
 * случай, если данные ещё не получены
 */
public abstract class BaseContentFragment extends Fragment {

  @BindView(R.id.content_loader) ProgressBar loader;
  @BindView(R.id.content_list) RecyclerView content;

  protected Unbinder unbinder;

  /**
   * метод, вызываемый при создании экрана
   *
   * здесь выставляется параметр, что у экрана может быть меню(поиск — часть меню)
   * также здесь распаковываются параметры, которые могли быть переданы при создании экрана, это
   * используется в экране статьи {@link ArticleFragment}
   *
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    final Bundle args = getArguments();

    if (args != null) {
      unwrapArguments(args);
    }
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
   * метод, вызываемый в момент, когда визульная часть уже создана, здесь происходит инициализация
   * индикатора загрузки и элемента интерфейса для отображения данных
   *
   * @param view корневой элемент, в котором отрисовываются элементы экрана
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  /**
   * метод, вызываемый при выборе элемента меню
   *
   * @param item выбранный элемент
   * @return возвращает флаг, {@code true} если событие обработано или {@code false} в противном
   * случае. {@code super.onOptionsItemSelected(item)} возвращает {@code false}, он вызывается,
   * когда ни один из элементов не был выбран
   */
  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * метод, вызываемый в момент, когда работа с экраном возобновляется
   * в этом методе обновляется заголовок в верхнем баре
   */
  @Override public void onResume() {
    super.onResume();
    updateAppbar();
  }

  /**
   * метод, вызываемый при уничтожении экрана, здесь элементы интерфейса деинициализируются, чтобы
   * не засорять память
   */
  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  /**
   * абстрактный метод, который должен быть переопределён дочерними экранами, нужен для получения
   * заголовка экрана
   *
   * @return строка, содержащая заголовок
   */
  @NonNull abstract String getTitle();

  /**
   * абстрактный метод, который должен быть переопределён дочерними экранами, нужен для того, чтобы
   * знать, должна ли быть в верхнем баре кнопка перехода назад(стрелка влево)
   *
   * @return {@code true} если кнопка должна быть
   */
  abstract boolean hasBackButton();

  /**
   * метод вызываемый при распаковке параметров
   *
   * @param args сами параметры с пометкой, что они не пустые
   */
  protected void unwrapArguments(@NonNull Bundle args) {
  }

  /**
   * метод, эмулирующий нажатие системной кнопки назад
   */
  protected void onBackPressed() {
    final MainActivity owner = getOwner();

    if (owner != null) owner.onBackPressed();
  }

  /**
   * метод получения контейнера, где отрисовываются окна,
   * через него можно менять экраны {@link MainActivity}
   *
   * @return экземпляр контейнера
   */
  @Nullable protected final MainActivity getOwner() {
    return (MainActivity) getActivity();
  }

  /**
   * метод для показа/скрытия индикатора загрузки/контента
   *
   * @param show флаг, определяющий, показывается или скрывается контент
   */
  protected final void toggleContent(boolean show) {
    content.setVisibility(show ? View.VISIBLE : View.GONE);
    loader.setVisibility(show ? View.GONE : View.VISIBLE);
  }

  /**
   * метод, для открытия другого экрана
   *
   * @param fragment экземпляр окна, которое нужно открыть
   */
  protected final void navigateTo(@NonNull BaseContentFragment fragment) {
    final MainActivity owner = getOwner();

    if (owner != null) owner.navigateTo(fragment);
  }

  /**
   * метод обновляющий параметры верхнего бара экрана(наличие кнопки назад и заголовок)
   */
  final void updateAppbar() {
    final MainActivity owner = getOwner();
    final ActionBar supportBar = owner != null ? owner.getSupportActionBar() : null;

    if (supportBar != null) {
      supportBar.setTitle(getTitle());
      supportBar.setDisplayHomeAsUpEnabled(hasBackButton());
    }
  }
}