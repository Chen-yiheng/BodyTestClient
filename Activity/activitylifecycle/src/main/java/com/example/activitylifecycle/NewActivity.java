package com.example.activitylifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by vip on 2017/3/12.
 */
//
public class NewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Log.i("tag","NewActivity的Oncreate方法被执行");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("tag","NewActivity的onStart方法被执行");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("tag","NewActivity的onResume方法被执行");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("tag","NewActivity的onPause方法被执行");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag","NewActivity的onStop方法被执行");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag","NewActivity的onDestroy方法被执行");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("tag","NewActivity的onRestart方法被执行");
    }
}