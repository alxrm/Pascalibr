package rm.com.pascalibr;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex
 */

@Singleton
@Component(modules = PascalibrModule.class)
interface PascalibrComponent {
  void inject(MainActivity activity);
}
