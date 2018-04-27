package com.newcj.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Main extends Activity {
	private final static String TAG = "SERVICE_TEST";
	private ServiceConnection sc;
	private boolean isBind;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sc = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName name) {
			}
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				LocalService.SimpleBinder sBinder = (LocalService.SimpleBinder) service;
				Log.v(TAG, "3 + 5 = " + sBinder.add(3, 5));
				Log.v(TAG, sBinder.getService().toString());
			}
		};

		findViewById(R.id.btnBind).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				bindService(new Intent(Main.this, LocalService.class), sc, Context
					   .BIND_AUTO_CREATE);
				isBind = true;
			}
		});

		findViewById(R.id.btnUnbind).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isBind) {
					unbindService(sc);
					isBind = false;
				}
			}
		});
		findViewById(R.id.btnStart).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(Main.this, LocalService1.class));
			}
		});

		findViewById(R.id.btnStop).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(Main.this, LocalService1.class));
			}
		});

		findViewById(R.id.btnStartFore).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(Main.this, ForegroundService.class));
			}
		});


		findViewById(R.id.btnStopFore).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(Main.this, ForegroundService.class));
			}
		});

	}
}