package com.example.vip.listadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 给listView添加头尾显示
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("坚持，静心" + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        addByXml();
//        addByObject();
        lv.setAdapter(adapter);
    }

    private void addByObject() {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.a);
        lv.addHeaderView(image);
        Button button = new Button(this);
        button.setText("点击获取更多");
        lv.addFooterView(button);
    }

    private void addByXml() {
        lv.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header, null));
        lv.addFooterView(LayoutInflater.from(this).inflate(R.layout.footer, null));
    }


}
