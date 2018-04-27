package com.example.vip.mycontentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 定义本运用程序中的所有常量
 */

public class Constant {
	//主机名或域名
	public static final String AUTHORITY = "com.example.vip.mycontentprovider";
	//数据库名称
	public static final String DATABASE_NAME = "Provider.db";
	//数据库版本
	public static final int DATABASE_VERSION = 1;
	//表明
	public static final String DBTABLE_NAME = "friend";

	public static final class FriendsTableMetaData implements BaseColumns {
		//ContentProvider的表名
		public static final String TABLE_NAME = "friend";
		/**
		 * 访问该ContentProvider的URI
		 */
		public static final Uri URI = Uri.parse("content://" + AUTHORITY + "/"+TABLE_NAME);
		/**
		 * 访问集合类型数据所返回的数据类型的定义
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/mycontentprovider";
		/**
		 * 访问非集合类型数据所返回的数据类型的定义
		 */
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/mycontentprovider";
		/**
		 * conpentprovider的表的列名
		 */
		public static final String FRIEND_NAME = "name";
		public static final String FRIEND_NUMBER="number";

		/**
		 * 默认的排序方法
		 *
		 */
		public static final String DEFAULT_SORT_ORDER = "_id desc";

	}
}
