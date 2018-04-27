package com.example.vip.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.File;

/**
 *
 * vibrate
 * 这个属性。它是一个长整型的数组，用于设置手机静止和振动的时长，以毫秒为单位。
 * 下标为 0 的值表示手机静止的时长，下标为 1 的值表示手机振动的时长，
 * 下标为 2 的值又表示手机静止的时长，以此类推
 */
public class MainActivity extends AppCompatActivity {
	private NotificationManager manager;
	private Notification notification;
	private Button showNotification;
	private boolean isShow;

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "已收到", Toast.LENGTH_SHORT).show();

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showNotification = (Button) findViewById(R.id.notification);
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isShow) {
					createNotification();
					createMyNotification();
					isShow = true;
				} else {
					manager.cancel(1);
					manager.cancel(2);
					isShow = false;
				}
			}
		});
		IntentFilter filter = new IntentFilter("android.notification.broadcast");
		registerReceiver(receiver, filter);
	}

	private void createNotification() {
		Notification.Builder builder = new Notification.Builder(this);
		builder.setSmallIcon(R.mipmap.ic_launcher).setTicker("狼来了，狼来了，狼来了.......");
		builder.setContentTitle("天下大同").setContentText("革命尚未成功，同志仍需努力");
		Uri soundFile = Uri.fromFile(new File(Environment.getExternalStorageDirectory()
			   .getAbsolutePath() + "/mp3/蔡俊涛-一点通.mp3"));
		builder.setSound(soundFile);
		long[] vibrates = {0, 1000, 1000, 1000, 1000, 1000};
		builder.setVibrate(vibrates);
		builder.setLights(Color.RED, 1000, 1000);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
			   MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
		builder.setContentIntent(pendingIntent);
		notification = builder.build();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		//notification.defaults=Notification.DEFAULT_ALL;//全部设为默认
		manager.notify(1, notification);
	}

	private void createMyNotification() {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.mipmap.ic_launcher).setTicker("宁可我负天下人，勿另天下人负我");
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, new Intent(this,
			   MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
		builder.setContentIntent(pendingIntent2);
		RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_main);
		remoteViews.setImageViewResource(R.id.image2, R.drawable.playing);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("android" +
			   ".notification.broadcast"), PendingIntent.FLAG_NO_CREATE);
		remoteViews.setOnClickPendingIntent(R.id.image2, pendingIntent);
		builder.setContent(remoteViews);
		Notification notification = builder.build();
		notification.flags = Notification.FLAG_NO_CLEAR;
		manager.notify(2, notification);


	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}
