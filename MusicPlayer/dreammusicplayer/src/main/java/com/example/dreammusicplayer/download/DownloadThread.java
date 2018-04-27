package com.example.dreammusicplayer.download;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *下载线程
 */

public class DownloadThread extends Thread{
	private URL downloadUrl;
	private File destFile;
	private int threadID;
	private int blockSize;
	private int downloadedLength;
	private boolean isFinished;

	public DownloadThread(URL downloadUrl, File destFile, int threadID, int blockSize) {
		this.downloadUrl = downloadUrl;
		this.destFile = destFile;
		this.threadID = threadID;
		this.blockSize = blockSize;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public int getDownloadedLength() {
		return downloadedLength;
	}

	@Override
	public void run() {
		RandomAccessFile raf=null;
		BufferedInputStream bis=null;
		try {
			HttpURLConnection connection= (HttpURLConnection) downloadUrl.openConnection();
//			connection.setConnectTimeout(5000);
			Log.w("tag","connection:"+connection);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Charset","UTF-8");
			int startPos=blockSize*(threadID-1);
			int endPos=blockSize*threadID-1;
			connection.setRequestProperty("Range","bytes="+startPos+"-"+endPos);
			raf=new RandomAccessFile(destFile,"rwd");
			raf.seek(startPos);
			bis= new BufferedInputStream(connection.getInputStream());
			byte[]info=new byte[1024];
			int len=0;
			while ((len=bis.read(info))!=-1){
				raf.write(info,0,len);
				downloadedLength+=len;
			}
			isFinished=true;
			Log.w("tag","isFinished=true;"+connection);
		} catch (IOException e) {
			downloadedLength=-1;
			e.printStackTrace();
		}finally {
			try {
				if(null!=raf){
					raf.close();
				}
				if(null!=bis){
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
