package com.example.vip.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * EditText:文本的输入控件，用来输入文本内容
 * text：文本内容
 * TextSize：字体大小
 * <p>
 * EditText继承自TextView，故TextView中的属性，EditText都可以使用
 */
public class MainActivity extends AppCompatActivity {
	private CheckBox checkBox;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittext);
		checkBox = (CheckBox) findViewById(R.id.checkBox);
		editText = (EditText) findViewById(R.id.editText2);
		//可以不显示光标
//        editText.setCursorVisible(false);
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				if (isChecked) {
					//显示明文
					editText.setTransformationMethod(HideReturnsTransformationMethod
						   .getInstance());
				} else {
					//显示密文
					editText.setTransformationMethod(PasswordTransformationMethod.getInstance
                                 ());
				}
				//将光标移到末尾
				editText.setSelection(editText.getText().length());
			}
		});
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int before, int count) {
//                Log.i("Tag", s + "   " + start + "   " + before + "   " + count);
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.i("Tag", s + "   " + start + "   " + before + "   " + count);

			}

			@Override
			public void afterTextChanged(Editable e) {
//              Log.i("Tag", " +++++++++"+e);

				String str = e.toString();
				char[] c = str.toCharArray();
				if (c.length == 11) {
					for (int i = 0; i < c.length - 1; i++) {
//                        Log.i("Tag", "#########"+c[i]);
						if ((!(c[i] >= '0' && c[i] <= '9')) && (!(c[i] >= 'a' && c[i] <=
                                      'Z')) && (!(c[i] >= 'A' && c[i] <= 'Z'))) {
							Toast.makeText(MainActivity.this, "密码不能为数字字母之外的字符", Toast
                                           .LENGTH_SHORT).show();
							break;
						}
					}
				}

			}
		});


	}

}
