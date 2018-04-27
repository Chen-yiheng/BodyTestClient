package com.newcj.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {

	/**
	 * 在 Local Service 中我们直接继承 Binder 而不是 IBinder,因为 Binder 实现了 IBinder 接口，这样我们可以少做很多工作。
	 *
	 * @author newcj
	 */
	public class SimpleBinder extends Binder {
		/**
		 * 获取 Service 实例
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
		// 创建 SimpleBinder
		sBinder = new SimpleBinder();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 返回 SimpleBinder 对象
		return sBinder;
	}

}
