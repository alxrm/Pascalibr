package rm.com.pascalibr;

import android.app.Application;

/**
 * класс приложения, он нужен для того, чтобы инициализировать что-то на глобальном уровне
 */
public final class PascalibrApplication extends Application {

  private PascalibrComponent component;

  /**
   * метод, который вызывается каждый раз при запуске приложения
   */
  @Override public void onCreate() {
    super.onCreate();

    component =
        DaggerPascalibrComponent.builder().pascalibrModule(new PascalibrModule(this)).build();
  }

  /**
   * метод для получения контейнера инициализированных значений
   *
   * @return экземпляр контейнера
   */
  public final PascalibrComponent injector() {
    return component;
  }
}
