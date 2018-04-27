package com.example.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by vip on 2017/3/24.    `
 */
//
public class SecondActivity extends AppCompatActivity {
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_second);
          Log.i("tag","SecondActivity调用onCreate方法创建了一个实例");

     }
     public void skipReturn(View view){
          Intent intent=new Intent();
          intent.setAction("com.example.resultactivity.NewActivity");
          startActivity(intent);
     }

     @Override
     protected void onDestroy() {
          super.onDestroy();
          Log.i("tag","SecondActivity调用onDestroy方法销毁了一个实例");
     }
}
