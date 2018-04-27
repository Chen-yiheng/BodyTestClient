package com.example.vip.datasave;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Android 系统中主要提供了三种方式用于简单地实现数据持久化功能，
 * 即文件存储、SharedPreference 存储以及数据库存储
 * 本次测试文件存储
 * openFileOutput(String str,int i),第一个参数是文件名字，第二个则是模式，
 * 主要有两个值，MODE_PRIVATE表示如果存在改文件，则覆盖其内容
 * MODE_APPEND表示如果存在改文件，则在改文件的内容后面添加内容
 */
public class MainActivity extends AppCompatActivity {
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText);
		String str = loadEditText();
		if (!TextUtils.isEmpty(str)) {
			editText.setText(str);
			editText.setSelection(str.length());
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		saveEditText(editText.getText().toString());
	}

	private void saveEditText(String str) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			fos = openFileOutput("data", Context.MODE_PRIVATE);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bw) {
					bw.close();
				}
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private String loadEditText() {
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		FileInputStream fis = null;
		try {
			fis = openFileInput("data");
			br = new BufferedReader(new InputStreamReader(fis));
			String str;
			while (null != (str = br.readLine())) {
				buffer.append(str);
			}
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
