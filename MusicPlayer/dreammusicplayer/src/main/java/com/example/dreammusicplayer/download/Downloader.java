package com.example.dreammusicplayer.download;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;


/**
 * Created by vip on 2017/4/25.
 */

public class Downloader extends Thread {
	private DownloadThread[] downloadThreads;
	private int threadSize = 3;
	private int blockSize;
	private URL srcUrl;
	private File directory;
	private File destFile;
	private boolean isFinishedAll;
	private int downloadedLength;
	private int downFileLength;
	private Handler handler;
	private Context context;
	private String fileName;

	public File getDestFile() {
		return destFile;
	}


	public Downloader(URL srcUrl, String directory, Handler handler) {
		this.srcUrl = srcUrl;
		this.directory = new File(directory);
		this.handler = handler;
	}

	public Downloader(URL srcUrl) {
		this.srcUrl = srcUrl;
	}

	public boolean isFinishedAll() {
		return isFinishedAll;
	}

	public void setThreadSize(int threadSize) {
		this.threadSize = threadSize;
	}

	public int getDownloadedLength() {
		return downloadedLength;
	}

	public int getDownFileLength() {
		return downFileLength;
	}


	@Override
	public void run() {
		try {
			Log.w("tag", "downFileLength:" + downFileLength);
			fileName = createFileName(null);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			destFile = new File(directory, fileName);
			String url = srcUrl.toString().substring(0, srcUrl.toString().lastIndexOf('/'));
			Log.w("tag", "path:" + url);
			String file = srcUrl.toString().substring(srcUrl.toString().lastIndexOf('/') + 1);
			Log.w("tag", "path:" + file);
			//中文和含空格文件名需要编码，这样服务器才能接受访问
			file = URLEncoder.encode(file, "UTF-8");
			file = file.replaceAll("\\+", "%20");
			srcUrl = new URL(url + "/" + file);

			HttpURLConnection connection = (HttpURLConnection) srcUrl.openConnection();
//			connection.setConnectTimeout(5000);
			downFileLength = connection.getContentLength();


			blockSize = downFileLength % threadSize == 0 ? downFileLength / threadSize :
				   downFileLength / threadSize + 1;
			downloadThreads = new DownloadThread[threadSize];
			RandomAccessFile raf = new RandomAccessFile(destFile, "rwd");
//			raf.setLength(downFileLength);
			raf.close();
			for (int i = 0; i < threadSize; i++) {
				downloadThreads[i] = new DownloadThread(srcUrl, destFile, i + 1, blockSize);
				downloadThreads[i].start();
			}
			boolean isFinished = false;
			Bundle bundle = new Bundle();
			while (!isFinished) {
				isFinished = true;
				downloadedLength = 0;
				for (int i = 0; i < threadSize; i++) {
					if (downloadThreads[i].getDownloadedLength() == -1) {
						downloadedLength += downloadThreads[i].getDownloadedLength();
					}
					if (downloadThreads[i] != null && !downloadThreads[i].isFinished()) {
						isFinishedAll = false;
						if (downloadThreads[i].getDownloadedLength() == -1) {
							downloadThreads[i] = new DownloadThread(srcUrl, destFile, i +
								   1, blockSize);
							downloadThreads[i].start();
						}
					}

				}
				if (null != handler) {
					Message message = handler.obtainMessage();
					message.arg1 = downloadedLength;
					message.arg2 = downFileLength;
					bundle.putBoolean("isFinished", isFinished);
					message.setData(bundle);
					message.sendToTarget();
				}
				Log.w("tag", "downloadedLength:" + downloadedLength);
			}
			isFinishedAll = true;
			Log.w("tag", "下载完成");

		} catch (IOException e) {
		}

	}

	public String createFileName(HttpURLConnection connection) {
		String fileName = srcUrl.toString().substring(srcUrl.toString().lastIndexOf('/') + 1);
		if (fileName == null || "".equals(fileName.trim())) {
//			for (int i = 0; ; i++) {
//				String field = connection.getHeaderField(i);
//				if (null == field) {
//					break;
//				}
//				if ("content-disposition".equals(connection.getHeaderFieldKey(i).toLowerCase()
//				)) {
//					Matcher matcher = Pattern.compile(".*filename=(.*)").matcher(field
//						   .toLowerCase());
//					if (matcher.find()) {
//						return matcher.group(1);
//					}
//				}
//
//			}
			fileName = UUID.randomUUID() + ".tmd";

		}
		return fileName;
	}

	public String getFileName() {
		return fileName;
	}
}
