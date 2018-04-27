package com.example.vip.http;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**GET是请求信息，而post是提交信息
 * post的使用如下
 * connection.setRequestMethod("POST");
 * DataOutputStream out = new DataOutputStream(connection.getOutputStream());
 * out.writeBytes("username=admin&password=123456");
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button connect;
	private TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connect = (Button) findViewById(R.id.connect);
		show = (TextView) findViewById(R.id.show);
		connect.setOnClickListener(this);
		show.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			show.setText((String) msg.obj);


		}
	};

	@Override
	public void onClick(View v) {
		HttpUtil.sendHttpRequest("http://www.baidu.com", new HttpListener() {
			@Override
			public void onFinish(String response) {
				Message message = new Message();
				message.obj = response;
				handler.sendMessage(message);
			}

			@Override
			public void onError(Exception e) {

			}
		});
	}
}
