package rm.com.pascalibr.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import javax.inject.Inject;
import rm.com.pascalibr.PascalibrApplication;
import rm.com.pascalibr.R;
import rm.com.pascalibr.data.ArticleProvider;
import rm.com.pascalibr.data.ProviderListener;
import rm.com.pascalibr.model.Article;
import rm.com.pascalibr.ui.adapter.ArticleAdapter;
import rm.com.pascalibr.util.Converters;

/**
 * Created by alex
 */

public final class ArticleFragment extends BaseContentFragment
    implements ProviderListener<Article> {
  static final String KEY_ARTICLE_NAME = "KEY_ARTICLE_NAME";
  static final String KEY_ARTICLE_SOURCE = "KEY_ARTICLE_SOURCE";

  @Inject ArticleProvider provider;
  @Inject ArticleAdapter adapter;

  private String articleTitle;
  private String providerSource;

  @NonNull public static ArticleFragment newInstance(@NonNull String name, @NonNull String source) {
    final Bundle args = new Bundle();
    final ArticleFragment fragment = new ArticleFragment();

    args.putString(KEY_ARTICLE_NAME, name);
    args.putString(KEY_ARTICLE_SOURCE, source);
    fragment.setArguments(args);

    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((PascalibrApplication) getActivity().getApplication()).injector().inject(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_content, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    content.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_primary_light));
    content.setAdapter(adapter);
    provider.provide(providerSource, this);
  }

  @Override public void onProvide(@NonNull Article payload) {
    loader.setVisibility(View.GONE);
    adapter.updateData(Converters.descriptionSetOf(payload));
  }

  @NonNull @Override String getTitle() {
    return articleTitle;
  }

  @Override boolean hasBackButton() {
    return true;
  }

  @Override protected void unwrapArguments(@NonNull Bundle args) {
    super.unwrapArguments(args);

    articleTitle = args.getString(KEY_ARTICLE_NAME, "Ошибка");
    providerSource = args.getString(KEY_ARTICLE_SOURCE, null);
  }
}
