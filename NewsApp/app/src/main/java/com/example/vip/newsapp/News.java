package com.example.vip.newsapp;

/**
 * 新闻类
 *
 */

public class News {
	private String title;
	private String content;

	public News( String title,String content) {
		this.content = content;
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}
}
