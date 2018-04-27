package com.example.fragmentaddmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class SecondFragment extends Fragment {
	private Button secondFragment;
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
		View view=inflater.inflate(R.layout.fragment_second,container,false);
		secondFragment= (Button) view.findViewById(R.id.toThirdFragment);
		secondFragment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"这是SecondFragment",Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(0,1,2,"第一");
		menu.add(0,2,1,"第二");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("tag","onOptionsItemSelected");
		switch (item.getItemId()){
			case 1:
				Toast.makeText(getActivity(),"第一",Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(getActivity(),"第二",Toast.LENGTH_SHORT).show();
				break;
		}
		return true;
	}
}
