package rm.com.pascalibr.util;

import java.util.HashMap;
import rm.com.pascalibr.R;

/**
 * Created by alex
 */

public interface Constants {
  String TYPE_CONSTRUCTION = "Конструкция";
  String TYPE_KEYWORD = "Зарезервированное слово";
  String TYPE_PROCEDURE = "Процедура";
  String TYPE_FUNCTION = "Функция";
  String TYPE_DIRECTIVE = "Процедурная директива";
  String TYPE_OPERATOR = "Оператор";

  String ENTRY_SUBTITLE = "subtitle";
  String ENTRY_TEXT = "text";
  String ENTRY_CODE = "code";

  int ENTRY_SUBTITLE_KEY = 0;
  int ENTRY_TEXT_KEY = 1;
  int ENTRY_CODE_KEY = 2;

  HashMap<String, Integer> TYPE_COLORS = new HashMap<String, Integer>() {{
    put(TYPE_CONSTRUCTION, 0xFF512DA8);
    put(TYPE_KEYWORD, 0xFF303F9F);
    put(TYPE_PROCEDURE, 0xFF0288D1);
    put(TYPE_FUNCTION, 0xFF0097A7);
    put(TYPE_DIRECTIVE, 0xFF00796B);
    put(TYPE_OPERATOR, 0xFF388E3C);
  }};

  HashMap<String, String> TYPE_LETTERS = new HashMap<String, String>() {{
    put(TYPE_CONSTRUCTION, "К");
    put(TYPE_KEYWORD, "ЗС");
    put(TYPE_PROCEDURE, "П");
    put(TYPE_FUNCTION, "Ф");
    put(TYPE_DIRECTIVE, "ПД");
    put(TYPE_OPERATOR, "О");
  }};

  HashMap<String, Integer> ENTRY_KEYS = new HashMap<String, Integer>() {{
    put(ENTRY_SUBTITLE, ENTRY_SUBTITLE_KEY);
    put(ENTRY_TEXT, ENTRY_TEXT_KEY);
    put(ENTRY_CODE, ENTRY_CODE_KEY);
  }};

  HashMap<Integer, Integer> ENTRY_LAYOUTS = new HashMap<Integer, Integer>() {{
    put(ENTRY_SUBTITLE_KEY, R.layout.item_article_subtitle);
    put(ENTRY_TEXT_KEY, R.layout.item_article_text);
    put(ENTRY_CODE_KEY, R.layout.item_article_code);
  }};
}
