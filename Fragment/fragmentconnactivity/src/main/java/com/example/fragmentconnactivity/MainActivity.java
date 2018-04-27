package com.example.fragmentconnactivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 实现Fragment与Activity的交互，Fragment里的button里的操作交给activity完成
 * 每一次transaction.commit();前都需重新获取FragmentManager和FragmentTransaction
 * manager = getFragmentManager();
 * transaction = manager.beginTransaction();
 */
public class MainActivity extends AppCompatActivity implements OnFragmentClikListener {
	private android.app.FragmentManager manager;
	private FragmentTransaction transaction;
	private SecondFragment secondFragment;
	private ThirdFragment thirdFragment;
	private FirstFragment firstFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();//隐藏标题栏

		Log.i("tag","Bundle"+savedInstanceState);
		if (null == savedInstanceState) {
			manager = getFragmentManager();
			transaction = manager.beginTransaction();
			firstFragment = new FirstFragment();
			firstFragment.setOnFragmentClikListener(this);
			transaction.add(R.id.fragment, firstFragment, "FIRST");
			transaction.addToBackStack(null);
			transaction.commit();
			Log.i("tag","创建一个firstFragment"+firstFragment);
		}



	}

	@Override
	public void onClik(View view) {

		switch (view.getId()) {
			case R.id.toSecondFragment:
				manager = getFragmentManager();
				transaction = manager.beginTransaction();
				if (null == secondFragment) {
					secondFragment = new SecondFragment();
				}
				secondFragment.setOnFragmentClikListener(this);
				transaction.replace(R.id.fragment, secondFragment, "SECOND");
				transaction.addToBackStack(null);
				transaction.commit();
				break;
			case R.id.toThirdFragment:
				manager = getFragmentManager();
				transaction = manager.beginTransaction();
				if (null == thirdFragment) {
					thirdFragment = new ThirdFragment();
				}
				transaction.hide(secondFragment);
				transaction.add(R.id.fragment, thirdFragment, "THIRD");
				transaction.addToBackStack(null);
				transaction.commit();
				break;
			case R.id.thirdFragment:
				Toast.makeText(this, "这是第三个fragment", Toast.LENGTH_SHORT).show();
				break;

		}

	}
}
