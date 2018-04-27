package com.newcj.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocalService1 extends Service {

	/**
	 * onBind 是 Service 的抽象方法，因此我们不得不实现它。
	 * 返回 null，表示客服端不能建立到此服务的连接。
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
