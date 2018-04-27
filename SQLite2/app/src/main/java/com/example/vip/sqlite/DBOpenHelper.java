package com.example.vip.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vip on 2017/4/6.
 *
 *
 */

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;

	public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
		   version) {
		super(context, name, factory, version);

	}

	public DBOpenHelper(Context context, String name) {
		this(context, name, null, VERSION);
	}

	public DBOpenHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists phonebook(id integer primary key autoincrement," +
			   "name " + "varchar(20),phoneNumber varchar(20),relationship varchar(30))");
	}

	/**
	 * 在已经创建数据库的时候，如果需要创建新的表，则需要改变版本号，这样才能创建成功
	 * 当版本号大于当前的版本号时，运行这个方法
	 *
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists student");//删除原来的表格
		onCreate(db);//创建新的表格
	}
}
