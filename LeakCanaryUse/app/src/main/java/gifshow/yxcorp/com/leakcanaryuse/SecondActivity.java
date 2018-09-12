package gifshow.yxcorp.com.leakcanaryuse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
  private  static Context sContext;
  private static View sView;
  private static final String TAG = "SecondActivity";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    sContext = this;
    getIntentContent();
  }

  private void getIntentContent(){
    Uri uri = getIntent().getData();
    if(uri != null) {
      String scheme = uri.getScheme();
      String host = uri.getHost();
      String path = uri.getPath();
      int port = uri.getPort();
      String query = uri.getQuery(); //查询部分
      
      String goodId = uri.getQueryParameter("goodId");

      Log.d(TAG, "getIntentContent: ");
    }
  }
}
