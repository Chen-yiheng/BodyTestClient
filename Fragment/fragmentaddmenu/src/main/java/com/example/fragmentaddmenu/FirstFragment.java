package com.example.fragmentaddmenu;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by vip on 2017/5/2.
 */

public class FirstFragment extends Fragment {
	private Button toSecondFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		要想fragment具备创建Menu功能，必须添加这个
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_first,container,false);
		toSecondFragment= (Button) view.findViewById(R.id.toSecondFragment);
		toSecondFragment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("tag","taiozhuandajkd46415614");
				android.app.FragmentManager manager=FirstFragment.this.getFragmentManager();
				FragmentTransaction transaction=manager.beginTransaction();
				SecondFragment fragment=new SecondFragment();
				transaction.replace(R.id.fragment,fragment);
				transaction.addToBackStack(null);
				transaction.commit();
				Log.i("tag","taiozhuandajkd");
			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment,menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.book:
				Toast.makeText(getActivity(),"点击的是小说",Toast.LENGTH_SHORT).show();
				break;
			case R.id.news:
				Toast.makeText(getActivity(),"点击的是新闻",Toast.LENGTH_SHORT).show();
				break;
			case R.id.taobao:
				Toast.makeText(getActivity(),"点击的是淘宝",Toast.LENGTH_SHORT).show();
				break;
		}
		return true;
	}
}