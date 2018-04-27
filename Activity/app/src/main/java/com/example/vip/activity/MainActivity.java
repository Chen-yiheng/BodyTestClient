package com.example.vip.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity:用来管理用户界面的活动
 * 通常一个Activity表示一个页面，可以处理与用户之间的交互工作。
 * 应用程序中有对应的入口Activity，声明配置在AndroidManifest.xml文件中，
 * 优先启动的Activity才需要配置对应的action和category
 *
 * 实现Activity的时候一般继承自Activity，并且实现里面的onCreate方法。
 *
 * startActivity方法：表示启动有一个Activity的页面
 * Intent：意图，用于告知系统需要操作的效果
 *
 * super.onCreate(savedInstanceState);父类方法必须调用，否则报异常
 * 在项目中定义的所有的Activity都需要在AndroidManifest.xml中进行注册，否则会报ActivityNotFoundException
 *另外android中定义的四大组件在使用到的时候都需要在AndroidManifest.xml中注册
 */
public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent);
            }
        });

    }
}
