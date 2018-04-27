package com.example.vip.meterialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.backup:
				Toast.makeText(this,"you clicked Backup",Toast.LENGTH_SHORT).show();
				break;
			case R.id.delete:
				Toast.makeText(this,"you clicked Delete",Toast.LENGTH_SHORT).show();
				break;
			case R.id.settings:
				Toast.makeText(this,"you clicked Settings",Toast.LENGTH_SHORT).show();
				break;

		}
		return true;
	}
}
