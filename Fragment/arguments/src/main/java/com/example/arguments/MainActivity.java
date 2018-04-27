package com.example.arguments;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 实现Fragment之间的数据的传输
 */
public class MainActivity extends AppCompatActivity {
	private FirstFragment firstFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android.app.FragmentManager manager = getFragmentManager();
		firstFragment = (FirstFragment) manager.findFragmentById(R.id.fragment);
		if (null == firstFragment) {
			firstFragment = new FirstFragment();
			manager.beginTransaction().add(R.id.fragment,firstFragment).commit();
		}
	}


}
