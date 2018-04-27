package com.example.resultactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * 带有返回值得Activity的跳转
 */
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 10;
    private Button btn;
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏

        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv3);
        setTitle("我很爱你");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                //参数一：请求intent对象 参数二：requestCode请求码，用来标志该Activity的请求
                startActivityForResult(i, REQUEST_CODE);
            }
        });
        Log.i("tag","resultactivity运用中的MainActivity被创建");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag","resultactivity运用中的mainActivity被销毁");
    }

    /**
     * 如果需要处理返回值，需要重写一个方法
     * requestCode和resultCode是用来区分哪一个页面使用带有返回值进行跳转而来的，
     * 并且返回值又是给定哪一个页面的
     * data，返回值时携带的intent对象
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("tag","已接收到数值onActivityResult");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            tv.setText(data.getStringExtra("name"));
        }

    }
//


}
