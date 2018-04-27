package com.example.navigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
	private DrawerLayout drawerLayout;


	/**
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		ActionBar actionBar = getSupportActionBar();
		if (null != actionBar) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		}
		navigationView.setCheckedItem(R.id.nav_call);
		navigationView.setNavigationItemSelectedListener(new NavigationView
			   .OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				drawerLayout.closeDrawers();
				switch (item.getItemId()) {
					case R.id.nav_call:
						Toast.makeText(MainActivity.this, "you selected Call ", Toast
							   .LENGTH_SHORT).show();
						break;
					case R.id.nav_friends:
						Toast.makeText(MainActivity.this, "you selected Friends ", Toast
							   .LENGTH_SHORT).show();
						break;
					case R.id.nav_location:
						Toast.makeText(MainActivity.this, "you selected Location ", Toast
							   .LENGTH_SHORT).show();
						break;
					case R.id.nav_task:
						Toast.makeText(MainActivity.this, "you selected Task ", Toast
							   .LENGTH_SHORT).show();
						break;
					case R.id.nav_mail:
						Toast.makeText(MainActivity.this, "you selected Mail ", Toast
							   .LENGTH_SHORT).show();
						break;
				}
				return true;
			}
		});
	}

	/**
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);
				break;
		}
		return false;
	}
}
