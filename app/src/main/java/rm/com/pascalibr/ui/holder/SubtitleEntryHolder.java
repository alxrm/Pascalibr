package rm.com.pascalibr.ui.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.DescriptionEntry;

/**
 * элемент статьи, содержащий подзаголовок
 */
public final class SubtitleEntryHolder extends DescriptionEntryHolder {

  @BindView(R.id.item_article_subtitle) TextView subtitle;

  public SubtitleEntryHolder(View itemView) {
    super(itemView);
  }

  @Override public void bind(@NonNull DescriptionEntry model) {
    subtitle.setText(model.data);
  }
}
