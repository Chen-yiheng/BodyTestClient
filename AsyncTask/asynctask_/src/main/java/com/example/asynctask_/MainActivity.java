package com.example.asynctask_;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 为了更加方便我们在子线程中对 UI 进行操作，Android 还提供了另外一些好用的工
 * 具，AsyncTask 就是其中之一。借助 AsyncTask，即使你对异步消息处理机制完全不了解，
 * 也可以十分简单地从子线程切换到主线程。
 */

public class MainActivity extends AppCompatActivity {
	private TextView textView;
	private Button change;
	private boolean isChange;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		change = (Button) findViewById(R.id.change);
		textView = (TextView) findViewById(R.id.text2);
		change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				isChange = true;
			}
		});
		changeTask.execute();
	}

	private AsyncTask<Void, String, Boolean> changeTask = new AsyncTask<Void, String, Boolean>
		   () {
		@Override
		protected void onPreExecute() {
			textView.setText("我爱你");
		}

		@Override
		protected Boolean doInBackground(Void... voids) {
			while (true) {
				if (isChange) {
					publishProgress("北国风光，千里冰封，万里雪飘，望长城内外，惟余莽莽，大河上下，顿失滔滔");
					break;
				}

			}
			return true;
		}


		@Override
		protected void onProgressUpdate(String... values) {
			textView.setText(values[0]);
		}

		@Override
		protected void onPostExecute(Boolean aBoolean) {
			Toast.makeText(MainActivity.this, "已结束", Toast.LENGTH_SHORT).show();
		}


	};


}

