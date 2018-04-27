package com.newcj.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ForegroundService extends Service {

	private static final Class[] mStartForegroundSignature = new Class[]{
		   int.class, Notification.class};
	private static final Class[] mStopForegroundSignature = new Class[]{
		   boolean.class};
	private static final Class<?>[] mSetForegroundSignature = new Class[]{boolean.class};
	private NotificationManager mNM;
	private Method mStartForeground;
	private Method mStopForeground;
	private Method mSetForeground;
	private Object[] mStartForegroundArgs = new Object[2];
	private Object[] mStopForegroundArgs = new Object[1];
	private Object[] mSetForegroundArgs = new Object[1];

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		try {
			mStartForeground = ForegroundService.class.getMethod("startForeground",
				   mStartForegroundSignature);
			mStopForeground = ForegroundService.class.getMethod("stopForeground",
				   mStopForegroundSignature);
			mSetForeground = ForegroundService.class.getMethod("setForeground");
		} catch (NoSuchMethodException e) {
			mStartForeground = mStopForeground = null;
		}
		/*
		 * 我们并不需要为 notification.flags 设置 FLAG_ONGOING_EVENT，因为
		 * 前台服务的 notification.flags 总是默认包含了那个标志位
		 */
		Notification.Builder builder = new Notification.Builder(this);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
			   new Intent(this, Main.class), 0);
		builder.setContentIntent(contentIntent);
		builder.setSmallIcon(R.drawable.icon);
		builder.setTicker("Foreground Service Start");
		builder.setContentTitle("前台服务");
		builder.setContentText("我爱你，黄益凛.");
		Notification notification = builder.build();


	   /*
	    * 注意使用  startForegroundCompat ，id 为 0 将不会显示 notification
         */
		startForegroundCompat(1, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.i("tag", "onStartCommand");
		return START_STICKY;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopForegroundCompat(1);
	}

	/**
	 * 以兼容性方式开始前台服务
	 */
	private void startForegroundCompat(int id, Notification n) {
		if (mStartForeground != null) {
			mStartForegroundArgs[0] = id;
			mStartForegroundArgs[1] = n;
			mSetForegroundArgs[0] = true;

			try {
				mStartForeground.invoke(this, mStartForegroundArgs);
				mSetForeground.invoke(this, mSetForegroundArgs);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			return;
		}
		mNM.notify(id, n);
	}

	/**
	 * 以兼容性方式停止前台服务
	 */
	private void stopForegroundCompat(int id) {
		if (mStopForeground != null) {
			mStopForegroundArgs[0] = Boolean.TRUE;
			mSetForegroundArgs[0] = false;

			try {

				mStopForeground.invoke(this, mStopForegroundArgs);
				mSetForeground.invoke(this, mSetForegroundArgs);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return;
		}
		
		/*
		 *  在 setForeground 之前调用 cancel，因为我们有可能在取消前台服务之后
		 *  的那一瞬间被kill掉。这个时候 notification 便永远不会从通知一栏移除
		 */
		mNM.cancel(id);
	}

}
