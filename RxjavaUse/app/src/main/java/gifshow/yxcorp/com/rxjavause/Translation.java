package gifshow.yxcorp.com.rxjavause;

import android.util.Log;

public class Translation {
  private static final String TAG = "GetTranslation:xiong";
  private int mStatus;
  private Content mContent;

  private static class Content {
    private String mFrom;
    private String mTo;
    private String mVendor;
    private String mOut;
    private String mErrNo;

  }

  public void show() {
    Log.d(TAG, "show:mStatus " + mStatus);
    Log.d(TAG, "show:mFrom" + mContent.mFrom);
    Log.d(TAG, "show:mTo " + mContent.mTo);
    Log.d(TAG, "show:mVendor " + mContent.mVendor);
    Log.d(TAG, "show:mOut " + mContent.mOut);
    Log.d(TAG, "show:mErrNo " + mContent.mErrNo);
  }
}
