package com.example.fragmentaddmenu;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Fragment也具备创建添加menu的功能，而且fragment可自己监听MenuItem也可交给Activity处理
 * Fragment必须在onCreate（）方法中设置setHasOptionsMenu(true);允许添加menu，
 * 否则即便添加也不会显示
 * 还有Fragment自己添加的menu只有在自己显示的时候，菜单才会显示
 *
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (null == savedInstanceState) {
			android.app.FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			FirstFragment firstFragment = new FirstFragment();
			transaction.add(R.id.fragment, firstFragment);
			transaction.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.about:
				Toast.makeText(this, "点击的是关于", Toast.LENGTH_SHORT).show();
				break;
			case R.id.setting:
				Toast.makeText(this,"点击的是设置", Toast.LENGTH_SHORT).show();
				break;
			case R.id.game:
				Toast.makeText(this, "点击的是游戏", Toast.LENGTH_SHORT).show();
				break;
			default:
				//如果希望fragment自己处理MenuItem的点击事件，则必须调用父类方法；
				return super.onOptionsItemSelected(item);
		}
		return true;

	}
}
