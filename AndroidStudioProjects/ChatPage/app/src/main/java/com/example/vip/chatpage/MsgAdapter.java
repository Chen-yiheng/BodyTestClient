package com.example.vip.chatpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 *
 */

public class MsgAdapter extends ArrayAdapter<Message> {
	private int resourceId;



	public MsgAdapter(Context context, int resource, List<Message> list) {
		super(context, resource, list);
		resourceId=resource;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View view;
		Message msg=getItem(position);
		if (convertView == null) {
			view=LayoutInflater.from(getContext()).inflate(resourceId,null);
			holder=new ViewHolder();
			holder.leftLyout= (LinearLayout) view.findViewById(R.id.left_layout);
			holder.rightLayout= (LinearLayout) view.findViewById(R.id.right_layout);
			holder.leftContent= (TextView) view.findViewById(R.id.left_content);
			holder.rightContent= (TextView) view.findViewById(R.id.right_content);
			view.setTag(holder);
		}else {
			view=convertView;
			holder= (ViewHolder) view.getTag();
		}
		if(msg.getType()==Message.TYPE_SEND){
			holder.leftLyout.setVisibility(View.VISIBLE);
			holder.rightLayout.setVisibility(View.GONE);
			holder.leftContent.setText(msg.getContent());
		}else if(msg.getType()==Message.TYPE_RECEIVE){
			holder.rightLayout.setVisibility(View.VISIBLE);
			holder.leftLyout.setVisibility(View.GONE);
			holder.rightContent.setText(msg.getContent());
		}
		return view;
	}

	private class ViewHolder {
		private LinearLayout leftLyout;
		private LinearLayout rightLayout;
		private TextView leftContent;
		private TextView rightContent;
	}
}
