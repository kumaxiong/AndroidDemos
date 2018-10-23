package kumaxiong.com.expandtextview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

  TextView tv;

  TextView mTvExpend;

  boolean isExpend = false;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv = findViewById(R.id.tv);
    mTvExpend = findViewById(R.id.tv_expend);
    tv.setText("HHHHHHHHHHHHHHHHHHHHHHHH测试HHH测试HHH测试HHH测试HHH测试HHH测试HHH测试HHH测试HHH测试HHH测试HHH测试HHH测试" +
        "第二行第二行第二行第二行第二行第二行第二行第二行第二行");
//    makeTextViewResizable(tv, 3, "更多", true);
    showCheckAll(tv);
  }

  private void showCheckAll(final TextView textView) {

    if (tv.getTag() == null) {
      tv.setTag(tv.getText());
    }
    ViewTreeObserver vto = tv.getViewTreeObserver();
    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

      @SuppressWarnings("deprecation")
      @Override
      public void onGlobalLayout() {
        ViewTreeObserver obs = tv.getViewTreeObserver();
        obs.removeGlobalOnLayoutListener(this);

        Layout l = textView.getLayout();
        if (l != null) {
          int lines = l.getLineCount();
          if (lines > 0) {
            if (l.getEllipsisCount(lines - 1) > 0) {
              mTvExpend.setVisibility(View.VISIBLE);
              mTvExpend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if (isExpend) {
                    // 收起逻辑
                    tv.setMaxLines(3);
                    mTvExpend.setText("展开");
                  } else {
                    // 展开逻辑
                    tv.setMaxLines(Integer.MAX_VALUE);
                    mTvExpend.setText("收起");
                  }
                  tv.invalidate();
                  isExpend = !isExpend;
                }
              });
            }
          }

        }
      }
    });
  }

  public void makeTextViewResizable(final TextView tv, final int maxLine,
                                    final String expandText, final boolean viewMore) {

    if (tv.getTag() == null) {
      tv.setTag(tv.getText());
    }
    ViewTreeObserver vto = tv.getViewTreeObserver();
    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

      @SuppressWarnings("deprecation")
      @Override
      public void onGlobalLayout() {
        String text;
        int lineEndIndex;
        ViewTreeObserver obs = tv.getViewTreeObserver();
        obs.removeGlobalOnLayoutListener(this);
        if (maxLine == 0) {
          lineEndIndex = tv.getLayout().getLineEnd(0);
          text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
        } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
          lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
          text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
        } else {
          lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
          text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
        }
        tv.setText(text);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        if (viewMore) {
          mTvExpend.setVisibility(View.VISIBLE);
          mTvExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(MainActivity.this, "Hllo", Toast.LENGTH_SHORT).show();
              tv.setLayoutParams(tv.getLayoutParams());
              tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
              tv.invalidate();
              makeTextViewResizable(tv, -1, "View Less", false);
            }
          });
        } else {
          mTvExpend.setVisibility(View.VISIBLE);
          mTvExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(MainActivity.this, "Hllo", Toast.LENGTH_SHORT).show();
              tv.setLayoutParams(tv.getLayoutParams());
              tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
              tv.invalidate();
              makeTextViewResizable(tv, 3, "View More", true);
            }
          });
        }
//        tv.setText(
//            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
//                viewMore), TextView.BufferType.SPANNABLE);
      }
    });

  }

  private SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned,
                                                                   final TextView tv,
                                                                   final int maxLine, final String spanableText, final boolean viewMore) {
    String str = strSpanned.toString();
    SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

    if (str.contains(spanableText)) {


      ssb.setSpan(new MySpannable(false) {
        @Override
        public void onClick(View widget) {
          tv.setLayoutParams(tv.getLayoutParams());
          tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
          tv.invalidate();
          if (viewMore) {
            makeTextViewResizable(tv, -1, "View Less", false);
          } else {
            makeTextViewResizable(tv, 3, "View More", true);
          }
        }
      }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

    }
    return ssb;

  }

}

class MySpannable extends ClickableSpan {

  private boolean isUnderline = false;

  /**
   * Constructor
   */
  public MySpannable(boolean isUnderline) {
    this.isUnderline = isUnderline;
  }

  @Override
  public void updateDrawState(TextPaint ds) {

    ds.setUnderlineText(isUnderline);
    ds.setColor(Color.parseColor("#343434"));

  }

  @Override
  public void onClick(View widget) {

  }
}