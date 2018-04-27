package com.example.vip.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

/**
 *
 * Fragment作为activity一个界面的组成部分，因此Fragment的生命周期和Activity一致
 * 可以静态地使用Fragment，也可以动态地生成修改和删除Fragment，本例测试的是静态地使用fragment
 * 在Apptheme中可以设置了隐藏状态栏，只需加入<item name="android:windowFullscreen">true</item>
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();//隐藏标题栏,



	}
}
