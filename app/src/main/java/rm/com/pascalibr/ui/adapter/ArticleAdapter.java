package rm.com.pascalibr.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import rm.com.pascalibr.model.DescriptionEntry;
import rm.com.pascalibr.ui.holder.CodeEntryHolder;
import rm.com.pascalibr.ui.holder.DescriptionEntryHolder;
import rm.com.pascalibr.ui.holder.SubtitleEntryHolder;
import rm.com.pascalibr.ui.holder.TextEntryHolder;
import rm.com.pascalibr.util.Constants;
import rm.com.pascalibr.util.Converters;

/**
 * Created by alex
 */

public final class ArticleAdapter extends BaseAdapter<DescriptionEntry, DescriptionEntryHolder> {

  @Override public int getItemViewType(int position) {
    return Converters.entryKeyOf(data.get(position).type);
  }

  @Override public DescriptionEntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View itemView = LayoutInflater.from(parent.getContext())
        .inflate(Converters.holderLayoutOf(viewType), parent, false);

    switch (viewType) {
      case Constants.ENTRY_TEXT_KEY:
        return new TextEntryHolder(itemView);
      case Constants.ENTRY_SUBTITLE_KEY:
        return new SubtitleEntryHolder(itemView);
      case Constants.ENTRY_CODE_KEY:
        return new CodeEntryHolder(itemView);
      default:
        throw new IllegalStateException("Unknown entry type");
    }
  }
}
