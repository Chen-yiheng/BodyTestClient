package com.example.resultfragment;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vip on 2017/5/3.
 */

public class TitleFragment extends ListFragment {
	public static final int REQUEST_CODE = 0X120;
	public static final int RESULT_CODE = 0X110;
	private int listPosition;
	public  static final String ARG="arg";

	private List<String> list = Arrays.asList("天才", "成功", "失败");
	private ArrayAdapter<String> adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
			   list);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		listPosition=position;
		Intent intent=new Intent(getActivity(),ContentActivity.class);
		intent.putExtra(ARG,list.get(position));
		startActivityForResult(intent,REQUEST_CODE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==REQUEST_CODE&&resultCode==RESULT_CODE){
			list.set(listPosition,list.get(listPosition)+data.getStringExtra(ARG));
			adapter.notifyDataSetChanged();
		}
	}
}
