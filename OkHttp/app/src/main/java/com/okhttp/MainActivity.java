package com.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
	private Button connect;
	private TextView connectInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connect = (Button) findViewById(R.id.connectHttp);
		connectInfo = (TextView) findViewById(R.id.info);
		connect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sendGetRequest();
			}
		});
	}

	private void sendGetRequest() {
		new Thread() {
			@Override
			public void run() {
				try {
					OkHttpClient client = new OkHttpClient();
					Request request = new Request.Builder().url("http://www.baidu.com")
						   .build();
					Response response = client.newCall(request).execute();
					String responseData = response.body().string();
					showConnectInfo(responseData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void showConnectInfo(final String info) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				connectInfo.setText(info);
			}
		});
	}

	private void sendPostRequest() {
		try {
			OkHttpClient okHttpClient = new OkHttpClient();
			RequestBody body = new FormBody.Builder().add("username", "黄益凛").add("password",
				   "ahdia").build();
			Request request=new Request.Builder().url("http://www.baidu.com").post(body).build();
			Response response=okHttpClient.newCall(request).execute();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
