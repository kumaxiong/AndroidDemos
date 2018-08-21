package hello.kumaxiong.com.eventbususe;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import gifshow.yxcorp.com.eventbususe.R;
import gifshow.yxcorp.com.eventbususe.SecondActivity;
import hello.kumaxiong.com.eventbususe.events.MessageEvent;
import hello.kumaxiong.com.eventbususe.events.SomeOtherEvent;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private Button mBtnGotoSecondActivity;
  private TextView mTvMessage;

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBtnGotoSecondActivity = findViewById(R.id.btn_go_second_activity);
    mBtnGotoSecondActivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
      }
    });
    mTvMessage = findViewById(R.id.tv_message);
    EventBus.getDefault().register(this);
  }
  
  
  
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onMessageEvent(MessageEvent event) {
    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    mTvMessage.setText("" + event.message);
  }
  
  @Subscribe
  public void handleSomethinElse(SomeOtherEvent event) {
    Log.d(TAG, "handleSomethinElse: ");
  }


  @Override
  protected void onDestroy() {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }
}
