package com.example.vip.socketclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 169.254.94.3
 */
public class MainActivity extends AppCompatActivity {
	private TextView receivedInfo;
	private EditText sendInfo;
	private Socket client;
	private Button send;
	private DataOutputStream dos;
	private DataInputStream dis;
	private final String TAG = "TAG";
	private boolean isSended;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		receivedInfo = (TextView) findViewById(R.id.receivedInfo);
		sendInfo = (EditText) findViewById(R.id.sendInfo);
		send = (Button) findViewById(R.id.send);
		new Send().start();
		new Receiver().start();
		Log.w(TAG, "完成初始化");
	}

	private class Send extends Thread {
		private boolean isSend;

		public Send() {
			isSend = true;
			try {
				client = new Socket("169.254.94.3", 9999);
				dos = new DataOutputStream(client.getOutputStream());
				dis = new DataInputStream(client.getInputStream());
				Log.w(TAG, "已经初始化bw+br：" + dos + dis);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			while (isSend) {
				if (isSended) {
					try {
						dos.writeUTF(sendInfo.getText().toString());
						dos.flush();
						Log.w(TAG, "发送完成");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private class Receiver extends Thread {
		private boolean isReceived;

		public Receiver() {
			isReceived = true;
			Log.w(TAG, "线程开始创建");
		}

		@Override
		public void run() {
			while (isReceived) {
				try {
					String str = dis.readUTF();
					receivedInfo.setText(receivedInfo.getText() + str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}


	public void onSend(View view) {
		isSended = true;


	}

	@Override
	protected void onDestroy() {
		try {
			if (null != dos) {
				dos.close();
			}
			if (null != dis) {
				dis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
