package rm.com.pascalibr.util;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import rm.com.pascalibr.model.Article;
import rm.com.pascalibr.model.DescriptionEntry;

import static rm.com.pascalibr.util.Constants.ENTRY_KEYS;
import static rm.com.pascalibr.util.Constants.ENTRY_LAYOUTS;
import static rm.com.pascalibr.util.Constants.ENTRY_SUBTITLE;
import static rm.com.pascalibr.util.Constants.ENTRY_TEXT;
import static rm.com.pascalibr.util.Constants.TYPE_COLORS;
import static rm.com.pascalibr.util.Constants.TYPE_LETTERS;

/**
 * Created by alex
 */

public final class Converters {

  private Converters() {
  }

  @NonNull public static String lettersOf(@NonNull String type) {
    return TYPE_LETTERS.get(type);
  }

  public static int colorOf(@NonNull String type) {
    return TYPE_COLORS.get(type);
  }

  public static int entryKeyOf(@NonNull String type) {
    return ENTRY_KEYS.get(type);
  }

  public static int holderLayoutOf(int key) {
    return ENTRY_LAYOUTS.get(key);
  }

  @NonNull public static ArrayList<DescriptionEntry> descriptionSetOf(@NonNull Article payload) {
    final ArrayList<DescriptionEntry> result = new ArrayList<>();
    final int size = payload.description.size();
    final int lastIndex = size - 1;

    result.add(new DescriptionEntry(ENTRY_SUBTITLE, payload.title.titleText));

    int index = 0;

    while (index < size) {
      final DescriptionEntry curEntry = payload.description.get(index);
      final StringBuilder accumulator = new StringBuilder(curEntry.data);

      if (!curEntry.type.equals(ENTRY_TEXT) || index == lastIndex) {
        result.add(curEntry);
        index += 1;
        continue;
      }

      DescriptionEntry next;
      index += 1;

      while (index < size && (next = payload.description.get(index)).type.equals(ENTRY_TEXT)) {
        accumulator.append("\n\n").append(next.data);
        index += 1;
      }

      result.add(new DescriptionEntry(ENTRY_TEXT, accumulator.toString()));
    }

    return result;
  }
}
