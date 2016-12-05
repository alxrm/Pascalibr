package rm.com.pascalibr.model;

/**
 * Created by alex
 */

public final class CatalogEntry {

  public final String name;
  public final String type;
  public final String fileName;

  public CatalogEntry(String name, String type, String fileName) {
    this.name = name;
    this.type = type;
    this.fileName = fileName;
  }
}
