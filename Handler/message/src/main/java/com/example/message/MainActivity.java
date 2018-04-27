package com.example.message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * handler 启动一个线程时并没有调用线程的start方法。
 * 而只是在main线程中调用线程的run方法
 */
public class MainActivity extends AppCompatActivity {
	private ProgressBar bar;
	private Button button;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			bar.setProgress(msg.arg1);
			handler.post(runnable);
		}
	};

	private Runnable runnable = new Runnable() {
		private int i = 0;

		@Override
		public void run() {
			Log.i("tag", "会当凌绝顶，一览众山小" + i);
			i += 10;
			Message message = handler.obtainMessage();
			message.arg1 = i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handler.sendMessage(message);
			if (i == 100) {
				handler.removeCallbacks(runnable);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar = (ProgressBar) findViewById(R.id.bar);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bar.setVisibility(View.VISIBLE);
				handler.post(runnable);
			}
		});

	}
}
