package rm.com.pascalibr.data;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.concurrent.ExecutorService;
import rm.com.pascalibr.model.Article;

/**
 * класс для чтения статьи про конкретный элемент языка из JSON файла
 */
public final class ArticleProvider extends GsonProvider<Article> {

  public ArticleProvider(@NonNull ExecutorService executor, @NonNull Handler mainThreadHandler,
      @NonNull Gson gson, @NonNull AssetManager assets) {
    super(executor, mainThreadHandler, gson, assets);
  }

  /**
   * переопределённый метод десериализации содержимого JSON файла
   * данный метод возвращает экземпляр объекта статьи {@link Article}
   *
   * @param src содержимое JSON файла в текстовом формате
   * @return экземпляр объекта статьи
   */
  @NonNull @Override protected Article parse(@NonNull String src) {
    return gson.fromJson(src, new TypeToken<Article>() {}.getType());
  }
}
