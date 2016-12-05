package rm.com.pascalibr.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by alex
 */

public final class Conditions {

  private Conditions() {
  }

  public static void check(boolean clause) {
    if (!clause) throw new IllegalStateException("Check failed");
  }

  public static void check(boolean clause, @NonNull String message) {
    if (!clause) throw new IllegalStateException("Check failed: " + message);
  }

  public static <T> T checkNotNull(@Nullable T item) {
    if (item == null) {
      throw new NullPointerException("Element should not be null");
    }

    return item;
  }

  public static <T> T checkNotNull(@Nullable T item, @NonNull String message) {
    if (item == null) {
      throw new NullPointerException("Element should not be null: " + message);
    }

    return item;
  }
}
