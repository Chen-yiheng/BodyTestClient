package com.example.vip.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter的另一个常用的适配对象Spinner下拉列表
 * 1、可以通过list传入数据源
 *
 * 2、可以通过xml文件传入数据
 * 3、还可以直接在布局中设置
 */
public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<CharSequence> adapter2;
    private List<String> list;
    private TextView tv;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);

//        initByList();
        initByResource();

    }

    /**
     *
     */
    private void initByResource() {
        adapter2 = ArrayAdapter.createFromResource(this, R.array.datalist, android.R.layout
                .simple_list_item_1);
        spinner.setAdapter(adapter2);
        tv = (TextView) findViewById(R.id.tv);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(adapter2.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     *
     */
    private void initByList() {
        list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add("功成名就" + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        亦可以设置列表的显示样式
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        tv = (TextView) findViewById(R.id.tv);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //          选中时执行的方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(adapter.getItem(position));
            }

            //没有选中时执行的方法
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
