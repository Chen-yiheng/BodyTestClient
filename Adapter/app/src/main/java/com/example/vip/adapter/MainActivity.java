package com.example.vip.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayAdapter:使用在适配每一项都是字符的情况
 * <p>
 * 适配的过程中需要存在数据源，适配器，将数据源放在适配器上，再将适配器设置到AdapterView上
 * 数据源：
 * Adapter：ArrayAdapter
 * AdapterView：ListView常用
 * <p>
 * MVC 模式,将前端显示和后端数据分离
 * M（model）数据模型----数据源
 * V（view）显示的视图控件-----AdapterView
 * C（controller）控制器-------adapter
 */
public class MainActivity extends AppCompatActivity {
     private ListView lv;
     //    private ArrayAdapter<String> adapter;
     private List<String> list;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          lv = (ListView) findViewById(R.id.lv);
          list = new ArrayList();
          for (int i = 0; i < 40; i++) {
               list.add("黄益凛" + i);
          }
//        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
//        lv.setAdapter(adapter);
//       使用自定义布局
      test();
//          test2();

     }

     /**
      * 使用自定义的布局文件,布局的脚跟必须是TextView
      */
     public void test2() {
          //参数以使用到的上下文对象 参数二：使用到的布局文件，给item进行使用时 参数三：数据源
          final ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.array_adapter_item,
                  list);
          lv.setAdapter(adapter);
          //给ListView设置点击事件监听
          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               /*
               parent：适配器设置到的adapterView对象和，在这里表示的是ListView
               view：适配器item对应的view
               position：索引位置，索引从0开始依次向下递增
               id：在listview中的item对应的行id
                */
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "点击的索引位置是" + position + ",id:" + id,
                            Toast.LENGTH_SHORT).show();
               }
          });
          //设置长按事件监听
          lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                       id) {
                    list.remove(position);
                    //list对象改变后需要通知与其相关联的对象进行刷新
                    Toast.makeText(MainActivity.this, "删除" + parent.getItemAtPosition(position),
                            Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    return true;
               }
          });

     }

     /**
      * 使用xml文件作为数据源，不过arrayadapter的类型必须为字符序列charsequence
      */
     public void test() {
          ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array,
                  android.R.layout.simple_expandable_list_item_1);
          lv.setAdapter(adapter2);

     }
}
