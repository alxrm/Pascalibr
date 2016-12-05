package rm.com.pascalibr;

import dagger.Component;
import javax.inject.Singleton;
import rm.com.pascalibr.ui.ArticleFragment;
import rm.com.pascalibr.ui.CatalogFragment;

/**
 * Created by alex
 */

@Singleton
@Component(modules = PascalibrModule.class)
public interface PascalibrComponent {
  void inject(CatalogFragment fragment);
  void inject(ArticleFragment fragment);
}
