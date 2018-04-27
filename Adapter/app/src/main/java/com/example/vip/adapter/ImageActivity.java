package com.example.vip.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleAdapter:可以匹配多控件
 */

public class ImageActivity extends AppCompatActivity {
    private ListView lv;
    private List<Map<String, Object>> list;
    private SimpleAdapter adapter;
    private String[] name = {"照相机", "音乐", "设置", "浏览器", "下载", "新闻", "书架", "工具", "关机"};
    private String[] introduce = {"用于拍照和拍视频", "用于听音乐", "用于设置各种选项", "用于浏览网页", "下载软件、文件等",
            "查看最新新闻", "看小说和找小说", "各种工具汇集之所", "只能说拜拜了"};
    private int[] images = {R.drawable.camera, R.drawable.music, R.drawable.setting,
            R.drawable.browser, R.drawable.download, R.drawable.news,
            R.drawable.book, R.drawable.tool, R.drawable.shutdown};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("images", images[i]);
            map.put("names", name[i]);
            map.put("introduces", introduce[i]);
            list.add(map);
        }
        String[] from = {"images", "names", "introduces"};
        int[] to = {R.id.images2, R.id.image_name, R.id.introduce};
        adapter = new SimpleAdapter(this, list, R.layout.image_adpter_item, from, to);
        lv.setAdapter(adapter);

    }
}
