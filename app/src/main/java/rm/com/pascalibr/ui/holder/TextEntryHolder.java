package rm.com.pascalibr.ui.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.DescriptionEntry;

/**
 * элемент статьи, содержащий текст с описанием
 */
public final class TextEntryHolder extends DescriptionEntryHolder {

  @BindView(R.id.item_article_text) TextView text;

  public TextEntryHolder(View itemView) {
    super(itemView);
  }

  @Override public void bind(@NonNull DescriptionEntry model) {
    text.setText(model.data.trim());
  }
}
