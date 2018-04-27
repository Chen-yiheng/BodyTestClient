package com.example.dynamicfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vip on 2017/5/1.
 */

public class SettingFragment extends Fragment {
	private TextView textView;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_content,container,false);
		textView= (TextView) view.findViewById(R.id.fragment_content);
		textView.setText("我是设置");
		return view;
	}
}
