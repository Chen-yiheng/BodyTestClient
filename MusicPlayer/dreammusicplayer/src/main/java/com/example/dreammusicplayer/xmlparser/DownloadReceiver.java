package com.example.dreammusicplayer.xmlparser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by vip on 2017/4/29.
 */

public class DownloadReceiver extends BroadcastReceiver {
	private static boolean isFinsihed=false;
	private static String fileName;

	@Override
	public void onReceive(Context context, Intent intent) {
		fileName=intent.getStringExtra("fileName");
		isFinsihed=intent.getBooleanExtra("isFinished",false);
		Log.w("tag","DownloadReceiver.fileName:"+fileName);
	}

	public synchronized static void setIsFinsihed(boolean isFinsihed) {
		DownloadReceiver.isFinsihed = isFinsihed;
	}

	public static void setFileName(String fileName) {
		DownloadReceiver.fileName = fileName;
	}

	public synchronized static boolean isFinsihed() {
		return isFinsihed;
	}

	public static String getFileName() {
		return fileName;
	}
}
