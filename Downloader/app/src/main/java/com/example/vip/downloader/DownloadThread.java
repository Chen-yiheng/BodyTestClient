package com.example.vip.downloader;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vip on 2017/4/5.
 *
 *
 *
 */

public class DownloadThread extends Thread {
	private FileDownloader downloader;
	private URL downUrl;
	private File destFile;
	private int threadID;
	private int blockSize;
	private int downloadedLength;
	private boolean isFinish;
	private final String TAG="tag";

	public DownloadThread(FileDownloader downloader, URL url, File destFile, int threadID, int
		   blockSize, int downloadedLength) {
		this.downloader = downloader;
		this.downUrl = url;
		this.destFile = destFile;
		this.threadID = threadID;
		this.blockSize = blockSize;
		this.downloadedLength = downloadedLength;
	}

	@Override
	public void run() {
		BufferedInputStream bis=null;
		RandomAccessFile raf=null;
		try {
			HttpURLConnection connection = (HttpURLConnection) downUrl.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, " +
				   "image/pjpeg, application/x-shockwave-flash, application/xaml+xml, " +
				   "application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
				   "application/x-ms-application, application/vnd.ms-excel, application/vnd" +
				   ".ms-powerpoint, application/msword, */*");
			connection.setRequestProperty("Accept-Language","zh-CN");
			connection.setRequestProperty("Referer",downUrl.toString());
			connection.setRequestProperty("Charset","UTF-8");
			int startPos=blockSize*(threadID-1)+downloadedLength;
			int endPos=blockSize*threadID-1;
			connection.setRequestProperty("Range","bytes="+startPos+"-"+endPos);
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; " +
				   "Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET " +
				   "CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
			connection.setRequestProperty("Connection", "Keep-Alive");
			bis=new BufferedInputStream(connection.getInputStream());
			raf=new RandomAccessFile(destFile,"rwd");
			raf.seek(startPos);
			byte[]buffer=new byte[1024];
			int len=0;
			while (-1!=(len=bis.read(buffer,0,1024))){
				raf.write(buffer,0,len);
				downloadedLength+=len;
				downloader.update(threadID,downloadedLength);
				downloader.append(len);
			}
			isFinish=true;
			Log.i(TAG,"完成下载");
		} catch (IOException e) {
			downloadedLength=-1;
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

	public boolean isFinish() {
		return isFinish;
	}

	public int getDownloadedLength() {
		return downloadedLength;
	}
}
