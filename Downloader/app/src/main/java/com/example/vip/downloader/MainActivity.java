package com.example.vip.downloader;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	private EditText downAddress;
	private ProgressBar progressBar;
	private TextView downTv;
	private FileDownloader downloader;
	final String TAG = "tag";


	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.i(TAG, "msg：" + msg.arg1);
			progressBar.setProgress(msg.arg1);
			float num = (float) progressBar.getProgress() / (float) progressBar.getMax();
			Log.i(TAG,"num:"+num);
			int result = (int) (num * 100);
			downTv.setText("已下载" + result + "%");
			if (result == 100) {
				Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
			}

		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		downAddress = (EditText) findViewById(R.id.downAddress);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		downTv = (TextView) findViewById(R.id.downTv);
	}

	public void onDownload(View view) {
		final String downAddr = downAddress.getText().toString();
		progressBar.setVisibility(View.VISIBLE);
		downTv.setVisibility(View.VISIBLE);
		if (Environment.getExternalStorageState().equals(Environment
			   .MEDIA_MOUNTED)) {
			File path = Environment.getExternalStorageDirectory();
			downloader = new FileDownloader(MainActivity.this, path, downAddr,
				   5, handler);
			downloader.start();
			progressBar.setMax(downloader.getDownFileLength());
			Log.i(TAG, "开始下载");

		}
	}


}
