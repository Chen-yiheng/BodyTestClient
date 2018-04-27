package com.example.vip.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * GridView网格布局也可以使用adapter适配
 * numColumns:定义呈现的列数
 */
public class MainActivity extends AppCompatActivity {
    private GridView grid;
    private MyAdapter adapter;
    private int[] images = {R.drawable.book, R.drawable.browser, R.drawable.call, R.drawable.camera,
            R.drawable.download, R.drawable.escape, R.drawable.football, R.drawable.housekeeper,
            R.drawable.music, R.drawable.news, R.drawable.setting, R.drawable.shutdown, R
            .drawable.taobao, R.drawable.tool};
    private String[] names = {"书架", "浏览器", "call", "照相机", "下载", "越狱", "足球", "手机管家", "音乐", "新闻",
            "设置", "关机", "淘宝", "工具"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (GridView) findViewById(R.id.grid);
        adapter = new MyAdapter(this, images, names);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "点击了" + adapter.getItem(position), Toast
                        .LENGTH_SHORT).show();
            }
        });
    }
}
