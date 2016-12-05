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

  @Override public void retrieve(@NonNull String path,
      @NonNull ProviderListener<List<CatalogEntry>> callback) {
    super.retrieve(path, callback);
  }

  public void find(@NonNull final String query,
      @NonNull final ProviderListener<List<CatalogEntry>> callback) {
    if (cachedResult == null) return;

    executor.submit(new Runnable() {
      @Override public void run() {
        final List<CatalogEntry> found = filter(cachedResult, query);

        sendResult(callback, found);
      }
    });
  }

  @NonNull @Override protected List<CatalogEntry> parse(String src) {
    return gson.fromJson(src, new TypeToken<List<CatalogEntry>>() {}.getType());
  }

  @NonNull private List<CatalogEntry> filter(List<CatalogEntry> entries, String query) {
    final ArrayList<CatalogEntry> result = new ArrayList<>();

    for (CatalogEntry entry : entries) {
      if (entryMatch(entry, query)) result.add(entry);
    }

    return result;
  }

  private boolean entryMatch(CatalogEntry entry, String query) {
    return entry.type.toLowerCase().contains(query.toLowerCase()) || entry.name.toLowerCase()
        .contains(query.toLowerCase());
  }
}
