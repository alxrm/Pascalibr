package rm.com.pascalibr.util;

import android.support.annotation.NonNull;

/**
 * Created by alex
 */

public final class Types {
  public static final String CONSTRUCTION = "Конструкция";
  public static final String KEYWORD = "Зарезервированное слово";
  public static final String PROCEDURE = "Процедура";
  public static final String FUNCTION = "Функция";
  public static final String DIRECTIVE = "Процедурная директива";
  public static final String OPERATOR = "Оператор";

  private Types() {}

  public static int colorOf(@NonNull String type) {
    switch (type) {
      case CONSTRUCTION:
        return 0xFF512DA8;
      case KEYWORD:
        return 0xFF303F9F;
      case PROCEDURE:
        return 0xFF0288D1;
      case FUNCTION:
        return 0xFF0097A7;
      case DIRECTIVE:
        return 0xFF00796B;
      case OPERATOR:
        return 0xFF388E3C;
      default:
        throw new IllegalStateException("Unknown type passed in");
    }
  }

  public static String lettersOf(@NonNull String type) {
    switch (type) {
      case CONSTRUCTION:
        return "К";
      case KEYWORD:
        return "ЗС";
      case PROCEDURE:
        return "П";
      case FUNCTION:
        return "Ф";
      case DIRECTIVE:
        return "ПД";
      case OPERATOR:
        return "О";
      default:
        throw new IllegalStateException("Unknown type passed in");
    }
  }
}
