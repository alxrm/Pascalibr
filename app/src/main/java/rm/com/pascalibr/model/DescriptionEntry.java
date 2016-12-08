package rm.com.pascalibr.model;

/**
 * тип данных элемента содержимого статьи
 */
public final class DescriptionEntry {

  /**
   * тип элемента статьи, может быть трёх типов:
   *
   * 1) subtitle
   * 2) text
   * 3) code
   */
  public final String type;
  public final String data;

  public DescriptionEntry(String type, String data) {
    this.type = type;
    this.data = data;
  }
}
