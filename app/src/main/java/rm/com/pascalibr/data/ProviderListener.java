package rm.com.pascalibr.data;

import android.support.annotation.NonNull;

/**
 * Created by alex
 */
public interface ProviderListener<T> {
  void onProvide(@NonNull T payload);
}
