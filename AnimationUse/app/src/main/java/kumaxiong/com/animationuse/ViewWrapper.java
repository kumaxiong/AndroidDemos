package kumaxiong.com.animationuse;

import android.view.View;

public class ViewWrapper {
  private View mTarget;
  private int mMaxWidth;

  public ViewWrapper(View target, int maxWidth) {
    this.mTarget = target;
    this.mMaxWidth = maxWidth;
  }

  public int getWidth() {
    return this.mMaxWidth;
  }

  public void setWidth(int widthValue) {
    mTarget.getLayoutParams().width = mMaxWidth * widthValue / 100;
    mTarget.requestLayout();
  }
}
