package com.example.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 启动运用程序就会开启Activity栈，位于栈顶的activity被用户可见，
 * 每次启动一个activity都会将该activity的实例加入到栈中
 *
 * Activity的启动模式有四种，分别是（AndroidManifest的activity中设置launchMode）
 * Standard：标准的启动模式，如果需要启动一个activity就会创建该activity的实例，也是activity的默认启动模式，
 * 但这样在往自身activity上跳转时，本不需要再创建新的实例，但他仍会自动创建
 *
 * SingleTop：如果启动的activity已经位于栈顶，那么就不会重新创建一个新的activity的实例，而是复用
 * 位于栈顶的实例对象个，如果要启动的activity存在但不位于栈顶，那么启动时也会重新创建
 *
 * SingleTask:设置了singleTask启动模式的activity在启动时，如果位于Activity栈当中，就会复用该
 * activity，同时栈中在该activity顶部的所有activity实例都会出栈销毁，运用在：网页的图集，一键退出当前的运用程序
 *
 * SingleInstance:如果使用了SingleInstance启动模式的activity在启动时候会复用已经存在的activity实例。
 * 不管这个activity的实例位于哪一个运用当中，都会共享已经启动的activity的实例对象，使用SingleInstance
 * 的启动模式的activity会单独的开启一个共享栈，这个栈中只存在当前的activity的实例对象
 *
 */
public class MainActivity extends AppCompatActivity {

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          Log.i("tag","MainActivity调用onCreate方法创建了一个实例");

     }
     public void skip(View view){
          Intent intent=new Intent(this,SecondActivity.class);
          startActivity(intent);
     }

     @Override
     protected void onDestroy() {
          super.onDestroy();
          Log.i("tag","MainActivity调用onDestroy方法销毁了一个实例");
     }
}
