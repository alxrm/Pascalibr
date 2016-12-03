package rm.com.pascalibr;

import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rm.com.pascalibr.provider.ArticleProvider;
import rm.com.pascalibr.provider.CatalogProvider;

/**
 * Created by alex
 */

@Module
final class PascalibrModule {

  private final PascalibrApplication app;

  PascalibrModule(PascalibrApplication app) {
    this.app = app;
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

  @Provides @Singleton CatalogProvider provideCatalog(
      ExecutorService executorService, Handler mainThread, Gson gson, AssetManager assets
  ) {
    return new CatalogProvider(executorService, mainThread, gson, assets);
  }

  @Provides @Singleton ArticleProvider provideArticle(
      ExecutorService executorService, Handler mainThread, Gson gson, AssetManager assets
  ) {
    return new ArticleProvider(executorService, mainThread, gson, assets);
  }
}
