package com.example.backstack;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 测试Fragment的返回栈，在Fragment跳转时，只要将当前的Fragment加入返回栈，
 *  那么在已跳转的那个Frgment上按返回键，就会将上一个Fragment返回
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();

		android.app.FragmentManager manager=getFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		FirstFragment firstFragment=new FirstFragment();
		transaction.add(R.id.fragment,firstFragment,"FIRST");
		transaction.commit();
		Log.i("tag","MainActivity:onCreate");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("tag","MainActivity:onDestroy");
	}

}
