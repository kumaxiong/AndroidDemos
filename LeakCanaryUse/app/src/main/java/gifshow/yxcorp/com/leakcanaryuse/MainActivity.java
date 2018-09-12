package gifshow.yxcorp.com.leakcanaryuse;

import java.lang.ref.WeakReference;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private static final int MESSAGE_TYPE1 = 10;
  private Button mBtnSendMessage;
  private TextView mTvShow;
  private SuperHandler mHandler = new SuperHandler(this);

  private static class SuperHandler extends Handler {
    private final WeakReference<MainActivity> mActivity;

    private SuperHandler(MainActivity activity) {
      mActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case MESSAGE_TYPE1: {
          MainActivity activity = mActivity.get();
          if(activity != null) {
            activity.setTvShowText("hhh");
          }
        }
      }
      // end handleMessage
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBtnSendMessage = findViewById(R.id.btn_go_second);
    mTvShow = findViewById(R.id.tv_show);
    mBtnSendMessage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Message msg = Message.obtain();
        msg.what = MESSAGE_TYPE1;
//        mHandler.sendMessage(msg);
        mHandler.sendMessageDelayed(msg,1000*60*5);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("hello://test:8081/goods?goodId=1023"));
        startActivity(intent);
      }
    });
  }


  public void setTvShowText(String text) {
    mTvShow.setText(text);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // 移除所有的消息队列
    mHandler.removeCallbacksAndMessages(null);
  }
}


