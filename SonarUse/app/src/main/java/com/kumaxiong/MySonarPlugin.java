package com.kumaxiong;

import com.facebook.sonar.core.SonarConnection;
import com.facebook.sonar.core.SonarObject;
import com.facebook.sonar.core.SonarPlugin;

public class MySonarPlugin implements SonarPlugin {

  private SonarConnection mSonarConnection;

  @Override
  public String getId() {
    return null;
  }

  @Override
  public void onConnect(SonarConnection connection) throws Exception {
    mSonarConnection = connection;
  }

  @Override
  public void onDisconnect() throws Exception {
    mSonarConnection = null;
  }


  public void sendMessage (final String message) {
    SonarObject sonarObject = new SonarObject.Builder().put("message",message).build();
    mSonarConnection.send("MyMessage",sonarObject);
//    mSonarConnection.receive("getData", new SonarReceiver() {
//      @Override
//      public void onReceive(SonarObject params, SonarResponder responder) throws Exception {
//        responder.success(new SonarObject.Builder()
//            .put("data",message)
//            .build());
//      }
//    });
  }
}