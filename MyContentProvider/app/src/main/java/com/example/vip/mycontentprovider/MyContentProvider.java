package com.example.vip.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vip on 2017/4/8.
 */

public class MyContentProvider extends ContentProvider {
	private static final UriMatcher uriMatcher;
	private static final int FRIEND_COLLECTION = 1;
	private static final int FRIEND_SINGLE = 2;
	private SQLiteOpenHelper helper;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(Constant.AUTHORITY, Constant.DBTABLE_NAME, FRIEND_COLLECTION);
		//#号为通配符
		uriMatcher.addURI(Constant.AUTHORITY, Constant.DBTABLE_NAME + "/#", FRIEND_SINGLE);

	}


	@Override
	public boolean onCreate() {
		helper = new SQLiteOpenHelper(getContext(), Constant.DATABASE_NAME, null, 1) {

			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL("create table if not exists " + Constant.DBTABLE_NAME + "( " +
					   Constant.FriendsTableMetaData._ID + " integer primary key  " +
					   "autoincrement,name varchar(20),number varchar(20))");
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				db.execSQL("drop table if exists " + Constant.DBTABLE_NAME);
			}
		};
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
					String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (uriMatcher.match(uri)) {
			case FRIEND_COLLECTION:
				qb.setTables(Constant.DBTABLE_NAME);
//				qb.setProjectionMap(projectionMap);
				Log.i("tag", "查询集合类型数据");
				break;
			case FRIEND_SINGLE:
				qb.setTables(Constant.DBTABLE_NAME);
//				qb.setProjectionMap(projectionMap);
				qb.appendWhere(Constant.FriendsTableMetaData._ID + "=" + uri.getPathSegments()
					   .get(1));
				Log.i("tag", "查询非集合类型数据");
				break;
		}
		String orderBy = TextUtils.isEmpty(sortOrder) ? Constant.FriendsTableMetaData
			   .DEFAULT_SORT_ORDER : sortOrder;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case FRIEND_COLLECTION:
				return Constant.FriendsTableMetaData.CONTENT_TYPE;
			case FRIEND_SINGLE:
				return Constant.FriendsTableMetaData.CONTENT_TYPE_ITEM;
			default:
				throw new IllegalArgumentException("Unknown URI" + uri);

		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		SQLiteDatabase db = helper.getWritableDatabase();
		long rowId = db.insert(Constant.DBTABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertUri = ContentUris.withAppendedId(Constant.FriendsTableMetaData.URI,
				   rowId);
			getContext().getContentResolver().notifyChange(insertUri, null);
			Log.i("tag", "已进入insert,uri:" + insertUri);
			return insertUri;
		}

		throw new SQLiteException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.delete(Constant.DBTABLE_NAME, selection, selectionArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.update(Constant.DBTABLE_NAME, values, selection, selectionArgs);
	}
}
