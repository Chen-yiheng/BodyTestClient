package com.example.handlerthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;

/**
 * android 中提供了真正启动线程的类HandlerThread
 * 然后通过Message从主线程向目的线程发送数据，
 * 最后在目的线程中public void handleMessage(Message msg) 中处理数据
 */
public class MainActivity extends AppCompatActivity {
     private HandlerThread thread;

     Handler handler = new Handler(thread.getLooper()) {
          @Override
          public void handleMessage(Message msg) {
               Log.i("tag", "threadname::" + Thread.currentThread().getName());
               int i = msg.arg1;
               Date date = (Date) msg.obj;
               Bundle bundle = msg.getData();

               Log.i("tag", "接收到的数据:" + i + ",Date:" + date.toString() + ",Bundle:" + bundle
                       .getString("author") + bundle.getInt("age") + bundle.getString("poeme"));
          }
     };



     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          Log.i("tag", "threadname:" + Thread.currentThread().getName());
          thread = new HandlerThread("handler_thread");
          thread.start();
          Message msg = handler.obtainMessage();
          msg.arg1 = 100000;
          msg.obj = new Date();
          Bundle bundle = new Bundle();
          bundle.putString("author", "黄益凛");
          bundle.putInt("age", 21);
          bundle.putString("poeme", "北国风光，千里冰封，万里雪飘");
          msg.setData(bundle);
          msg.sendToTarget();

     }
}
