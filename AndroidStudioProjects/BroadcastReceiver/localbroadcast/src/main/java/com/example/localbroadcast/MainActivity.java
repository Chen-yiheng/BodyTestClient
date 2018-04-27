package com.example.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 测试本地广播机制，使用这个机制发出的广播只能跟在程序的内部进行传递，
 * 并且广播接收器也只能接收内部的广播
 */

public class MainActivity extends AppCompatActivity {
	public final static String MY_BROADCAST_ACTION = "android.MY_BROADCAST";
	private Button sendLocalBroadcast, sendBroadcast;
	private LocalBroadcastManager localBroadcastManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendLocalBroadcast = (Button) findViewById(R.id.send);
		sendBroadcast = (Button) findViewById(R.id.send3);
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(MY_BROADCAST_ACTION);
		
		localBroadcastManager.registerReceiver(LocalBroadcastReceiver, filter);
		sendLocalBroadcast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				localBroadcastManager.sendBroadcast(new Intent(MY_BROADCAST_ACTION));
			}
		});

		sendBroadcast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendBroadcast(new Intent(MY_BROADCAST_ACTION));
			}
		});

	}

	private BroadcastReceiver LocalBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "接收到本地广播", Toast.LENGTH_SHORT).show();
		}
	};
}
