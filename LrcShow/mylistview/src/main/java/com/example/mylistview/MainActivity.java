package com.example.mylistview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.mylistview.mylistview.MyAdapter;
import com.example.mylistview.mylistview.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 对ListView进行扩展，加入在ListView上滑动就可以显示出一个删除按钮，
 * 点击按钮就会删除相应数据的功能。
 * 只能用自定义适配器
 *
 */
public class MainActivity extends AppCompatActivity {
	private MyListView myListView;
	private MyAdapter adapter;
	private List<String>contenList=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myListView= (MyListView) findViewById(R.id.mylistview);
		initList();
		myListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
			@Override
			public void onDelete(int index) {
				contenList.remove(index);
				adapter.notifyDataSetChanged();
			}
		});
		adapter=new MyAdapter(this,0,contenList);
		myListView.setAdapter(adapter);
	}

	private void initList(){
		for (int i=0;i<30;i++){
			contenList.add("黄益凛"+i);
		}

	}
}
