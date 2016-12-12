package rm.com.pascalibr.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import javax.inject.Inject;
import rm.com.pascalibr.PascalibrApplication;
import rm.com.pascalibr.R;
import rm.com.pascalibr.data.ArticleProvider;
import rm.com.pascalibr.data.ProviderListener;
import rm.com.pascalibr.model.Article;
import rm.com.pascalibr.ui.adapter.ArticleAdapter;
import rm.com.pascalibr.util.Converters;

/**
 * экран отображения статьи
 *
 * реализует интерфейс {@link ProviderListener}
 */
public final class ArticleFragment extends BaseContentFragment
    implements ProviderListener<Article> {
  static final String KEY_ARTICLE_NAME = "KEY_ARTICLE_NAME";
  static final String KEY_ARTICLE_SOURCE = "KEY_ARTICLE_SOURCE";

  @Inject ArticleProvider provider;
  @Inject ArticleAdapter adapter;

  private String articleTitle;
  private String providerSource;

  /**
   * метод создания нового экрана, содержащего статью
   *
   * @param name название конструкции языка, информацию про которую нужно отобразить
   * @param source путь к JSON файлу, с содержимым статьи
   * @return новый экземпляр экрана
   */
  @NonNull public static ArticleFragment newInstance(@NonNull String name, @NonNull String source) {
    final Bundle args = new Bundle();
    final ArticleFragment fragment = new ArticleFragment();

    args.putString(KEY_ARTICLE_NAME, name);
    args.putString(KEY_ARTICLE_SOURCE, source);
    fragment.setArguments(args);

    return fragment;
  }

  /**
   * метод, вызываемый при создании экрана, здесь инициализируются классы для получения данных и
   * привязки их к визуальной части
   *
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((PascalibrApplication) getActivity().getApplication()).injector().inject(this);
  }

  /**
   * метод, вызываемый после создания интерфейса окна
   * здесь происходит запрос на чтение данных из файла {@link ArticleProvider}
   * и подключенияе адаптера {@link ArticleAdapter}
   *
   * @param view корневой элемент, в котором отрисовываются элементы экрана
   * @param savedInstanceState сохранённое состояние, не используется здесь
   */
  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    toggleContent(false);
    content.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_primary_light));
    content.setAdapter(adapter);
    provider.provide(providerSource, this);
  }

  /**
   * метод, вызываемый в момент, когда данные прочитаны
   *
   * @param payload сам результат, экземпляр типа данных результата, в данном случае статья
   */
  @Override public void onProvide(@NonNull Article payload) {
    toggleContent(true);
    adapter.updateData(Converters.descriptionSetOf(payload));
  }

  /**
   * метод получения заголовка экрана
   *
   * @return текст заголовка экрана
   */
  @NonNull @Override String getTitle() {
    return articleTitle;
  }

  /**
   * метод включения/выключения кнопки назад в верхнем баре
   *
   * @return здесь возвращается {@code true}, потому что из экрана статьи должна быть возможность
   * вернуться к списку статей
   */
  @Override boolean hasBackButton() {
    return true;
  }

  /**
   * метод распаковки данных, переданных при создании экрана
   *
   * @param args сами параметры с пометкой, что они не пустые
   */
  @Override protected void unwrapArguments(@NonNull Bundle args) {
    super.unwrapArguments(args);

    articleTitle = args.getString(KEY_ARTICLE_NAME, "Ошибка");
    providerSource = args.getString(KEY_ARTICLE_SOURCE, null);
  }
}
