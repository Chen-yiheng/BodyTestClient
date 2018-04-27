package com.vip.litepal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
	private Button createDB, deleteData, queryData;
	private Button addData, updateData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createDB = (Button) findViewById(R.id.create_db);
		createDB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Connector.getDatabase();
			}
		});
		addData = (Button) findViewById(R.id.add_data);
		addData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Book book = new Book();
				book.setAuthor("chengdong");
				book.setName("shengxu");
				book.setPages(800);
				book.setPrice(30);
				book.save();
				//更新的第一种方式,仅更新相同对象的值
				book.setPress("changjiang");
				book.save();
				Category category = new Category(1245, "自然科学类");
				category.save();
			}
		});
		updateData = (Button) findViewById(R.id.update_data);
		updateData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Book book = new Book();
				book.setPrice(40);
				book.updateAll("id=? and pages=?", "3", "800");

				Book book1 = new Book();
				//设置为默认值,不能直接设置为0
				book1.setToDefault("pages");
				book1.updateAll();
			}
		});
		deleteData = (Button) findViewById(R.id.delete_data);
		deleteData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DataSupport.deleteAll(Book.class, "price=?", "30");
			}
		});
		queryData = (Button) findViewById(R.id.query_data);
		queryData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				List<Book> books = DataSupport.findAll(Book.class);
				for (Book book : books) {
					Log.d("tag", book.getName() + "\t" + book.getAuthor() + "\t" + book
						   .getPrice() + "\t" + book.getPages());
				}
				Book firstBook = DataSupport.findFirst(Book.class);
				Book lastBook = DataSupport.findLast(Book.class);
				books = DataSupport.select("price", "name").find(Book.class);
				books = DataSupport.where("id>?", "3").find(Book.class);
				//排序,desc表示降序排列，asc表示升序排列
				books = DataSupport.order("price desc").find(Book.class);
				//查询前四条
				books = DataSupport.limit(4).find(Book.class);
				//从第二条开始查，查四条
				books = DataSupport.limit(4).offset(1).find(Book.class);
				//全部用上也可以
				books = DataSupport.select("name", "author").where("pages>?", "30").order
					   ("pages desc").limit(10).offset(10).find(Book.class);
				//或是还可以使用SQL语句进行查询
				Cursor cursor = DataSupport.findBySQL("select * from Book where pages>? and " +
					   "price<?", "10", "40");
				while (cursor.moveToNext()) {
					Log.i("tag", cursor.getString(cursor.getColumnIndex("name")));
				}
			}

		});

	}
}
