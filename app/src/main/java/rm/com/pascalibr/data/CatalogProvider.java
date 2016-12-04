package rm.com.pascalibr.data;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import rm.com.pascalibr.model.CatalogEntry;

/**
 * Created by alex
 */

public final class CatalogProvider extends GsonProvider<List<CatalogEntry>> {

  public CatalogProvider(@NonNull ExecutorService executor, @NonNull Handler mainThreadHandler,
      @NonNull Gson gson, @NonNull AssetManager assets) {
    super(executor, mainThreadHandler, gson, assets);
  }

  @NonNull @Override protected List<CatalogEntry> parse(String src) {
    return gson.fromJson(src, new TypeToken<List<CatalogEntry>>() {}.getType());
  }
}
