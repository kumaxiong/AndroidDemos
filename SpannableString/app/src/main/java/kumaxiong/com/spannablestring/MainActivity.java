package kumaxiong.com.spannablestring;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView textView = findViewById(R.id.span_text);
    textView.setText(getSpan());
    textView.setMovementMethod(LinkMovementMethod.getInstance());

  }


  private CharSequence getSpan(){
    SpannableStringBuilder sb = new SpannableStringBuilder();

    String text = "Hello";
    int start = sb.length();
    int end = start + text.length();
    sb.append(text);
    ClickableSpan clickableSpan = new ClickableSpan() {
      @Override
      public void onClick(View widget) {
        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void updateDrawState(TextPaint ds) {

      }
    };

    sb.setSpan(clickableSpan,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

    return sb;
  }
}
