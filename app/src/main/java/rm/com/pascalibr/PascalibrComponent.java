package rm.com.pascalibr;

import javax.inject.Singleton;

import dagger.Component;
import rm.com.pascalibr.ui.ArticleFragment;
import rm.com.pascalibr.ui.CatalogFragment;
import rm.com.pascalibr.ui.MainActivity;

/**
 * Created by alex
 */

@Singleton
@Component(modules = PascalibrModule.class)
public interface PascalibrComponent {
  void inject(CatalogFragment fragment);
  void inject(ArticleFragment fragment);
}
