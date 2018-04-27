package com.example.vip.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Android在照相接信息接电话等事件发生时会向与之注册的广播接收器发送广播
 * <p>
 * 要想广播接收器接收到广播需要注册，注册的方法有两种
 * 1、在AndroidManifests中注册，这样注册，即便这个运用退出了也会接收到广播
 * 2、在java中用代码注册，运用显示时接收广播，运用不可见时取消注册
 */
public class MainActivity extends AppCompatActivity {
	private Button register, unregister, send;
	private TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		register = (Button) findViewById(R.id.register);
		unregister = (Button) findViewById(R.id.unregister);
		show = (TextView) findViewById(R.id.show);
		send = (Button) findViewById(R.id.send);
		final MyBroadcastReceiver receiver = new MyBroadcastReceiver(show);
		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentFilter filter = new IntentFilter();
				filter.addAction("com.example.vip.broadcastreceiver.MainActivity");
				MainActivity.this.registerReceiver(receiver, filter);
			}
		});

		unregister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.unregisterReceiver(receiver);
			}
		});
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("name", "黄益凛");
				bundle.putInt("age", 22);
				bundle.putString("sex", "男");
				i.putExtras(bundle);
				i.setAction("com.example.vip.broadcastreceiver.MainActivity");
				MainActivity.this.sendBroadcast(i);
			}
		});
	}

}
