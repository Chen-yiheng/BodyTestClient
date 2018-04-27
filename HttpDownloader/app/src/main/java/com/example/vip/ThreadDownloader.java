package com.example.vip;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 线程下载类
 */



class ThreadDownloader extends Thread {

     private int threadID;
     private int blockSize;
     private boolean isCompleted;
     private String srcUrl;
     private File destFile;
     private int downloadingLength;

     public ThreadDownloader() {
     }

     public ThreadDownloader(String srcUrl, File destFile, int blockSize, int threadID) {
          this.blockSize = blockSize;
          this.threadID = threadID;
          this.srcUrl = srcUrl;
          this.destFile = destFile;
     }

     public ThreadDownloader(String srcUrl, String destUri, int blockSize, int threadID) {
          this(srcUrl, new File(destUri), blockSize, threadID);
     }

     public ThreadDownloader(String srcUrl, File destFile, int blockSize) {
          this(srcUrl, destFile, blockSize, 0);

     }

     public ThreadDownloader(String srcUrl, String destUri, int blockSize) {
          this(srcUrl, new File(destUri), blockSize, 0);
     }

     @Override
     public void run() {
          BufferedInputStream bis = null;
          URLConnection connection = null;
          RandomAccessFile raf = null;
          try {
               URL url = new URL(srcUrl);
               connection = url.openConnection();
               connection.setAllowUserInteraction(true);
               int startPos = blockSize * (threadID - 1);
               int endPos = blockSize * threadID - 1;
               connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
               Log.i("tag",Thread.currentThread().getName() + "  bytes=" + startPos + "-" + endPos);
               bis = new BufferedInputStream(connection.getInputStream());
               raf = new RandomAccessFile(destFile, "rwd");
               raf.seek(startPos);
               int len = 0;
               byte[] buffer = new byte[1024];
               while (-1 != (len = bis.read(buffer,0,1024))) {
                    raf.write(buffer, 0, len);
                    downloadingLength += len;
               }
               isCompleted = true;
               Log.d("tag", "current thread task has finished,all size:"
                       + downloadingLength);

          } catch (MalformedURLException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          } finally {
               try {
                    if(null!=raf){
                         raf.close();
                    }

                    if (null != bis) {
                         bis.close();
                    }
               } catch (IOException e) {
                    e.printStackTrace();
               }
          }

     }

     public boolean isCompleted() {
          return isCompleted;
     }

     public int length() {
          return downloadingLength;
     }

}


