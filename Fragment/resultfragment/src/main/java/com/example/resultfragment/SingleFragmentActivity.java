package com.example.resultfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by vip on 2017/5/2.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android.app.FragmentManager fm=getFragmentManager();
		Fragment fragment=fm.findFragmentById(R.id.activity_main);
		if(null==fragment){
			fragment=createFragment();
			fm.beginTransaction().add(R.id.activity_main,fragment).commit();
		}

	}
}
