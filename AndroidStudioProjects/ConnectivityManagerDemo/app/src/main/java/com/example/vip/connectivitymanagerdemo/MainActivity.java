package com.example.vip.connectivitymanagerdemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 获取当前的网络链接状态及网络链接信息
 *
 * 需要用到ConnectivityManager获取链接的管理对象
 * 如果需要获取网络链接状态，需要给定权限
 *  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 *
 */
public class MainActivity extends AppCompatActivity {
     private TextView wifi;
     private TextView gprs;
     private ConnectivityManager manager;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          wifi = (TextView) findViewById(R.id.wifi);
          gprs = (TextView) findViewById(R.id.gprs);
          manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo.State  gprsState= manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                  .getState();
          NetworkInfo.State wifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                  .getState();
          wifi.setText("WIFI的状态为：" + wifiState.toString());
          gprs.setText("GPRS的状态为：" + gprsState.toString());
          if (!gprsState.equals(NetworkInfo.State.CONNECTED) && !wifiState.equals(NetworkInfo
                  .State.CONNECTED)) {
               Toast.makeText(this,"无网络连接，稍后跳转到网络设置页面",Toast.LENGTH_SHORT).show();
               Timer timer=new Timer();
               timer.schedule(new MyTask(),5000);
          }

     }
     class MyTask extends TimerTask{
          @Override
          public void run() {
               Intent i=new Intent();
               i.setAction(Settings.ACTION_WIRELESS_SETTINGS);
               startActivity(i);
          }
     }
}
