package kumaxiong.com.aidlbinderuser;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CalService extends Service {

  private static final String TAG = "xlq";

  public CalService() {
    Log.d(TAG, "CalService: onCreate");
  }

  @Override
  public IBinder onBind(Intent intent) {
    // 返回Binder用于通信
    return mBinder;
  }


  @Override
  public void onDestroy() {
    Log.d(TAG, "onDestroy: ");
    super.onDestroy();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.d(TAG, "onUnbind: ");
    return super.onUnbind(intent);
  }

  @Override
  public void onRebind(Intent intent) {
    Log.d(TAG, "onRebind: ");
    super.onRebind(intent);
  }

  private final ICalculateAIDL.Stub mBinder = new ICalculateAIDL.Stub() {
    @Override
    public int add(int x, int y) throws RemoteException {
      return x + y;
    }

    @Override
    public int min(int x, int y) throws RemoteException {
      return x - y;
    }
  };
}
