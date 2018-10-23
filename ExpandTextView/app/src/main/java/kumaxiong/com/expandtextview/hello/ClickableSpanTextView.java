package kumaxiong.com.expandtextview.hello;

import android.content.Context;
import android.text.Selection;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ClickableSpanTextView extends android.support.v7.widget.AppCompatTextView {

  static class SpannableClickListener implements View.OnTouchListener {

    private static SpannableClickListener defaultInstance;

    public static SpannableClickListener defaultInstance() {
      if (defaultInstance == null) {
        defaultInstance = new SpannableClickListener();
      }
      return defaultInstance;
    }

    private SpannableClickListener() {}

    @Override
    public boolean onTouch(View view, MotionEvent event) {
      if (!(view instanceof TextView)) {
        return false;
      }
      TextView textView = (TextView) view;

      CharSequence text = textView.getText();
      if (!(text instanceof Spannable)) {
        return false;
      }
      Spannable spannable = (Spannable) text;

      int action = event.getAction();
      if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        android.text.Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        ClickableSpan[] link = spannable.getSpans(off, off, ClickableSpan.class);

        if (link.length != 0) {
          if (action == MotionEvent.ACTION_UP) {
            link[0].onClick(textView);
          } else if (action == MotionEvent.ACTION_DOWN) {
            Selection.setSelection(
                spannable, spannable.getSpanStart(link[0]), spannable.getSpanEnd(link[0]));
          }

          return true;
        } else {
          Selection.removeSelection(spannable);
        }
      }

      return false;
    }
  }

  static class CompositeOnTouchListener implements View.OnTouchListener {
    OnTouchListener[] listeners;

    public CompositeOnTouchListener(OnTouchListener... listeners) {
      this.listeners = listeners;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
      for (OnTouchListener listener : listeners) {
        // Apply each touch listener in order until one returns true
        if (listener.onTouch(view, event)) {
          return true;
        }
      }
      return false;
    }
  }

  public ClickableSpanTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    super.setOnTouchListener(SpannableClickListener.defaultInstance());
  }

  @Override
  public void setOnTouchListener(OnTouchListener listener) {
    super.setOnTouchListener(
        new CompositeOnTouchListener(SpannableClickListener.defaultInstance(), listener));
  }
}