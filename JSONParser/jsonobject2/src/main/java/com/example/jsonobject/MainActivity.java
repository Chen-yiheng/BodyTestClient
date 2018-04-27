package com.example.jsonobject;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 解析json文件
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button parser;
	private TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parser = (Button) findViewById(R.id.parser);
		show = (TextView) findViewById(R.id.show);
		show.setMovementMethod(ScrollingMovementMethod.getInstance());
		parser.setOnClickListener(this);

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			show.setText(parserWithJsonObject((String) msg.obj));
		}
	};

	/**
	 * 用JSONObject解析json文件
	 *
	 * @param str
	 * @return
	 */
	private String parserWithJsonObject(String str) {
		StringBuffer buffer = new StringBuffer();
		try {
			JSONArray jsonArray = new JSONArray(str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				buffer.append("id：").append(jsonObject.getString("id")).append("\n");
				buffer.append("名字：").append(jsonObject.getString("name")).append("\n");
				buffer.append("性别：").append(jsonObject.getString("sex")).append("\n");
				buffer.append("年龄：").append(jsonObject.getString("age")).append("\n");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}




	@Override
	public void onClick(View v) {
		new Thread() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL("http://169.254.143.228:8080/json/person.json");
					connection = (HttpURLConnection) url.openConnection();
					BufferedReader reader = new BufferedReader(new InputStreamReader
						   (connection.getInputStream()));
					String str = "", line = "";
					while (null != (line = reader.readLine())) {
						str += line;
					}
					Message message = new Message();
					message.obj = str;
					handler.sendMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}.start();

	}
}
