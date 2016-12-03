package rm.com.pascalibr.model;

import android.support.annotation.NonNull;

/**
 * Created by alex
 */

public final class DescriptionEntry {

  public final String type;
  public final String data;

  public DescriptionEntry(@NonNull String type, @NonNull String data) {
    this.type = type;
    this.data = data;
  }
}
