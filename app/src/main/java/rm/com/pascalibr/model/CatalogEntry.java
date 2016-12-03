package rm.com.pascalibr.model;

import android.support.annotation.NonNull;

/**
 * Created by alex
 */

public final class CatalogEntry {

  public final String name;
  public final String type;

  public CatalogEntry(@NonNull String name, @NonNull String type) {
    this.name = name;
    this.type = type;
  }
}
