package com.example.vip.downloader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vip on 2017/4/5.
 */

public class FileService {
	private DBOpenHelper openHelper;

	private class DBOpenHelper extends SQLiteOpenHelper {

		public DBOpenHelper(Context context) {
			super(context, "download.db", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table if not exists filedownlog (id integer primary key " +
				   "autoincrement ,downpath varchar(100),threadid integer,downlength " +
				   "integer)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists filedownlog");
			onCreate(db);
		}
	}

	public FileService(Context context) {
		openHelper = new DBOpenHelper(context);
	}

	/**
	 * 用于加载已下载的信息
	 *
	 * @param path
	 * @return
	 */
	public Map<Integer, Integer> getData(String path) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select threadid,downlength from filedownlog where " +
			   "downpath=?", new String[]{path});
		Map<Integer, Integer> data = new HashMap<>();
		while (cursor.moveToNext()) {
			data.put(cursor.getInt(0), cursor.getInt(1));
		}
		cursor.close();
		db.close();
		return data;
	}

	/**
	 * 保存下载过程中的断点数据
	 *
	 * @param path
	 * @param data
	 */
	public void save(String path, Map<Integer, Integer> data) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		db.beginTransaction();

		try {
			for (int temp : data.keySet()) {
				db.execSQL("insert into filedownlog (downpath,threadid,downlength)values(?,?," +
					   "?)",
					   new Object[]{path, temp, data.get(temp)});
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		db.close();
	}

	/**
	 * 更新数据库下载断点的信息
	 * @param path
	 * @param data
	 */
	public void update(String path, Map<Integer, Integer> data) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		db.beginTransaction();

		try {
			for (Integer key : data.keySet()) {
				db.execSQL("update filedownlog set downlength =? where downpath=? and threadid=?",
					   new Object[]{data.get(key), path, key});
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		db.close();
	}

	/**
	 * 当文件下载完成后，删除对应的下载记录
	 * @param path
	 */
	public void delete(String path){
		SQLiteDatabase db=openHelper.getWritableDatabase();
		db.execSQL("delete from filedownlog where downpath =?",new Object[]{path});
		db.close();
	}


}


