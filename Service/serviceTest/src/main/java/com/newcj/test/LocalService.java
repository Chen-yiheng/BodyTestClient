package com.newcj.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {

	/**
	 * �� Local Service ������ֱ�Ӽ̳� Binder ������ IBinder,��Ϊ Binder ʵ���� IBinder �ӿڣ��������ǿ��������ܶ๤����
	 *
	 * @author newcj
	 */
	public class SimpleBinder extends Binder {
		/**
		 * ��ȡ Service ʵ��
		 *
		 * @return
		 */
		public LocalService getService() {
			return LocalService.this;
		}

		public int add(int a, int b) {
			return a + b;
		}
	}

	public SimpleBinder sBinder;

	@Override
	public void onCreate() {
		super.onCreate();
		// ���� SimpleBinder
		sBinder = new SimpleBinder();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// ���� SimpleBinder ����
		return sBinder;
	}

}
