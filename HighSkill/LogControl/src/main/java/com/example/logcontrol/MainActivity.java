package com.example.logcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LogUtil.v("tag", "verbose");
		LogUtil.d("tag", "debug");
		LogUtil.i("tag", "info");
		LogUtil.w("tag", "wram");
		LogUtil.e("tag", "error");
	}
}
