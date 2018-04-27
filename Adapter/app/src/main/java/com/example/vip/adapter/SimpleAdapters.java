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
 * Created by vip on 2017/3/9.
 */

public class SimpleAdapters extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_adapter);
        listView = (ListView) findViewById(R.id.lv);
        list = new ArrayList();

        for (int i = 0; i < 50; i++) {
            Map map = new HashMap();
            map.put("data", "希望" + i);
            map.put("introduce", "介绍" + i);
            list.add(map);
        }
        /**
         * 参数一：上下文对象 参数二：数据源List<Map<String,Object>>
         * 参数三：item对应的布局文件，参数四：表示map中定义的Key组成的字符串类型的数组
         * 参数五：需要显示的控件id组成的int类型的数组
         *
         */

        String[] from = {"data", "introduce"};
        int[] to = {R.id.data, R.id.intro};
        adapter = new SimpleAdapter(this, list, R.layout.simple_adapter_item, from, to);
        listView.setAdapter(adapter);

    }
}
