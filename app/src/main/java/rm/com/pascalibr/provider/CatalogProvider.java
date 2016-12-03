package rm.com.pascalibr.provider;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutorService;

import rm.com.pascalibr.model.CatalogEntry;

/**
 * Created by alex
 */

public final class CatalogProvider extends GsonProvider<List<CatalogEntry>> {

  public CatalogProvider(
      @NonNull ExecutorService executor,
      @NonNull Handler mainThreadHandler,
      @NonNull Gson gson,
      @NonNull AssetManager assets
  ) {
    super(executor, mainThreadHandler, gson, assets);
  }
}
