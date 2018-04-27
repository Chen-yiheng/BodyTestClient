package com.example.dynamicfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private LinearLayout nixin;
	private LinearLayout frend;
	private LinearLayout connections;
	private LinearLayout setting;
	private NixinFragment nixinFragment;
	private FrendFragment frendFragment;
	private ConnectionsFragment connectionsFragment;
	private SettingFragment settingFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设为横屏
		nixin= (LinearLayout) findViewById(R.id.nixin);
		frend= (LinearLayout) findViewById(R.id.frend);
		connections= (LinearLayout) findViewById(R.id.connections);
		setting= (LinearLayout) findViewById(R.id.setting);
		nixin.setOnClickListener(this);
		frend.setOnClickListener(this);
		connections.setOnClickListener(this);
		setting.setOnClickListener(this);
		nixinFragment=new NixinFragment();
		frendFragment=new FrendFragment();
		connectionsFragment=new ConnectionsFragment();
		settingFragment=new SettingFragment();
		if(null==savedInstanceState){
			setFragment(nixinFragment);
		}
	}

	public void setFragment(Fragment fragment){
		FragmentManager manager=getFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.content,fragment);
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.nixin:
				setFragment(nixinFragment);
				break;
			case R.id.frend:
				setFragment(frendFragment);
				break;
			case R.id.connections:
				setFragment(connectionsFragment);
				break;
			case  R.id.setting:
				setFragment(settingFragment);
				break;
		}
	}

	@Override
	protected void onResume() {

		super.onResume();
	}
}
