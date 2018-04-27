package com.example.gson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 用Google提供的gson解析json文件，需导入gson包
 * 导包只需将jar包复制至libs文件夹下，在右击点击Add as Library
 *
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button parser;
	private TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parser = (Button) findViewById(R.id.parser);
		parser.setOnClickListener(this);
		show = (TextView) findViewById(R.id.show);
		show.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			show.setText((String)msg.obj);
			show.setText(parserWithGson((String) msg.obj));

		}
	};

	private String parserWithGson(String str) {
		StringBuffer builder = new StringBuffer();
		Gson gson = new Gson();
		List<Person> list = gson.fromJson(str, new TypeToken<List<Person>>() {
		}.getType());
		if(list==null)return "";
		for (Person person : list) {
			Log.i("tag",person.toString());
			builder.append("id：").append(person.getId()).append("\n");
			builder.append("名字：").append(person.getName()).append("\n");
			builder.append("性别：").append(person.getSex()).append("\n");
			builder.append("年龄：").append(person.getAge()).append("\n");
		}
		return builder.toString();
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
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
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
					if (null != connection) {
						connection.disconnect();

					}
				}


			}
		}.start();

	}
}
