package rm.com.pascalibr.util;

import java.util.HashMap;
import rm.com.pascalibr.R;

/**
 * набор различных констант
 */
public interface Constants {

  // типы элементов справочника
  String TYPE_CONSTRUCTION = "Конструкция";
  String TYPE_KEYWORD = "Зарезервированное слово";
  String TYPE_PROCEDURE = "Процедура";
  String TYPE_FUNCTION = "Функция";
  String TYPE_DIRECTIVE = "Процедурная директива";
  String TYPE_OPERATOR = "Оператор";

  // типы элементов статьи
  String ENTRY_SUBTITLE = "subtitle";
  String ENTRY_TEXT = "text";
  String ENTRY_CODE = "code";

  // константы для различия элементов статьи при отрисовке
  int ENTRY_SUBTITLE_KEY = 0;
  int ENTRY_TEXT_KEY = 1;
  int ENTRY_CODE_KEY = 2;

  // карта, где ключ это тип элемента справочника, а значение это цвет круглой иконки
  HashMap<String, Integer> TYPE_COLORS = new HashMap<String, Integer>() {{
    put(TYPE_CONSTRUCTION, 0xFF512DA8);
    put(TYPE_KEYWORD, 0xFF303F9F);
    put(TYPE_PROCEDURE, 0xFF0288D1);
    put(TYPE_FUNCTION, 0xFF0097A7);
    put(TYPE_DIRECTIVE, 0xFF00796B);
    put(TYPE_OPERATOR, 0xFF388E3C);
  }};

  // карта, где ключ это тип элемента статьи,
  // а значение это константа для отрисовки элементов статьи
  HashMap<String, Integer> ENTRY_KEYS = new HashMap<String, Integer>() {{
    put(ENTRY_SUBTITLE, ENTRY_SUBTITLE_KEY);
    put(ENTRY_TEXT, ENTRY_TEXT_KEY);
    put(ENTRY_CODE, ENTRY_CODE_KEY);
  }};

  // карта, где ключ это константа для отрисовки элементов статьи,
  // а значение это вёрстка элемента статьи
  HashMap<Integer, Integer> ENTRY_LAYOUTS = new HashMap<Integer, Integer>() {{
    put(ENTRY_SUBTITLE_KEY, R.layout.item_article_subtitle);
    put(ENTRY_TEXT_KEY, R.layout.item_article_text);
    put(ENTRY_CODE_KEY, R.layout.item_article_code);
  }};
}
