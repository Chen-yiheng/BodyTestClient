package com.example.vip.lbs;

/**
 *
 */

public interface HttpListener {
	void onFinish(String response);

	void onError(Exception e);
}
