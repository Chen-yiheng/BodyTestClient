package com.example.arguments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by vip on 2017/5/1.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
	private Button toSecondFragment;
	private TextView tv;
	private String argument;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("tag", "FirstFragment:onCreate");
		Bundle bundle=getArguments();
		if(null!=bundle){
			argument=bundle.getString("arg2");
		}

	}



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_first, container, false);
		toSecondFragment = (Button) view.findViewById(R.id.toSecondFragment);
		toSecondFragment.setOnClickListener(this);
		tv= (TextView) view.findViewById(R.id.text1);
		tv.setText(argument);
		Log.i("tag", "FirstFragment:onCreateView");
		return view;
	}

	@Override
	public void onClick(View v) {
		android.app.FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		SecondFragment secondFragment = newInstance("arg","志存高远");
		transaction.replace(R.id.fragment, secondFragment, "SECOND");
		transaction.addToBackStack(null);
		transaction.commit();
	}

	public static SecondFragment newInstance(String key,String value){
		Bundle bundle=new Bundle();
		bundle.putString(key,value);
		SecondFragment secondFragment=new SecondFragment();
		secondFragment.setArguments(bundle);
		return  secondFragment;

	}



	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i("tag", "FirstFragment:onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("tag", "FirstFragment:onDestroy");
	}
}
