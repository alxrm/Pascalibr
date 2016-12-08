package rm.com.pascalibr.model;

/**
 * тип данных с информацией о заголовке статьи
 */
public final class Title {

  public final String titleText;
  public final String type;

  public Title(String titleText, String type) {
    this.titleText = titleText;
    this.type = type;
  }
}
