package rm.com.pascalibr.model;

import android.support.annotation.NonNull;

/**
 * Created by alex
 */

public final class Title {

  public final String titleText;
  public final String type;

  public Title(@NonNull String titleText, @NonNull String type) {
    this.titleText = titleText;
    this.type = type;
  }
}
