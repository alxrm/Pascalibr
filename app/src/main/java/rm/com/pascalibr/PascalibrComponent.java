package rm.com.pascalibr;

import dagger.Component;
import javax.inject.Inject;
import javax.inject.Singleton;
import rm.com.pascalibr.ui.ArticleFragment;
import rm.com.pascalibr.ui.CatalogFragment;

/**
 * специальный класс, который используется библиотекой, здесь нужно указать,
 * где нужно автоматически инициализировать поля, помеченные аннотацией {@link Inject}
 */
@Singleton
@Component(modules = PascalibrModule.class)
public interface PascalibrComponent {

  /**
   * метод, в котором указывается, в каком классе нужна автоматическая инициализация полей
   * здесь в качестве места, где нужна автоматическая инициализация, указан экран со списком статей
   * 
   * @param fragment экран со списком статей, в котором есть поля с {@link Inject} аннотацией
   */
  void inject(CatalogFragment fragment);
  
  /**
   * здесь в качестве места, где нужна автоматическая инициализация, указан экран со списком статей
   *
   * @param fragment экран с содержимым статьи, в котором есть поля с {@link Inject} аннотацией
   */
  void inject(ArticleFragment fragment);
}
