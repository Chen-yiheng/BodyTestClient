package com.example.cardview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
	private DrawerLayout drawerLayout;
	private SwipeRefreshLayout refreshLayout;
	private FruitAdapter fruitAdapter;
	private Fruit[] fruits = {new Fruit(R.drawable.apple, "苹果"), new Fruit(R.drawable.pear,
		   "梨"), new Fruit(R.drawable.banana, "香蕉"), new Fruit(R.drawable.cherry, "樱桃"), new
		   Fruit(R.drawable.mango, "芒果"), new Fruit(R.drawable.grape, "葡萄"), new Fruit(R
		   .drawable.orange, "香橙"), new Fruit(R.drawable.pineapple, "地菠萝"), new Fruit(R.drawable
		   .watermelon, "西瓜"), new Fruit(R.drawable.strawberry, "草莓")};
	private List<Fruit> fruitList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
		ActionBar actionBar = getSupportActionBar();
		if (null != actionBar) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		}
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setCheckedItem(R.id.nav_call);
		navigationView.setNavigationItemSelectedListener(new NavigationView
			   .OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				drawerLayout.closeDrawers();
				Toast.makeText(MainActivity.this, "you click menu", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Snackbar.make(v, "删除？", Snackbar.LENGTH_SHORT).setAction("确认", new View
					   .OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "已删除", Toast.LENGTH_SHORT)
							   .show();
					}
				}).show();
			}
		});

		initFruitList();
		RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
		GridLayoutManager layoutManager=new GridLayoutManager(this,2);
		recyclerView.setLayoutManager(layoutManager);
		fruitAdapter=new FruitAdapter(fruitList);
		recyclerView.setAdapter(fruitAdapter);

		refreshLayout= (SwipeRefreshLayout) findViewById(R.id.refresh);
		refreshLayout.setColorSchemeResources(R.color.colorPrimary);
		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refreshFruitList();
			}
		});


	}

	private void refreshFruitList(){
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						initFruitList();
						fruitAdapter.notifyDataSetChanged();
						refreshLayout.setRefreshing(false);
					}
				});
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);
				break;
			case R.id.delete:
				Toast.makeText(this, "你点击了删除按钮", Toast.LENGTH_SHORT).show();
				break;
			case R.id.backup:
				Toast.makeText(this, "你点击了上传按钮", Toast.LENGTH_SHORT).show();
				break;
			case R.id.settings:
				Toast.makeText(this, "你点击了设置按钮", Toast.LENGTH_SHORT).show();
				break;
		}
		return false;
	}

	private void initFruitList() {
		fruitList.clear();
		for (int i = 0; i < 50; i++) {
			Random random = new Random();
			int index = random.nextInt(fruits.length);
			fruitList.add(fruits[index]);
		}
	}

}

