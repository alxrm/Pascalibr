package rm.com.pascalibr;

import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;
import rm.com.pascalibr.data.ArticleProvider;
import rm.com.pascalibr.data.CatalogProvider;
import rm.com.pascalibr.ui.adapter.ArticleAdapter;
import rm.com.pascalibr.ui.adapter.CatalogAdapter;

/**
 * класс-контейнер, нужен для библиотеки, здесь инициализируются значения, которые позже будут
 * записаны в соответствующие поля, помеченные аннотацией {@link Inject}, в классах, указанных в
 * {@link PascalibrComponent}
 */
@Module final class PascalibrModule {

  private final PascalibrApplication app;

  PascalibrModule(PascalibrApplication app) {
    this.app = app;
  }

  @Provides @Singleton ArticleAdapter provideArticleAdapter() {
    return new ArticleAdapter();
  }

  @Provides @Singleton CatalogAdapter provideCatalogAdapter() {
    return new CatalogAdapter();
  }

  @Provides @Singleton Handler provideMainThreadHandler() {
    return new Handler(Looper.getMainLooper());
  }

  @Provides @Singleton AssetManager provideAssetManager() {
    return app.getAssets();
  }

  @Provides @Singleton Gson provideGson() {
    return new Gson();
  }

  @Provides @Singleton ExecutorService provideExecutor() {
    return Executors.newSingleThreadExecutor();
  }

  @Provides @Singleton CatalogProvider provideCatalog(ExecutorService executorService,
      Handler mainThread, Gson gson, AssetManager assets) {
    return new CatalogProvider(executorService, mainThread, gson, assets);
  }

  @Provides @Singleton ArticleProvider provideArticle(ExecutorService executorService,
      Handler mainThread, Gson gson, AssetManager assets) {
    return new ArticleProvider(executorService, mainThread, gson, assets);
  }
}
