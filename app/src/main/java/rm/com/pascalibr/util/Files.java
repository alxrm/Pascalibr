package rm.com.pascalibr.util;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by alex
 */
public final class Files {

  private Files() {}

  @Nullable public static String readText(@NonNull AssetManager assets, @NonNull String path) {
    Conditions.checkNotNull(assets, "Assets are null");
    Conditions.checkNotNull(path, "Path is null");

    try {
      return readText(assets.open(path));
    } catch (IOException e) {
      Logger.e(e);
      return null;
    } finally {
      assets.close();
    }
  }

  @Nullable public static String readText(@NonNull InputStream stream) {
    try {
      return streamToString(stream);
    } catch (IOException e) {
      Logger.e(e);
    } finally {
      try {
        stream.close();
      } catch (IOException e) {
        Logger.e(e);
      }
    }

    return null;
  }

  @NonNull public static String streamToString(@NonNull InputStream stream) throws IOException {
    Conditions.checkNotNull(stream, "Stream cannot be null");

    final StringBuilder builder = new StringBuilder();
    final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

    String line;

    while ((line = reader.readLine()) != null) builder.append(line);

    return builder.toString();
  }
}
