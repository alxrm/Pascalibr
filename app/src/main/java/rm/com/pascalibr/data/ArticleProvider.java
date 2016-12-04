package rm.com.pascalibr.data;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import rm.com.pascalibr.model.Article;
import rm.com.pascalibr.model.CatalogEntry;
import rm.com.pascalibr.model.DescriptionEntry;
import rm.com.pascalibr.model.Title;

/**
 * Created by alex
 */
public final class ArticleProvider extends GsonProvider<Article> {

  public ArticleProvider(@NonNull ExecutorService executor, @NonNull Handler mainThreadHandler,
      @NonNull Gson gson, @NonNull AssetManager assets) {
    super(executor, mainThreadHandler, gson, assets);
  }

  @NonNull @Override protected Article parse(String src) {
    return gson.fromJson(src, new TypeToken<Article>() {}.getType());
  }
}
