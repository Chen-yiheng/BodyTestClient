package com.example.arguments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vip on 2017/5/1.
 */

public class SecondFragment extends Fragment implements View.OnClickListener {
	private Button toThirdFragment;
	private TextView tv;
	private String argument;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if (null != bundle) {
			argument = bundle.getString("arg");
		}

		Log.i("tag", "SecondFragment:onCreate");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_second, container, false);
		toThirdFragment = (Button) view.findViewById(R.id.toThirdFragment);
		tv = (TextView) view.findViewById(R.id.text2);
		tv.setText(argument);
		toThirdFragment.setOnClickListener(this);
		Log.i("tag", "SecondFragment:onCreateView");
		return view;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity(), "this is second activity", Toast.LENGTH_SHORT).show();
		android.app.FragmentManager manager=getFragmentManager();
		FirstFragment firstFragment=newInstance("arg2","勇攀高峰");
		manager.beginTransaction().replace(R.id.fragment,firstFragment).commit();
	}

	public static FirstFragment newInstance(String key, String value) {
		Bundle bundle = new Bundle();
		bundle.putString(key, value);
		FirstFragment firstFragment = new FirstFragment();
		firstFragment.setArguments(bundle);
		return firstFragment;

	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i("tag", "SecondFragment:onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("tag", "SecondFragment:onCreate");
	}
}
