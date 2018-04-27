package com.example.dreammusicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;


import com.example.dreammusicplayer.Utils.Constant;

import java.io.IOException;
import java.util.List;


/**
 * 音乐播放服务,广播接收器可以在AndroidManifest中注册，也可以用代码动态注册，
 * 静态注册时，接收类如果是内部类则必须是静态的，
 * 动态注册时，则可以将接收类写成匿名内部类，这样可以私有化，其内部调用的函数也不必要全部都是静态的
 */

public class MusicPlayerService extends Service {
	public MediaPlayer player;
	public static final String INTENT_MUSIC_PATH = "SRC_PATH";
	public static final String INTENT_COMMENT = "COMMENT";
	public static final String INTENT_LIST = "LIST";
	public static final int PLAY_CODE = 1;
	public static final int NEXT_CODE = 2;
	public static final int PREVIOUS_CODE = 3;
	public static final int CLOSE_CODE = 4;
	public static final String NOTIFICATION_FLAG = "flag";
	public static final int NOTIFICATION_ID = 8;

	public List<String> musicList;
	private MusicBinder binder;
	public int playingMusicIndex;
	private RemoteViews remoteViews;
	private NotificationCompat.Builder builder;
	private int playImageId;


	public class MusicBinder extends Binder {
		public MusicPlayerService getService() {
			return MusicPlayerService.this;
		}
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("tag", "onBind");
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		player = new MediaPlayer();
		playImageId = R.drawable.pausing;
		binder = new MusicBinder();
		player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				playingMusicIndex++;
				play();
			}
		});
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.NotificationReceiver");
		registerReceiver(notificationReceiver, filter);
		Log.w("tag", "onCreate");
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		switch (intent.getIntExtra(INTENT_COMMENT, 0)) {
			case Constant.PLAY_COMMENT:
				playingMusicIndex = intent.getIntExtra(INTENT_MUSIC_PATH, 0);
				play();
				break;
			case Constant.PAUSE_COMMENT:
				playOrPause();
				break;
			case Constant.UPDATE_COMMENT:
				musicList = intent.getStringArrayListExtra(INTENT_LIST);
				break;
		}
		notificationCreate(this);
		Log.w("tag", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	public void notificationCreate(Context context) {
		PendingIntent activityIntent = PendingIntent.getActivity(context, 0, new Intent(context,
			   MainActivity.class), 0);
		remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_service);
		remoteViews.setImageViewResource(R.id.image, R.drawable.music);
		remoteViews.setImageViewResource(R.id.notification_play, playImageId);
		remoteViews.setTextViewText(R.id.notification_content, musicList.get(playingMusicIndex));
		onClikNotificationLitener(context, R.id.notification_play, PLAY_CODE);
		onClikNotificationLitener(context, R.id.notification_next, NEXT_CODE);
		onClikNotificationLitener(context, R.id.notification_previous, PREVIOUS_CODE);
		onClikNotificationLitener(context, R.id.notification_close, CLOSE_CODE);
		builder = new NotificationCompat.Builder(context);
		//必须存在setSmallIcon，否则通知无法显现
		builder.setSmallIcon(R.drawable.music).setContentTitle("").setContentText("");
		builder.setTicker(musicList.get(playingMusicIndex)).setContent(remoteViews)
			   .setContentIntent(activityIntent);
		Notification notification = builder.build();
		notification.flags = Notification.FLAG_NO_CLEAR;
//		notification.flags |= Notification.FLAG_ONGOING_EVENT;
//		notification.flags |= Notification.FLAG_INSISTENT;
//		notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
//		开启前台服务
		startForeground(NOTIFICATION_ID,notification);
		Log.w("tag", "notificationCreate");
	}

	private void onClikNotificationLitener(Context context, int source, int requestcode) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.NotificationReceiver");
		intent.putExtra(NOTIFICATION_FLAG, requestcode);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestcode, intent,
			   0);
		remoteViews.setOnClickPendingIntent(source, pendingIntent);
		Log.i("tag", "onClikNotificationLitener");
	}

	private final BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			switch (intent.getIntExtra(MusicPlayerService.NOTIFICATION_FLAG, 0)) {
				case PLAY_CODE:
					playOrPause();
					break;
				case CLOSE_CODE:
					Log.w("tag", "onReceive:CLOSE_CODE");
					MainActivity.finsh = true;
					LrcActivity.finsh = true;
					stopForeground(true);
					context.stopService(new Intent(context, MusicPlayerService.class));
					return;
				case NEXT_CODE:
					playingMusicIndex++;
					play();
					break;
				case PREVIOUS_CODE:
					playingMusicIndex--;
					play();
					break;
			}
			notificationCreate(context);
			Log.w("tag", "onReceive");
		}
	};

	protected void playOrPause() {
		if (null == player) {
			return;
		}
		if (player.isPlaying()) {
			player.pause();
			playImageId = R.drawable.pausing;
		} else {
			player.start();
			playImageId = R.drawable.playing;
		}
		Log.w("tag", "playOrPause");
	}

	protected void play() {
		try {
			player.seekTo(0);
			player.reset();
			checkPlayingIndex();
			player.setDataSource(Constant.SAVE_PATH + musicList.get(playingMusicIndex));
			Log.i("tag", musicList.get(playingMusicIndex));
			player.prepare();
			player.start();
			playImageId = R.drawable.playing;
			Log.w("tag", "play");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkPlayingIndex() {
		if (playingMusicIndex < 0) {
			playingMusicIndex = musicList.size() - 1;
		}
		if (playingMusicIndex >= musicList.size()) {
			playingMusicIndex = 0;
		}
	}

	@Override
	public void onDestroy() {
		Log.w("tag", "service onDestroy");
		super.onDestroy();
		player.stop();
		player.release();
		stopForeground(true);
	}


/*	public static class NotificationReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			switch (intent.getIntExtra(MusicPlayerService.NOTIFICATION_FLAG, 0)) {
				case PLAY_CODE:
					playOrPause();
					break;
				case CLOSE_CODE:
					Log.w("tag", "onReceive:CLOSE_CODE");
					MainActivity.finsh = true;
					LrcActivity.finsh=true;
					manager.cancel(NOTIFICATION_ID);
					context.stopService(new Intent(context, MusicPlayerService.class));
					return;
				case NEXT_CODE:
					playingMusicIndex++;
					play();
					break;
				case PREVIOUS_CODE:
					playingMusicIndex--;
					play();
					break;
			}
			notificationCreate(context);
			Log.w("tag", "onReceive");
		}
	}*/

}


