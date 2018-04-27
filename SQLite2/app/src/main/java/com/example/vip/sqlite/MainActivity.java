package com.example.vip.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	private DBOpenHelper helper;
	private Button save;
	private Button show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new DBOpenHelper(this, "phonebook.db");
		save = (Button) findViewById(R.id.save);
		show = (Button) findViewById(R.id.show);
	}
	int i = 1;
	public void onSave(View view) {
		saveData("黄益凛" + i, "13538355250", "主人" + i);
		i++;
		deleteData("黄益凛1");
	}

	public void onShow(View view) {
		Cursor cursor = queryAll();
		while (cursor.moveToNext()) {
			Log.i("tag", cursor.getInt(0) + "    " + cursor.getString(1) + "    " + cursor
				   .getString(2) + "    " + cursor.getString(3));
		}
		cursor.close();
	}

	public void saveData(String name, String phoneNumber, String relationship) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into phonebook(name,phoneNumber,relationship)values(?,?,?)", new
			   Object[]{name, phoneNumber, relationship});
	}

	public void deleteData(String name) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from phonebook where name like ? ", new Object[]{name});
	}

	public Cursor queryAll() {
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.rawQuery("select * from phonebook", null);
	}

}
