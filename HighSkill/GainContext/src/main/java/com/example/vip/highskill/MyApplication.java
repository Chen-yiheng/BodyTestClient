package com.example.vip.highskill;

import android.app.Application;
import android.content.Context;

/**
 * Android 提供了一个 Application 类，每当应用程序启动的时候，系统就会自动将这个类
 * 进行初始化。而我们可以定制一个自己的 Application 类，
 * 以便于管理程序内一些全局的状态信息，比如说全局 Context。
 *
 */

public class MyApplication extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();

	}

	public static Context getContext() {
		return context;
	}

}
