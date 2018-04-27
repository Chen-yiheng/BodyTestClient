package com.example.vip.xmlparser;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private TextView tv;
	private Button parser;
	private XMLReader reader;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			reader.setContentHandler(new MyContentHandler(tv));
			try {
				reader.parse(new InputSource(new StringReader((String) msg.obj)));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}

		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView);
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		parser = (Button) findViewById(R.id.parser);
		parser.setOnClickListener(this);
		try {
			reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		new Thread() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL("http://169.254.143.228:8080/xml/person.xml");
					connection = (HttpURLConnection) url.openConnection();
					BufferedReader br = new BufferedReader(new InputStreamReader(connection
						   .getInputStream()));
					StringBuffer buffer = new StringBuffer();
					String line = "";
					while (null != (line = br.readLine())) {
						buffer.append(line).append("\n");
					}
					Message message = new Message();
					message.obj = buffer.toString();
					handler.sendMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
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
