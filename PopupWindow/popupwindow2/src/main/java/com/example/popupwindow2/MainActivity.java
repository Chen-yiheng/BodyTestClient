package com.example.popupwindow2;

import android.app.usage.UsageEvents;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private PopupWindow popupWindow;
	private View menuView;
	private Button menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		menu = (Button) findViewById(R.id.menu);
		menuView = getLayoutInflater().inflate(R.layout.menu_my, null, false);
		popupWindow = new PopupWindow(menuView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar
			   .LayoutParams.WRAP_CONTENT, true);

		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

		popupWindow.getContentView().setFocusableInTouchMode(true);
		popupWindow.getContentView().setFocusable(true);
		popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
					   && event.getAction() == KeyEvent.ACTION_DOWN) {
					if (popupWindow != null && popupWindow.isShowing()) {
						popupWindow.dismiss();
					}
					return true;
				}
				return false;
			}
		});
		menu.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
			if (popupWindow != null && !popupWindow.isShowing()) {
				popupWindow.showAsDropDown(menu, 0, -300);
//				popupWindow.showAtLocation(findViewById(R.id.activity_main), Gravity.BOTTOM,
//					   0, 0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		if (!popupWindow.isShowing()) {
			popupWindow.showAsDropDown(v, 0, -300);
		}

	}
}
