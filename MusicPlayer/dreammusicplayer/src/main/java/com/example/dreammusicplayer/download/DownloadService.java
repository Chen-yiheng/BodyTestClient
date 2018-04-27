package com.example.dreammusicplayer.download;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dreammusicplayer.Utils.Constant;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vip on 2017/4/28.
 */

public class DownloadService extends Service {
	private URL url;
	private Downloader downloader;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			boolean isFinished = bundle.getBoolean("isFinished");
			if (isFinished == true) {
				Log.w("tag","开始发送广播");
				Intent intent = new Intent();
				intent.setAction("android.intent.action.DownloadReceiver");
				intent.putExtra("isFinished", isFinished);
				Log.i("tag", "DownloadService.fileName:" + downloader.getFileName());
				intent.putExtra("fileName", downloader.getFileName());
				DownloadService.this.sendBroadcast(intent);
			}
		}
	};


	@Override
	public void onCreate() {
		Log.i("tag", "创建Service");
		super.onCreate();
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.w("tag", "进入onStartCommand");
		try {
			url = new URL(intent.getStringExtra("url"));
			Log.w("tag", "url:" + url.toString());
			downloader = new Downloader(url, Constant.SAVE_PATH, handler);
			downloader.start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return super.onStartCommand(intent, flags, startId);
	}


}
