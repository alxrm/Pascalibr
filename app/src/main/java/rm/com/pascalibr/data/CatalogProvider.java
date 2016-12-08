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
 * класс для чтения списка статей про элементы языка из JSON файла
 */
public final class CatalogProvider extends GsonProvider<List<CatalogEntry>> {

  public CatalogProvider(@NonNull ExecutorService executor, @NonNull Handler mainThreadHandler,
      @NonNull Gson gson, @NonNull AssetManager assets) {
    super(executor, mainThreadHandler, gson, assets);
  }

  /**
   * метод для поиска по списку каталогов по определённому запросу
   *
   * @param query текстовый запрос
   * @param callback экземпляр вызывающего класса, реализующего слушатель результата
   */
  public final void find(@NonNull final String query,
      @NonNull final ProviderListener<List<CatalogEntry>> callback) {
    if (cachedResult == null) return;

    executor.submit(new Runnable() {
      @Override public void run() {
        final List<CatalogEntry> found = filter(cachedResult, query);

        sendResult(found, callback);
      }
    });
  }

  /**
   * переопределённый метод десериализации содержимого JSON файла
   * данный метод возвращает экземпляр списка статей {@link CatalogEntry}
   *
   * @param src содержимое JSON файла в текстовом формате
   * @return список статей
   */
  @NonNull @Override protected List<CatalogEntry> parse(@NonNull String src) {
    return gson.fromJson(src, new TypeToken<List<CatalogEntry>>() {}.getType());
  }

  /**
   * приватный метод фильтрации элементов списка, оставляя только те, что соответствуют запросу
   *
   * @param entries список статей
   * @param query сам запрос
   * @return отфильтрованный неизменяемый список статей
   */
  @NonNull private List<CatalogEntry> filter(@NonNull List<CatalogEntry> entries,
      @NonNull String query) {
    final ArrayList<CatalogEntry> result = new ArrayList<>();

    for (CatalogEntry entry : entries) {
      if (entryMatch(entry, query)) result.add(entry);
    }

    return result;
  }

  /**
   * приватный метод проверки статьи на соответствие поисковому запросу
   *
   * @param entry элемент списка статей
   * @param query поисковой запрос
   * @return {@code true} если в названии или типе элемента, описанного в статье,
   * содержится поисковой запрос, в противном случае возвращается {@code false}
   */
  private boolean entryMatch(@NonNull CatalogEntry entry, @NonNull String query) {
    return entry.type.toLowerCase().contains(query.toLowerCase()) || entry.name.toLowerCase()
        .contains(query.toLowerCase());
  }
}
