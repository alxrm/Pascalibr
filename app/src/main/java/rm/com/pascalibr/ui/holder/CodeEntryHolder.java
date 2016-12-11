package rm.com.pascalibr.ui.holder;

import android.support.annotation.NonNull;
import android.view.View;
import butterknife.BindView;
import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;
import rm.com.pascalibr.R;
import rm.com.pascalibr.model.DescriptionEntry;

/**
 * элемент статьи, содержащий код
 */
public final class CodeEntryHolder extends DescriptionEntryHolder {

  @BindView(R.id.item_article_code) HighlightJsView codeView;

  public CodeEntryHolder(View itemView) {
    super(itemView);
  }

  @Override public void bind(@NonNull DescriptionEntry model) {
    codeView.setTheme(Theme.MONOKAI);
    codeView.setHighlightLanguage(Language.DELPHI);
    codeView.setSource(model.data);
  }
}
