package rm.com.pascalibr.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import rm.com.pascalibr.R;

/**
 * главный контейнер, здесь происходит смена экранов
 */
public final class MainActivity extends AppCompatActivity {

  /**
   * метод, вызываемый при создании контейнера, здесь выполняется необходимая инициализация
   *
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    navigateTo(CatalogFragment.newInstance(), true);
  }

  /**
   * метод перехода в новый экран с возможностью вернуться назад
   *
   * @param fragment экземпляр экрана, на который переходит пользователь
   */
  final void navigateTo(@NonNull BaseContentFragment fragment) {
    navigateTo(fragment, false);
  }

  /**
   * перегруженная версия метода перехода к новому экрану, с возможностью указания, что экран
   * начальный, если пользователь выйдет из него, то он покинет приложение
   *
   * @param fragment экземпляр экрана, на который переходит пользователь
   * @param root флаг, определяющий, начальный ли это экран
   */
  private void navigateTo(@NonNull BaseContentFragment fragment, boolean root) {
    final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .replace(R.id.root, fragment);

    if (!root) {
      fragmentTransaction.addToBackStack(null);
    }

    fragmentTransaction.commit();
  }
}
