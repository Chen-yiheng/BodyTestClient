package com.example.vip;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vip.httpdownloader.R;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
     private EditText downloadAddr, destAddr;
     private ProgressBar progress;
     private TextView progressTv;
     private final String tag = "tag";

     private Handler handler = new Handler() {
          @Override
          public void handleMessage(Message msg) {
               int dwnloadedLength = msg.getData().getInt("dwnloadedLength");
               progress.setProgress(dwnloadedLength);
               float temp = (float) progress.getProgress() / (float) progress.getMax();
               int pro = (int) (temp * 100);
               if (pro == 100) {
                    Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                    progressTv.setVisibility(View.GONE);
               }
               progressTv.setText("已经下载" + pro + "%");
          }
     };


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          downloadAddr = (EditText) findViewById(R.id.downloadAddress);
          destAddr = (EditText) findViewById(R.id.destAddress);
          progress = (ProgressBar) findViewById(R.id.progress);
          progressTv = (TextView) findViewById(R.id.progresstv);
     }

     private boolean isDownloading;
     private  Dawnload dawnload;

     public void onDownload(View view) {
          Button button= (Button) view;
          if (!isDownloading) {
               isDownloading=true;
               progress.setVisibility(View.VISIBLE);
               progressTv.setVisibility(View.VISIBLE);
               button.setText("取消");
               dawnload=new Dawnload(6);
               dawnload.start();

          }else{
               isDownloading=false;
               button.setText("下载");
               dawnload.finish();
          }


     }

     class Dawnload extends Thread {
          private int threadNumber;
          private int blockSize;
          private int dwnloadedLength;
          private  ThreadDownloader[] threadDownloaders;

          public Dawnload(int threadNumber) {
               this.threadNumber = threadNumber;
          }


          public void finish(){
               for(int i=0;i<threadNumber;i++){
                    if(threadDownloaders[i]!=null){
                         threadDownloaders[i].destroy();
                    }
               }
              this.destroy();
          }

          @Override
          public void run() {
               threadDownloaders = new ThreadDownloader[threadNumber];
               try {
                    URL url = new URL(downloadAddr.getText().toString());
                    URLConnection connection = url.openConnection();
                    int length = connection.getContentLength();
                    if (0 >= length) {
                         Toast.makeText(MainActivity.this, "下载地址有误", Toast.LENGTH_SHORT).show();
                         return;
                    }
                    Log.i(tag, "数据的长度：" + length);
                    progress.setMax(length);
                    blockSize = (length % threadNumber) == 0 ? length / threadNumber : length /
                            threadNumber + 1;
                    String filePath = Environment.getExternalStorageDirectory() + "/MyDownload/";
                    File destFile = new File(filePath);
                    if (!destFile.exists()) {
                         destFile.mkdirs();
                    }
                    destFile = new File(filePath + destAddr.getText().toString());
                    for (int i = 0; i < threadNumber; i++) {
                         threadDownloaders[i] = new ThreadDownloader(downloadAddr.getText()
                                 .toString(), destFile, blockSize, i + 1);
                         threadDownloaders[i].setName("thread" + i);
                         threadDownloaders[i].start();
                         Log.i(tag, "启动线程" + "thread" + i);
                    }
                    boolean isFinshed = false;
                    while (!isFinshed) {
                         dwnloadedLength = 0;
                         for (int i = 0; i < threadNumber; i++) {
                              isFinshed = true;
                              dwnloadedLength += threadDownloaders[i].length();
                              Log.i(tag, threadDownloaders[i].getName() + "已下载的长度：" +
                                      threadDownloaders[i].length());
                              if (!threadDownloaders[i].isCompleted()) {
                                   isFinshed = false;
                              }
                         }
                         Message message = handler.obtainMessage();
                         Bundle data = new Bundle();
                         data.putInt("dwnloadedLength", dwnloadedLength);
                         message.setData(data);
                         message.sendToTarget();
                         Thread.sleep(1000);
                    }

               } catch (MalformedURLException e) {
                    e.printStackTrace();
               } catch (IOException e) {
                    e.printStackTrace();
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }finally {
               }

          }
     }
}
