package kumaxiong.com.aidlbinderuser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private ICalculateAIDL iCalculateAIDL;

  private Button mBtnBind;
  private Button mBtnUnbind;
  private Button mBtnAdd;
  private Button mBtnMinus;
  //连接服务
  private ServiceConnection mServiceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      Log.d(TAG, "onServiceConnected: ");
      iCalculateAIDL = ICalculateAIDL.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      Log.d(TAG, "onServiceDisconnected: ");
      iCalculateAIDL = null;
    }
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.startActivity();
    setContentView(R.layout.activity_main);
    mBtnBind = findViewById(R.id.btn_bind);
    mBtnUnbind = findViewById(R.id.btn_unbind);
    mBtnAdd = findViewById(R.id.btn_add);
    mBtnMinus = findViewById(R.id.btn_min);
    mBtnBind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        bindService();
      }
    });

    mBtnUnbind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        unbindService();
      }
    });

    mBtnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          addInvoked();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    mBtnMinus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          minusInvoked();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }


  public void bindService() {
    Intent intent = new Intent("kumaxiong.com.aidlbinderuser");
    intent.setPackage("kumaxiong.com.aidlbinderuser");
    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
  }

  public void unbindService() {
    unbindService(mServiceConnection);
  }

  public void addInvoked() throws Exception {

    if (iCalculateAIDL != null) {
      int result = iCalculateAIDL.add(1, 1);
      Toast.makeText(this, "result = " + result, Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "异常", Toast.LENGTH_SHORT).show();
    }
  }

  public void minusInvoked() throws Exception {

    if (iCalculateAIDL != null) {
      int result = iCalculateAIDL.min(1, 1);
      Toast.makeText(this, "result = " + result, Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "异常", Toast.LENGTH_SHORT).show();
    }
  }


}
