package com.example.vip.lrcshow;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vip.lrcshow.viewgroud.TitleView;

/**
 * 测试视图的绘制过程
 */

public class MainActivity extends AppCompatActivity {
	private TitleView titleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		getSupportActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		titleView= (TitleView) findViewById(R.id.title_frame);
		titleView.setButtonText("返回");
		titleView.setmTitleText("最远的时光");

	}
}



