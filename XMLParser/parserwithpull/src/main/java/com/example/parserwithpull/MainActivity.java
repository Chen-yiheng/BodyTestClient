package com.example.parserwithpull;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button parser;
	private TextView text;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			text.setText((String) msg.obj);


		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parser = (Button) findViewById(R.id.parser);
		parser.setOnClickListener(this);
		text = (TextView) findViewById(R.id.text);
		text.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	@Override
	public void onClick(View view) {
		Log.i("tag","按下按键");
		new Thread() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL("http://169.254.143.228:8080/xml/person.xml");
					connection = (HttpURLConnection) url.openConnection();
					XmlPullParser pullParser = XmlPullParserFactory.newInstance()
						   .newPullParser();
					pullParser.setInput(new InputStreamReader(connection.getInputStream()));
					int eventType = pullParser.getEventType();
					String id = "";
					String name = "";
					String age = "";
					String sex = "";
					StringBuffer buffer = new StringBuffer();
					Log.i("tag", "开始解析");
					while (eventType != XmlPullParser.END_DOCUMENT) {
						String nodeName = pullParser.getName();
						switch (eventType) {
							case XmlPullParser.START_TAG:

								if ("id".equals(nodeName)) {
									id = pullParser.nextText();;
								} else if ("name".equals(nodeName)) {
									name = pullParser.nextText();;
								} else if ("age".equals(nodeName)) {
									age = pullParser.nextText();;
								} else if ("sex".equals(nodeName)) {
									sex = pullParser.nextText();;
								}
								break;
							case XmlPullParser.END_TAG:
								if ("person".equals(nodeName)) {
									buffer.append("id：").append(id).append("\n");
									buffer.append("名字：").append(name).append("\n");
									buffer.append("性别：").append(sex).append("\n");
									buffer.append("年龄：").append(age).append("\n");
								}
								break;
						}
						eventType=pullParser.next();
					}
					Log.i("tag", "解析完成");
					Message message = new Message();
					message.obj = buffer.toString();
					handler.sendMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
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
