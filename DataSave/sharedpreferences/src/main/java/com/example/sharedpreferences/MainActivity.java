package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * SharedPreferences 是使用键值对的方式来存储数据的。也就是说当保存一条数据的时候，
 * 需要给这条数据提供一个对应的键，这样在读取数据的时候就可以通过这个键把相应的值取出来。
 * 而且 SharedPreferences 还支持多种不同的数据类型存储，如果存储的数据类型是整型，
 * 那么读取出来的数据也是整型的，存储的数据是一个字符串，读取出来的数据仍然是字符串。
 * Android中主要提供了三种方法用于得到 SharedPreferences 对象:
 * 1. Context 类中的 getSharedPreferences()方法
 * 2. Activity 类中的 getPreferences()方法
 * 3. PreferenceManager 类中的 getDefaultSharedPreferences()方法
 * <p>
 * 主要有两种模式可以选
 * 择，MODE_PRIVATE 和 MODE_MULTI_PROCESS。MODE_PRIVATE 仍然是默认的操
 * 作模式，和直接传入 0 效果是相同的，表示只有当前的应用程序才可以对这个
 * SharedPreferences文件进行读写。MODE_MULTI_PROCESS 则一般是用于会有多个进程中
 * 对同一个 SharedPreferences 文件进行读写的情况
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private Button saveInfo, readInfo;
	private TextView textView;
	private EditText name, age;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveInfo = (Button) findViewById(R.id.button);
		readInfo = (Button) findViewById(R.id.button2);
		textView = (TextView) findViewById(R.id.info);
		name = (EditText) findViewById(R.id.name);
		age = (EditText) findViewById(R.id.age);

		radioGroup = (RadioGroup) findViewById(R.id.radio);

		preferences = getSharedPreferences("data", MODE_PRIVATE);
		editor = preferences.edit();
		saveInfo.setOnClickListener(this);
		readInfo.setOnClickListener(this);
		restore();
	}

	private void restore() {
		name.setText(preferences.getString("name", ""));
		age.setText(String.valueOf(preferences.getInt("age", 0)));
		if (preferences.getBoolean("married", false)) {
			radioGroup.check(R.id.married);
		} else {
			radioGroup.check(R.id.notMarried);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button:
				saveInfo();
				break;
			case R.id.button2:
				textView.setText(readInfo());
				break;
		}
	}

	private void saveInfo() {
		editor.putString("name", name.getText().toString());
		editor.putInt("age", Integer.parseInt(age.getText().toString()));
		if (radioGroup.getCheckedRadioButtonId() == R.id.married) {
			editor.putBoolean("married", true);
		} else if (radioGroup.getCheckedRadioButtonId() == R.id.notMarried) {
			editor.putBoolean("married", false);
		}
		editor.commit();
	}

	private String readInfo() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("名字：").append(preferences.getString("name", "")).append("\n");
		buffer.append("年龄：").append(preferences.getInt("age", 0)).append("\n");
		buffer.append("婚姻：").append(preferences.getBoolean("married", false));
		return buffer.toString();
	}
}
