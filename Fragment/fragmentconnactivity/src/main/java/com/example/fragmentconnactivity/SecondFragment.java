package com.example.fragmentconnactivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vip on 2017/5/1.
 */

public class SecondFragment extends Fragment implements View.OnClickListener {
	private Button toThirdFragment;
	private OnFragmentClikListener onFragmentClikListener;

	public void setOnFragmentClikListener(OnFragmentClikListener onFragmentClikListener){
		this.onFragmentClikListener=onFragmentClikListener;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_second, container, false);
		toThirdFragment = (Button) view.findViewById(R.id.toThirdFragment);
		toThirdFragment.setOnClickListener(this);
		Log.i("tag", "SecondFragment:onCreateView");
		return view;
	}

	@Override
	public void onClick(View v) {
		if(null!=onFragmentClikListener){
			onFragmentClikListener.onClik(v);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("tag", "SecondFragment:onCreate");
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
