package rm.com.pascalibr.model;

import java.util.ArrayList;

/**
 * тип данных, содержащий статью
 */
public final class Article {

  public final Title title;
  public final ArrayList<DescriptionEntry> description;

  public Article(Title title, ArrayList<DescriptionEntry> description) {
    this.title = title;
    this.description = description;
  }
}
