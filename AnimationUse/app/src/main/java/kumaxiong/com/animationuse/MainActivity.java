package kumaxiong.com.animationuse;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button mBtnAnimation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBtnAnimation = findViewById(R.id.btn_anim);
    mBtnAnimation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onExtendWidth2(mBtnAnimation);
      }
    });
  }


  public void loadAnimationFromXML() {
    mBtnAnimation.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.va_alpha));
  }
  public void translateAnimation() {
    Animation translateAnimation = new TranslateAnimation(0, 500, 0, 500);
    translateAnimation.setDuration(3000);
    mBtnAnimation.startAnimation(translateAnimation);
  }

  public void scaleAnimation() {
    Animation scaleAnimation = new ScaleAnimation(0, 2, 0, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setDuration(3000);
    mBtnAnimation.startAnimation(scaleAnimation);
  }

  public void rotateAnimation() {
    Animation rotateAnimation = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    rotateAnimation.setDuration(3000);
    mBtnAnimation.startAnimation(rotateAnimation);
  }

  public void alphaAnimation() {
    Animation alphaAnimation = new AlphaAnimation(1, 0);
    alphaAnimation.setDuration(3000);
    mBtnAnimation.startAnimation(alphaAnimation);
  }

  public void combineAnimation() {
    AnimationSet set = new AnimationSet(true);
    set.setRepeatMode(Animation.RESTART);
    set.setRepeatCount(2);

    Animation translateAnimation = new TranslateAnimation(0, 500, 0, 500);
    translateAnimation.setDuration(3000);
    translateAnimation.setRepeatMode(Animation.RESTART);
    translateAnimation.setRepeatCount(1);

    Animation scaleAnimation = new ScaleAnimation(0, 2, 0, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setDuration(3000);
    scaleAnimation.setRepeatMode(Animation.RESTART);
    scaleAnimation.setRepeatCount(2);

    set.addAnimation(translateAnimation);
    set.addAnimation(scaleAnimation);
    mBtnAnimation.startAnimation(set);

  }

  /**
   * 属性动画使用
   */
  public void onExtendWidth(final View view) {
    final int maxWidth = getWindowManager().getDefaultDisplay().getWidth();
    ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,R.animator.pv_animator);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        int currentValue = (int) animation.getAnimatedValue();
        view.getLayoutParams().width = maxWidth*currentValue/100;
        view.requestLayout();
      }
    });
    valueAnimator.start();
  }

  public void onExtendWidth2(final View view) {
    int maxWidth = getWindowManager().getDefaultDisplay().getWidth();
    ViewWrapper viewWrapper = new ViewWrapper(view,maxWidth);
    ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.pv_object_animator);
    objectAnimator.setTarget(viewWrapper);
    objectAnimator.start();
  }
}
