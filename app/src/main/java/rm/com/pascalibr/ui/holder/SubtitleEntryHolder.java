package rm.com.pascalibr.ui.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.DescriptionEntry;

/**
 * Created by alex
 */

public final class SubtitleEntryHolder extends DescriptionEntryHolder {

  public SubtitleEntryHolder(View itemView) {
    super(itemView);
  }

  @BindView(R.id.item_article_subtitle) TextView subtitle;

  @Override public void bind(@NonNull DescriptionEntry model) {
    subtitle.setText(model.data);
  }
}
