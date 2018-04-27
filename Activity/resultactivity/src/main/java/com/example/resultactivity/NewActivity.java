package com.example.resultactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * 实现按下按键便关闭该Activity，并返回值给调用该Activity的Activity对象
 * 修改Activity显示的标题：
 * 1.修改AndroidManifest.xml文件中的label标签
 * 2.setTitle("标题")
 * 去掉标题的方法；
 * 1.getSupportActionBar().hide();
 * 去掉状态栏的方法：
 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
 .LayoutParams.FLAG_FULLSCREEN);
 *
 */

public class NewActivity extends AppCompatActivity {
    private Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.layout);
        btn = (Button) findViewById(R.id.btn2);
        setTitle("我爱你");
        getSupportActionBar().hide();//隐藏标题栏
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("name", "我爱你，亲爱的陌生人");
                setResult(RESULT_OK, i);
                finish();
            }
        });
        Log.i("tag","resultactivity运用中的NewActivity被创建");

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag", "resultactivity运用中的NewActivity已被销毁");
    }
}
