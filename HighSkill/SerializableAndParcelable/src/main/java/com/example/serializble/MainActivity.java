package com.example.serializble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 测试序列化的两种方式
 */
public class MainActivity extends AppCompatActivity {
	private Button goSecondActivity;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		goSecondActivity = (Button) findViewById(R.id.secondActivity);
		goSecondActivity.setVisibility(View.VISIBLE);
		goSecondActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra("persion", new Persion(21, "黄益凛", "男"));
				intent.putExtra("people",new People(23,"黄益威","男"));
				startActivity(intent);
			}
		});
	}
}
