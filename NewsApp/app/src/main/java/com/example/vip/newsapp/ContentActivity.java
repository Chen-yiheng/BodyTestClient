package com.example.vip.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 *
 */

public class ContentActivity extends AppCompatActivity {

	public static void actionStart(Context context, News news) {
		Intent intent = new Intent(context, ContentActivity.class);
		intent.putExtra("title", news.getTitle());
		intent.putExtra("content", news.getContent());
		context.startActivity(intent);

	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
			   .LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_content);
		String title = getIntent().getStringExtra("title");
		String content = getIntent().getStringExtra("content");
		ContentFragment fragment = (ContentFragment) getFragmentManager().findFragmentById(R.id
			   .fragment);
		fragment.refreshNews(new News(title, content));

	}
}
