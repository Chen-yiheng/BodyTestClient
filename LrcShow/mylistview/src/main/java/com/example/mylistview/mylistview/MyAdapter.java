package com.example.mylistview.mylistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mylistview.R;

import java.util.List;

/**
 * Created by vip on 2017/5/22.
 */

public class MyAdapter extends ArrayAdapter<String>{

	public MyAdapter(Context context,int textViewResourceID,List<String> list){
		super(context,textViewResourceID,list);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if(convertView==null){
			view= LayoutInflater.from(getContext()).inflate(R.layout.my_listview_item,null);
		}else {
			view=convertView;
		}
		TextView textView= (TextView) view.findViewById(R.id.text_view);
		textView.setText(getItem(position));
		return view;
	}
}
