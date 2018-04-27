package com.example.vip.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * src:ImageView中内容填充的资源
 * backGround：ImageView的背景显示
 * ImageView是View的子类，也可以添加对应的点击监听事件
 * ImageButton：用来响应图片的点击是事件吗，它是ImageView的子类
 * 一般用ImageButton来响应控件的点击效果，使用ImageView来呈现一个图片软件
 * ScaleType是定义在ImageView上用来呈现图片的不同显示
 */
public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private ImageButton plane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= (ImageView) findViewById(R.id.image2);
        plane=(ImageButton) findViewById(R.id.plane);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了黄益凛的图片",Toast.LENGTH_SHORT).show();
            }
        });
        plane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"黄益凛太帅了",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
