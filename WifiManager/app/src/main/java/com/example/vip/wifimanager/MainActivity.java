package com.example.vip.wifimanager;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private Button open, close;
	private TextView showWifiState;
	private WifiManager wifiManager;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler = new Handler();
		open = (Button) findViewById(R.id.open);
		close = (Button) findViewById(R.id.close);
		showWifiState = (TextView) findViewById(R.id.wifiState);
		showWifiState.setMovementMethod(new ScrollingMovementMethod());
		wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
		open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				wifiManager.setWifiEnabled(true);
			}
		});
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				wifiManager.setWifiEnabled(false);
			}
		});
		handler.postDelayed(runnable,1000);
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			handler.postDelayed(this, 2000);
			int state = wifiManager.getWifiState();
			switch (state) {
				case WifiManager.WIFI_STATE_DISABLED:
					showWifiState.setText(showWifiState.getText() + "WiFi处于关闭状态\n");
					break;
				case WifiManager.WIFI_STATE_ENABLED:
					showWifiState.setText(showWifiState.getText() + "WiFi处于开启状态\n");
					break;
				case WifiManager.WIFI_STATE_ENABLING:
					showWifiState.setText(showWifiState.getText() + "WiFi正在开启中\n");
					break;
				case WifiManager.WIFI_STATE_DISABLING:
					showWifiState.setText(showWifiState.getText() + "WiFi正在关闭中\n");
					break;
				case WifiManager.WIFI_STATE_UNKNOWN:
					showWifiState.setText(showWifiState.getText() + "WiFi处于未知状态\n");
					break;
			}

		}
	};
}
