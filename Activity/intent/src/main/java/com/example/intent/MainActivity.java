package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Intent 的六大属性
 * ComponentName：组件名，Intent在执行时现在这里查找信息，找不到时才会去其它属性查找
 * action:定义动作，为了区别当前的intent对象进行使用的额，类型是字符串类型
 * category：分类，进一步的筛选到底需要处理的内容
 *
 *
 *
 * 启动一个Activity一般有两种方式，分别是显式启动和隐式启动
 * 显式启动：
 * Intent i=new Intent(当前Activity.this,目标Activity.class)
 *
 * 隐式启动：
 * Intent i=new Intent();
 * 需要在AndroidManifest中配置要启动的Activity的action和category值
 * <category android:name="android.intent.category.DEFAULT"/>跳转Activity的category
 * <category android:name="android.intent.category.LAUNCHER"/>入口Activity的category
 *
 * Activit六大属性之Extra
 * Extra:表示的是额外的内容，用来进行intent的传递数据
 * putExtra（键值对）键是字符串类型
 * putExtras:传递的是一个bundle类型的对象，该对象中可以放置很多数据
 *
 * Activit六大属性之data
 * Data:数据，包含了Uri类型的唯一字段，类似于：tel：// 定义了唯一性的表示前缀
 * 使用action和data进行结合可以实现跳转到系统运用对应的界面
 * data类型是Uri类型，这种类型唯一性的标识一些资源
 * tell://:拨打电话的唯一性的标识前缀
 * 直接拨打电话需要给定权限：android.permission.CALL_PHONE
 *
 * Android中定义的task任务可以将两个运用程序的不同activity界面存在于同一个任务栈中
 * task：可以将不同运用中的界面定义在同一个task当中看起来行云流水般的效果
 *
 *
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
//                Intent i = new Intent(MainActivity.this, NewActivity.class);
//                ComponentName cn=new ComponentName(MainActivity.this,NewActivity.class);
//                Intent i=new Intent();
//                i.setComponent(cn);
//                startActivity(i);
                Intent intent=new Intent();
                //该字符串只是一个名字，可为任意字符串，但为了规范，一般设置为以下
                intent.setAction("com.example.intent.MainActivity.action");
                startActivity(intent);
            }
        });

    }




}
