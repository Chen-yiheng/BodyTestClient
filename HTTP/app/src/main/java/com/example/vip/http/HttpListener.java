package com.example.vip.http;

/**
 *
 */

public interface HttpListener {
	void onFinish(String response);

	void onError(Exception e);
}
