package rm.com.pascalibr.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by alex
 */

public final class Article {

  public final Title title;
  public final ArrayList<DescriptionEntry> description;

  public Article(@NonNull Title title, @NonNull ArrayList<DescriptionEntry> description) {
    this.title = title;
    this.description = description;
  }
}
