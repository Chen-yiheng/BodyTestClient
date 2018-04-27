package com.example.vip.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * handler在启动线程时是将线程不断地加入队列中，然后按照顺序逐一执行
 * handler.post(print),直接将print线程加入队列中
 * handler.postDelayed(print,3000);延时3000ms后再将线程加入队列中
 *
 */
public class MainActivity extends AppCompatActivity {
     private Button start,end;
     private Handler handler=new Handler();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          start= (Button) findViewById(R.id.start);
          end= (Button) findViewById(R.id.end);
          start.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    handler.post(print);
               }
          });
          end.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    handler.removeCallbacks(print);
               }
          });
     }

     Runnable print=new Runnable() {
          int i=0;
          @Override
          public void run() {
               Log.i("tag","乘风破浪会有时，直挂云帆济沧海"+i++);
               handler.postDelayed(print,3000);
          }
     };
}
