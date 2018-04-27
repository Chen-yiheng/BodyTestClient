package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 软件升级的时候，要增加一张表或增加一个字段，
 * 则需按以下方法处理，这样才能保证数据不丢失
 */

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
		   version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists book (id integer primary key autoincrement," +
			   "name text,author text,price real,pages integer)");
		db.execSQL("create table if not exists catagory(id integer primary key autoincrement," +
			   "category text,category_code integer)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
			case 1:
				db.execSQL("create table if not exists catagory(id integer primary key " +
					   "autoincrement," + "category text,category_code integer)");
			case 2:
				db.execSQL("alter table book add column category_id integer");
			default:
		}
		Log.i("tag","更新成功");

	}

}
