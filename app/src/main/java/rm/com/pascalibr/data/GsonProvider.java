package rm.com.pascalibr.data;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import java.util.concurrent.ExecutorService;
import rm.com.pascalibr.util.Conditions;
import rm.com.pascalibr.util.Files;

/**
 * Обобщённый класс для чтения данных из JSON файлов
 * с помощью библиотеки GSON
 *
 * @param <T> тип данных, который должен вернуться
 * в результате чтения и десериализации
 */
public abstract class GsonProvider<T> {

  protected final ExecutorService executor;
  protected final AssetManager assets;
  protected final Handler mainThreadHandler;
  protected final Gson gson;

  protected volatile T cachedResult;

  public GsonProvider(@NonNull ExecutorService executor, @NonNull Handler mainThreadHandler,
      @NonNull Gson gson, @NonNull AssetManager assets) {
    this.executor = executor;
    this.mainThreadHandler = mainThreadHandler;
    this.gson = gson;
    this.assets = assets;
  }

  /**
   * основной метод, который вызывается при чтении данных из JSON файла
   * метод выполняет чтение в фоновом потоке
   *
   * @param path путь к JSON файлу
   * @param callback экземпляр вызывающего класса,
   * реализующего интерфейс слушателя результата,
   * как только файл будет прочитан и десериализован,
   * в главном потоке сразу же придут данные в вызывающий класс
   */
  public void provide(@NonNull final String path, @NonNull final ProviderListener<T> callback) {
    Conditions.checkNotNull(callback);

    executor.submit(new Runnable() {
      @Override public void run() {
        final String raw = Files.readText(assets, path);
        final T result = parse(raw != null ? raw : "");

        Conditions.checkNotNull(result, "Result cannot be null");

        cachedResult = result;
        sendResult(result, callback);
      }
    });
  }

  /**
   * приватный метод, вызывающийся при десериализации текста JSON файла в конкретный тип данных
   * метод абстрактный, так как для каждого типа данных может быть своя реализация чтения
   *
   * @param src содержимое JSON файла в текстовом формате
   * @return возвращает десериализованный объект
   */
  @NonNull protected abstract T parse(@NonNull String src);

  /**
   * метод отправки результата вызывающему классу в главном потоке
   *
   * @param result объект с результатом чтения файла
   * @param callback экземпляр вызывающего класса, реализующего интерфейс слушателя результата
   */
  protected void sendResult(@NonNull final T result, @NonNull final ProviderListener<T> callback) {
    mainThreadHandler.post(new Runnable() {
      @Override public void run() {
        callback.onProvide(result);
      }
    });
  }
}