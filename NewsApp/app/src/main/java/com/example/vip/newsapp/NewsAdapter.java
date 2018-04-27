package com.example.vip.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 *
 */

public class NewsAdapter extends ArrayAdapter<News> {
	private int resourceId;



	public NewsAdapter(Context context, int resource, List<News> objects) {
		super(context, resource, objects);
		resourceId=resource;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if(convertView==null){
			view= LayoutInflater.from(getContext()).inflate(resourceId,null);
			holder=new ViewHolder();
			holder.adapterTitle= (TextView) view.findViewById(R.id.adapter_title);
			view.setTag(holder);
		}else {
			view=convertView;
			holder= (ViewHolder) view.getTag();
		}
		holder.adapterTitle.setText(getItem(position).getTitle());
		return view;
	}

	private class ViewHolder{
		TextView adapterTitle;
	}
}
