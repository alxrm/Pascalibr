package rm.com.pascalibr.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import rm.com.pascalibr.R;

public final class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    navigateTo(CatalogFragment.newInstance(), true);
  }

  final void navigateTo(@NonNull BaseContentFragment fragment) {
    navigateTo(fragment, false);
  }

  private void navigateTo(@NonNull BaseContentFragment fragment, boolean root) {
    final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(R.id.root, fragment);

    if (!root) {
      fragmentTransaction.addToBackStack(null);
    }

    fragmentTransaction.commit();
  }
}
