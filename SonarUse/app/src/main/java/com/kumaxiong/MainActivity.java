package com.kumaxiong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kumaxiong.sonaruse.BuildConfig;
import com.example.kumaxiong.sonaruse.R;
import com.facebook.soloader.SoLoader;
import com.facebook.sonar.android.AndroidSonarClient;
import com.facebook.sonar.android.utils.SonarUtils;
import com.facebook.sonar.core.SonarClient;
import com.facebook.sonar.plugins.inspector.DescriptorMapping;
import com.facebook.sonar.plugins.inspector.InspectorSonarPlugin;
import com.facebook.sonar.plugins.network.NetworkSonarPlugin;

public class MainActivity extends AppCompatActivity {

  private NetworkSonarPlugin mNetworkSonarPlugin;
  private MySonarPlugin mySonarPlugin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // Sonar初始化
    SoLoader.init(this,false);


    if(BuildConfig.DEBUG && SonarUtils.shouldEnableSonar(this)) {
      // Sonar 客户端
      final SonarClient client = AndroidSonarClient.getInstance(this);
      DescriptorMapping descriptorMapping = DescriptorMapping.withDefaults();
      client.addPlugin(new InspectorSonarPlugin(getApplicationContext(),descriptorMapping));
      // 新建一个NetWorkSonarPlugin()
      mNetworkSonarPlugin = new NetworkSonarPlugin();
      client.addPlugin(mNetworkSonarPlugin);
      mySonarPlugin = new MySonarPlugin();
      client.addPlugin(mySonarPlugin);
      client.start();
    }
  }
}


