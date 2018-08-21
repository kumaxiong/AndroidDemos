package hello.kumaxiong.com.eventbususe;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gifshow.yxcorp.com.eventbususe.R;
import hello.kumaxiong.com.eventbususe.events.MessageEvent;

public class SecondActivity extends AppCompatActivity {

  private Button mButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    mButton = findViewById(R.id.btn_send_event);
    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EventBus.getDefault().post(new MessageEvent("哈哈哈"));
      }
    });
  }
}
