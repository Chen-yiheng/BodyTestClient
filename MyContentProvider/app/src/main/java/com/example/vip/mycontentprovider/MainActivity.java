package com.example.vip.mycontentprovider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.CopyOnWriteArraySet;

public class MainActivity extends AppCompatActivity {
	private Button insert, query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		insert = (Button) findViewById(R.id.insert);
		query = (Button) findViewById(R.id.query);
		insert.setOnClickListener(new MyOnClikListener());
		query.setOnClickListener(new MyOnClikListener());


	}

	private class MyOnClikListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.insert:
					ContentValues cv = new ContentValues();
					cv.put(Constant.FriendsTableMetaData.FRIEND_NAME, "黄益凛");
					cv.put(Constant.FriendsTableMetaData.FRIEND_NUMBER, "13538355250");
					Uri uri = getContentResolver().insert(Constant.FriendsTableMetaData.URI, cv);
					Log.i("tag", "uri：" + uri);
					break;
				case R.id.query:
					Cursor c = getContentResolver().query(Uri.parse(Constant
								 .FriendsTableMetaData.URI.toString() + "/#"),
						   null, null, null, null);
					while (c.moveToNext()) {
						Log.i("tag", "数据：" + c.getString(c.getColumnIndex("name")) + "   " +
							   c.getString(c.getColumnIndex("number")));
					}
					break;

			}
		}
	}
}
