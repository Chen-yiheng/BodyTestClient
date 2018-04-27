package com.example.drawerlayout;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 滑动滑出菜单，可以设置菜单的内容及大小，还可以设置从什么方位滑出来
 */
public class MainActivity extends AppCompatActivity {

	private DrawerLayout drawerLayout;
	private ListView listView;
	private ListAdapter adapter;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
             /*  Toolbar最左侧的按钮叫HomeAsUp按钮，它默认是一个返回的箭头，
              可以通过上一句显现出来，通过下一句改变图标*/
			actionBar.setHomeAsUpIndicator(R.drawable.connected);
		}
		listView = (ListView) findViewById(R.id.listView);
		list = new ArrayList<>();
		list.add("Apple");
		list.add("banana");
		list.add("pear");
		adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);
				break;
		}
		return true;
	}

	/**
	 * @param keyCode
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
			if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
				drawerLayout.closeDrawer(GravityCompat.START);
			} else {
				drawerLayout.openDrawer(GravityCompat.START);
			}
		}

		return false;
	}


}
