package com.example.vip.popupwindow;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private PopupWindow popupWindow;
	private Button menu;
	private Button setting, exit;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		menu = (Button) findViewById(R.id.menu);
		menu.setOnClickListener(this);
		View menuView = getLayoutInflater().inflate(R.layout.menu_my, null, false);
		popupWindow = new PopupWindow(menuView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar
			   .LayoutParams.WRAP_CONTENT);
//		popupWindow.setAnimationStyle(R.style.AnimayionFade);
		//得到焦点，点返回键时，不会退出Activity，如果focusable为true，PopupWindow弹出后，
		// 所有的触屏和物理按键都有PopupWindows处理
//		popupWindow.setFocusable(true);

		//以下三行作用是点击空白处的时候PopupWindow会消失
//		popupWindow.setTouchable(true);
//		popupWindow.setOutsideTouchable(true);
//		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
		setting = (Button) menuView.findViewById(R.id.rc21);
		exit = (Button) menuView.findViewById(R.id.rc24);
		text = (TextView) findViewById(R.id.text);
		setting.setOnClickListener(this);
		exit.setOnClickListener(this);
		text.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.menu:
				if (popupWindow.isShowing()) {
					popupWindow.dismiss();
				} else {
					//这个函数的第一个参数为一个View，我们这里是一个Button，
					// 那么PopupWindow会在这个Button下面显示，xoff，yoff
					// 为显示位置的偏移。
					popupWindow.showAsDropDown(v, 0, 0);
				}
				break;
			case R.id.rc21:
				Toast.makeText(this, "点击了设置", Toast.LENGTH_SHORT).show();
				break;
			case R.id.rc24:
				finish();
				break;
			case R.id.text:
				text.setText("马到功成");
				break;
		}

	}


}
