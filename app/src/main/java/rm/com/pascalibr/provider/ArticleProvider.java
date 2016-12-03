package rm.com.pascalibr.provider;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import rm.com.pascalibr.model.Article;
import rm.com.pascalibr.model.DescriptionEntry;
import rm.com.pascalibr.model.Title;

/**
 * Created by alex
 */
public final class ArticleProvider extends GsonProvider<Article> {

  public ArticleProvider(
      @NonNull ExecutorService executor,
      @NonNull Handler mainThreadHandler,
      @NonNull Gson gson,
      @NonNull AssetManager assets
  ) {
    super(executor, mainThreadHandler, gson, assets);
  }

  @Override
  protected Article parse(String src) {
    final JsonObject rootObj = new JsonParser().parse(src).getAsJsonObject();

    final Title title = readTitle(rootObj.getAsJsonObject("title"));
    final ArrayList<DescriptionEntry> description = readDescription(rootObj.getAsJsonArray("description"));

    return new Article(title, description);
  }

  private Title readTitle(JsonObject src) {
    return new Title(src.get("titleText").getAsString(), src.get("type").getAsString());
  }

  private ArrayList<DescriptionEntry> readDescription(JsonArray src) {
    final ArrayList<DescriptionEntry> result = new ArrayList<>(src.size());

    for (JsonElement elem : src) {
      final JsonObject entryObj = elem.getAsJsonObject();
      final String type = entryObj.get("type").getAsString();
      final String data = entryObj.get("data").getAsString();
      final DescriptionEntry entry = new DescriptionEntry(type, data);

      result.add(entry);
    }

    return result;
  }
}
