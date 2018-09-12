package gifshow.yxcorp.com.leakcanaryuse;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class LeakApplication extends Application {

  private static RefWatcher mRefWatcher;
  @Override
  public void onCreate() {
    super.onCreate();
    if(LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    mRefWatcher = LeakCanary.install(this);
  }

  public static RefWatcher getRefWatcher(){
    return mRefWatcher;
  }
}
