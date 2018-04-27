package com.example.vip.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */

public class HttpUtil {
	public static void sendHttpRequest(final String address, final HttpListener listener) {
		new Thread() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					connection.setConnectTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					BufferedReader reader = new BufferedReader(new InputStreamReader
						   (connection.getInputStream()));
					String str = "", line = "";
					while (null != (line = reader.readLine())) {
						str += line + "\n";
					}
					if (null != listener) {
						listener.onFinish(str);
					}
				} catch (java.io.IOException e) {
					if (null != listener) {
						listener.onError(e);
					}
				} finally {
					if (null != connection) {
						connection.disconnect();
					}
				}


			}
		}.start();
	}

}
