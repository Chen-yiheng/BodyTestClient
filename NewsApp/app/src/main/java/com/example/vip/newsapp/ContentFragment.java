package com.example.vip.newsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 */

public class ContentFragment extends Fragment {
	private TextView newsTitle;
	private TextView newsContent;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		   savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_content, container, false);
		newsTitle = (TextView) view.findViewById(R.id.news_title);
		newsContent = (TextView) view.findViewById(R.id.news_content);
		return view;
	}

	public void refreshNews(News news) {
		newsTitle.setText(news.getTitle());
		newsContent.setText(news.getContent());
	}

}
