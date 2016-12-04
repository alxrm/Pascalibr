package rm.com.pascalibr.data;

import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import java.util.concurrent.ExecutorService;
import rm.com.pascalibr.util.Conditions;
import rm.com.pascalibr.util.Files;

/**
 * Created by alex
 */
public abstract class GsonProvider<T> {

  protected final ExecutorService executor;
  protected final AssetManager assets;
  protected final Handler mainThreadHandler;
  protected final Gson gson;

  public GsonProvider(@NonNull ExecutorService executor, @NonNull Handler mainThreadHandler,
      @NonNull Gson gson, @NonNull AssetManager assets) {
    this.executor = executor;
    this.mainThreadHandler = mainThreadHandler;
    this.gson = gson;
    this.assets = assets;
  }

  public void retrieve(@NonNull final String path, @NonNull final ProviderListener<T> callback) {
    Conditions.checkNotNull(callback);

    executor.submit(new Runnable() {
      @Override public void run() {
        final String raw = Files.readText(assets, path);
        final T result = parse(raw);

        Conditions.checkNotNull(result, "Result cannot be null");

        sendResult(callback, result);
      }
    });
  }

  @NonNull protected abstract T parse(String src);

  protected void sendResult(final ProviderListener<T> callback, final T result) {
    mainThreadHandler.post(new Runnable() {
      @Override public void run() {
        callback.onProvide(result);
      }
    });
  }
}