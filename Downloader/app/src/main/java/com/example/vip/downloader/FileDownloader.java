package com.example.vip.downloader;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by vip on 2017/4/5.
 */

public class  FileDownloader extends Thread {
	final String TAG = "tag";
	private Context context;
	private String downUrl;
	private File destFile;
	private int allDownloadedLength;
	private DownloadThread[] theads;
	private int threadSize;
	private FileService fileService;
	private int blockSize;
	private Map<Integer, Integer> data;
	private boolean isFinish;
	private int downFileLength;
	private Handler handler;
	private  File destDirectory;

	public boolean isFinish() {
		return isFinish;
	}

	public int downloadLength() {
		return downFileLength;
	}

	public FileDownloader(Context context, File destDirectory, String downUrl, int threadSize,
					  Handler handler) {
		this.downUrl = downUrl;
		this.threadSize = threadSize;
		this.handler = handler;
		this.destDirectory=destDirectory;
		fileService = new FileService(context);
		theads = new DownloadThread[threadSize];
		data = new HashMap<>();

		if (!destDirectory.exists()) {
			destDirectory.mkdirs();
		}

	}

	public int getDownFileLength() {
		try {
			Thread.sleep(2000);
			Log.i(TAG,this.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return downFileLength;
	}

	private void downloadInit() throws IOException {

			URL url = new URL(downUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			this.downFileLength = connection.getContentLength();
			Log.i(TAG, "文件长度为" + downFileLength);
			if (downFileLength <= 0) {
				Log.i(TAG, "读取文件失败");
			}
			String fileName = getFileName(connection);
			this.destFile = new File(destDirectory, fileName);
			Map<Integer, Integer> map = fileService.getData(downUrl);
			if (map.size() > 0) {
				for (Integer temp : map.keySet()) {
					data.put(temp, map.get(temp));
				}
			}

			if (data.size() == theads.length) {
				for (Integer key : data.keySet()) {
					allDownloadedLength += data.get(key);
				}
			}
			blockSize = downFileLength % threadSize == 0 ? downFileLength / threadSize :
				   downFileLength / threadSize + 1;

		Log.i(TAG, "FileDownloader完成初始化blockSize="+blockSize+",threadSize"+threadSize);
	}


	@Override
	public void run() {
		try {
			downloadInit();
			Log.i(TAG,"进入总下载线程");
//			RandomAccessFile raf = new RandomAccessFile(destFile, "rwd");
//			raf.setLength(downFileLength);
//			raf.close();
			if (data.size() != threadSize) {
				data.clear();
				for (int i = 0; i < threadSize; i++) {
					data.put(i + 1, 0);
				}
				Log.i(TAG,"清除data");
			}
			for (int i = 0; i < threadSize; i++) {
				int downLength = data.get(i + 1);
				if (downLength < blockSize && allDownloadedLength < downFileLength) {
					theads[i] = new DownloadThread(this, new URL(downUrl), destFile, i + 1,
						   blockSize, downLength);
//					theads[i].setPriority(7);
					theads[i].start();
					Log.i(TAG, "开启线程" + theads[i].getName());
				} else {
					Log.i(TAG,"theads为空");
					theads[i] = null;
				}
			}
			fileService.save(downUrl, data);
			while (!isFinish) {
				isFinish = true;
				for (int i = 0; i < theads.length; i++) {
					if (theads[i] != null && !theads[i].isFinish()) {
						isFinish = false;
						if (theads[i].getDownloadedLength() == -1) {
							theads[i] = new DownloadThread(this, new URL(downUrl),
								   destFile, i + 1, blockSize, data.get(i + 1));
							theads[i].start();
						}
						Log.i(TAG,theads[i].getName()+"已下载的长度为："+theads[i].getDownloadedLength());
					}

				}
				if (handler != null) {
					Message message = handler.obtainMessage();
					message.arg1 = allDownloadedLength;
					message.sendToTarget();
				}
				Log.i(TAG,"监测"+allDownloadedLength);
				Thread.sleep(1000);
			}
			fileService.delete(downUrl);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public String getFileName(HttpURLConnection connection) {
		String fileName = downUrl.substring(downUrl.lastIndexOf('/') + 1);
		if (fileName == null || "".equals(fileName.trim())) {
			for (int i = 0; ; i++) {
				String field = connection.getHeaderField(i);
				Log.i(TAG, "要下载文件的field：" + field);
				if (null == field) {
					break;
				}
				if ("content-disposition".equals(connection.getHeaderFieldKey(i).toLowerCase()
				)) {
					Matcher matcher = Pattern.compile(".*filename=(.*)").matcher(field
						   .toLowerCase());
					if (matcher.find()) {
						return matcher.group(1);
					}
				}
			}
			fileName = UUID.randomUUID() + ".tmp";
		}
		Log.i(TAG, "获取下载文件的名称：" + fileName);
		return fileName;
	}

	public int getAllDownloadedLength() {
		return allDownloadedLength;
	}

	public int getThreadSize() {
		return threadSize;
	}

	public synchronized void append(int size) {
		allDownloadedLength += size;
	}

	public synchronized void update(int threadID, int downLength) {
		data.put(threadID, downLength);
		fileService.update(downUrl, data);
	}
}
