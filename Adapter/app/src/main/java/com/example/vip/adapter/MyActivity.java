package com.example.vip.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义适配器：
 * 1、继承BaseAdapter抽象类，实现里面的四个抽象方法
 * 2、通过代码将需要适配的数目指定，需要返回的View对象指定
 * <p>
 * LayoutInflater：布局映射适配器
 * 主要作用：将一个定义好的布局xml文件转化成view对象
 * findViewById通过view对象来调用
 * 通过from方法获取布局映射器对象，就可以调用该对象的inflate方法将一个xml文件转化成view对象
 * <p>
 * 缓存优化：
 * 如果一个屏幕中可以呈现n项item，那么在创建view视图的时候，系统会创建出n+1个
 * <p>
 * 利用屏幕上的item每划出一个，便会划入一个，我们可以将要划入的item呈现的view复用之前划出的item
 * 对应的view，以实现节约内存，提高效率
 */

public class MyActivity extends AppCompatActivity {
     private ListView lv;
     private MyAdapter<String> adapter;
     private List<String> list;

     /**
      * @param savedInstanceState
      */
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          lv = (ListView) findViewById(R.id.lv);
          list = new ArrayList();
          for (int i = 0; i < 40; i++) {
               list.add("黄益凛" + i);
          }
          adapter = new MyAdapter<>(this, list, R.layout.my_adapter_item);
          lv.setAdapter(adapter);

     }
}

/**
 * @param <T> /
 */
class MyAdapter<T> extends BaseAdapter {
     private Context context;
     private List<T> list;
     private int resource;

     public MyAdapter(Context context, List<T> list, @LayoutRes int resource) {
          this.context = context;
          this.list = list;
          this.resource = resource;
     }

     public MyAdapter() {
     }

     /**
      * @return
      */
     //计算需要适配的item数目
     @Override
     public int getCount() {
          return list.size();
     }

     //获取每一个item
     @Override
     public Object getItem(int position) {
          return list.get(position);
     }

     //获取每一个item的id值
     @Override
     public long getItemId(int position) {
          return position;
     }

     //获取每一个item对应的view视图
     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
          Holder holder;
          if (convertView == null) {
               convertView = LayoutInflater.from(context).inflate(resource, null);
               holder = new Holder();
               holder.tv = (TextView) convertView.findViewById(R.id.my_adapter);
               convertView.setTag(holder);
               Log.i("tag", "创建了一个view" + convertView);
          } else {
               holder = (Holder) convertView.getTag();
               Log.i("tag", "复用了view" + convertView);
          }
          holder.tv.setText((String) getItem(position));
          return convertView;

       /* Log.i("Tag","创建一个view"+convertView);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);
        TextView textView = (TextView) view.findViewById(R.id.my_adapter);
        textView.setText((String) getItem(position));*/
          //  return view;
     }

     private class Holder {
          TextView tv;
     }

}

