package com.newcj.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocalService1 extends Service {

	/**
	 * onBind �� Service �ĳ��󷽷���������ǲ��ò�ʵ������
	 * ���� null����ʾ�ͷ��˲��ܽ������˷�������ӡ�
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
