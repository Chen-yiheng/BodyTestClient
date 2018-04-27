package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 *
 */

public class NetworkActivity extends AppCompatActivity {
	private NetworkReceiver receiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new NetworkReceiver();
		registerReceiver(receiver, intentFilter);

	}

	class NetworkReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager manager = (ConnectivityManager) getSystemService
				   (CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				Toast.makeText(NetworkActivity.this, "neteork is available", Toast
					   .LENGTH_SHORT).show();
			} else {
				Toast.makeText(NetworkActivity.this, "neteork is not available", Toast
					   .LENGTH_SHORT).show();
			}
		}


	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
	}
}

//通过静态注册后,无需启动软件便可接收广播
class NetworkChangeReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService
			   (Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isAvailable()) {
			Toast.makeText(context, "网络可用", Toast
				   .LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "网络未连接", Toast
				   .LENGTH_SHORT).show();
		}
	}
}
