package com.example.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Activity的生命周期：
 * onCreate：创建Activity的实例对象的方法
 * onStart：启动当前的Activity实例的方法。
 * onResume：如果该方法执行，页面的实例就可以和用户进行交互
 * onPause：如果该方法执行，页面就和用户不在交互，失去焦点，页面仍旧可见。
 * onStop：页面和用户完全不可见
 * onDestroy：销毁当前的Activity的页面实例
 * onRestart：将置于后台的应用程序的Activity重新显示在前台的时候被调用
 * 一般会连续调用onStrart-->onResume
 * <p>
 * Activity的生命周期方法都是成对出现的，
 * onCreate-->onDestroy,onStart-->onStop,onResume-->onPause
 * 因为onResume和onPause方法会比其他的生命周期方法执行的更为频繁，所以一般在这两个方法中写的代码较少
 * <p>
 * Activity的协同作用：
 * 需要分清的是两个页面之间进行交互，如果存在A页面点击转到B页面，首先A要执行onPause方法，失去焦点。
 * 然后B需要运行，依次执行onCreate-->onStart-->onResume方法。A继续执行onStop方法运行到后台。
 * <p>
 * Task:一个由当执行某个共同任务时与用户产生交互的多个Activity组成的集合
 * Back Stack：这些Activity按顺序放在一个栈中，这个栈就叫做“Back stack”，即“返回栈”，也叫作Activity
 * 栈，它其实就是一种后进先出的栈结构
 */
public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                startActivity(i);
            }
        });

        Log.i("tag", "Oncreate方法被执行");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("tag", "onStart方法被执行");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("tag", "onResume方法被执行");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("tag", "onPause方法被执行");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag", "onStop方法被执行");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag", "onDestroy方法被执行");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("tag", "onRestart方法被执行");
    }
}
