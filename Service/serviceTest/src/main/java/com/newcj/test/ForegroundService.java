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
		 * ���ǲ�����ҪΪ notification.flags ���� FLAG_ONGOING_EVENT����Ϊ
		 * ǰ̨����� notification.flags ����Ĭ�ϰ������Ǹ���־λ
		 */
		Notification.Builder builder = new Notification.Builder(this);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
			   new Intent(this, Main.class), 0);
		builder.setContentIntent(contentIntent);
		builder.setSmallIcon(R.drawable.icon);
		builder.setTicker("Foreground Service Start");
		builder.setContentTitle("ǰ̨����");
		builder.setContentText("�Ұ��㣬������.");
		Notification notification = builder.build();


	   /*
	    * ע��ʹ��  startForegroundCompat ��id Ϊ 0 ��������ʾ notification
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
	 * �Լ����Է�ʽ��ʼǰ̨����
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
	 * �Լ����Է�ʽֹͣǰ̨����
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
		 *  �� setForeground ֮ǰ���� cancel����Ϊ�����п�����ȡ��ǰ̨����֮��
		 *  ����һ˲�䱻kill�������ʱ�� notification ����Զ�����֪ͨһ���Ƴ�
		 */
		mNM.cancel(id);
	}

}
