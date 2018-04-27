package com.example.sqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * SQLite 数据库是支持事务的，事务的特性可以保证让某一系列的操
 * 作要么全部完成，要么一个都不会完成,本例程测试替换书表格，Book 表中的数据都已经很老了，
 * 现在准备全部废弃掉替换成新数据，可以
 * 先使用 delete()方法将 Book表中的数据删除，
 * 然后再使用 insert()方法将新的数据添加到表中。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private Button save, query, update;
	private TextView data;


	/**
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new DBHelper(this, "BookStore.db", null, 2);
		db = dbHelper.getWritableDatabase();
		save = (Button) findViewById(R.id.save);
		query = (Button) findViewById(R.id.query);
		data = (TextView) findViewById(R.id.data);
		update = (Button) findViewById(R.id.change);
		update.setOnClickListener(this);
		save.setOnClickListener(this);
		query.setOnClickListener(this);
		/**
		 * 滚动条显示多行内容
		 */
		data.setMovementMethod(ScrollingMovementMethod.getInstance());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.save:
				insertData();
				break;
			case R.id.query:
				queryData();
				break;
			case R.id.change:
				updateBookTable();
				break;
		}
	}

	private void insertData() {
		db.execSQL("insert into book(name,author,price,pages)values(?,?,?,?)", new
			   String[]{"完美世界", "辰东", "50.8", "3000"});
		db.execSQL("insert into book(name,author,price,pages)values(?,?,?,?)", new String[]{"圣墟",
			   "辰东", "30.8", "1000"});
		db.execSQL("insert into book(name,author,price,pages)values(?,?,?,?)", new String[]{"神墓",
			   "辰东", "40.6", "2000"});
		db.execSQL("insert into book(name,author,price,pages)values(?,?,?,?)", new
			   String[]{"长生界",
			   "辰东", "28.8", "800"});
		db.execSQL("insert into book(name,author,price,pages)values(?,?,?,?)", new
			   String[]{"战天", "苍天白鹤", "50.8", "3000"});
		db.execSQL("insert into book(name,author,price,pages)values(?,?,?,?)", new
			   String[]{"神雕侠侣", "金庸", "60", "500"});
	}

	private void queryData() {
		Cursor cursor = db.rawQuery("select * from book", null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			buffer.append("ID：").append(cursor.getInt(cursor.getColumnIndex("id"))).append
				   ("\n");
			buffer.append("书名：").append(cursor.getString(1)).append("\n");
			buffer.append("作者：").append(cursor.getString(2)).append("\n");
			buffer.append("价格：").append(cursor.getFloat(cursor.getColumnIndex("price"))).append
				   ("\n");
			buffer.append("页数：").append(cursor.getInt(4)).append("\n");
		}
		data.setText(buffer.toString());


	}

	/**
	 * 如有异常，事务便不能成功，故能实现删除和重新插入同时成功或同时失败
	 */
	private void updateBookTable() {
		db.beginTransaction();
		try {
			db.execSQL("delete from book where id>?", new String[]{"0"});
			if (false) {
				throw new Exception();
			}
			db.execSQL("insert into book(name ,author,price)values(?,?,?)", new String[]{"名人传",
				   "黄益凛", "88.8"});
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}


}
