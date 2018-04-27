package com.example.cardview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 *
 */

public class FruitActivity extends AppCompatActivity {
	public static final String FRUIT_NAME = "fruitName";
	public static final String FRUIT_IMAGE_ID = "fruitImageId";
	private int fruitImgId;
	private String fruitName;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fruit);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (null != actionBar) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		Intent intent = getIntent();
		fruitName = intent.getStringExtra(FRUIT_NAME);
		fruitImgId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
		TextView fruitContent = (TextView) findViewById(R.id.fruit_content);
		ImageView toolbarImage = (ImageView) findViewById(R.id.toolbar_image);
		final CollapsingToolbarLayout collapsingBar = (CollapsingToolbarLayout) findViewById(R.id
			   .collapsingBar);
		collapsingBar.setTitle(fruitName);
		Glide.with(this).load(fruitImgId).into(toolbarImage);
		fruitContent.setText(getFruitContent(fruitName));
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fruit_fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createDialog();
			}
		});

	}

	private void createDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(fruitImgId).setTitle("您觉得" + fruitName + "怎样?");
		final String[] comments = {"好吃", "还行", "不好吃"};
		builder.setItems(comments, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), comments[which], Toast.LENGTH_SHORT)
					   .show();
			}
		});
		builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private String getFruitContent(String content) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 200; i++) {
			builder.append(content);
		}
		return builder.toString();
	}


}
