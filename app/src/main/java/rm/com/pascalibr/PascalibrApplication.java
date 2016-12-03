package rm.com.pascalibr;

import android.app.Application;

/**
 * Created by alex
 */

public final class PascalibrApplication extends Application {

  private PascalibrComponent component;

  @Override public void onCreate() {
    super.onCreate();

    component = DaggerPascalibrComponent
        .builder()
        .pascalibrModule(new PascalibrModule(this))
        .build();
  }

  final PascalibrComponent injector() {
    return component;
  }
}
