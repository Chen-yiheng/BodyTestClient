package com.example.vip.asynctask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;


/**
 * Android不允许在子线程中进行UI操作，有些时候，我们必须在子线程里去执行一些耗时任务，
 * 然后根据任务的执行结果来更新相应的 UI 控件，对于这种情况，Android 提供了一套异步消息处理机制，
 * 完美地解决了在子线程中进行UI 操作的问题，本例使用handler
 */
public class MainActivity extends AppCompatActivity {
	private TextView textView;
	private Button change;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(msg.arg1).append("\n");
			buffer.append((Date) msg.obj).append("\n");
			buffer.append(msg.getData().getString("arg"));
			textView.setText(buffer.toString());

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text2);
		change = (Button) findViewById(R.id.change);
		change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new Thread() {
					@Override
					public void run() {
						Message message = new Message();
						message.arg1 = 8;
						message.obj = new Date();
						Bundle bundle = new Bundle();
						bundle.putString("arg", "我爱你");
						message.setData(bundle);
						handler.sendMessage(message);

					}
				}.start();
			}
		});
	}
}

