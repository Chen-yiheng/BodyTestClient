package com.example.vip.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

/**
 *
 */

public class MyService extends Service {
	private MyBinder binder;
	private NotificationManager notificationManager;


	public class MyBinder extends Binder {
		public MyService getService() {
			return MyService.this;
		}

		public float add(int a, int b) {
			return (a + b);
		}
	}


	@Override
	public void onCreate() {
		super.onCreate();
		binder = new MyBinder();
		notificationManager = (NotificationManager) getSystemService(Context
			   .NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity
			   .class), 0);
		builder.setContentIntent(intent);
		builder.setSmallIcon(R.drawable.a);
		builder.setTicker("开始");
//		builder.setAutoCancel(true);//设置此标志将使其在用户在面板中单击时自动取消通知。
// 当通知被取消时，将通过{@link #setDeleteIntent}设置的PendingInten将被广播。
		builder.setContentTitle("明志体心");
		builder.setContentText("天将降大任于斯人也，必先苦其心志，劳其筋骨，空乏其身，行拂乱其所为，使增益有所不能");
		Notification notification = builder.build();
//		startForeground(1, notification);
		notificationManager.notify(1, notification);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		stopForeground(true);
		notificationManager.cancel(1);
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
}
