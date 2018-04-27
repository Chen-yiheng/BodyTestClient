package com.example.vip.lrcshow.viewgroud;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vip.lrcshow.R;


/**
 * Created by vip on 2017/5/21.
 */

public class TitleView extends FrameLayout {
	private Button mButton;
	private TextView mTitle;

	public TitleView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = LayoutInflater.from(context).inflate(R.layout.title_layout, this);
		mButton = (Button) view.findViewById(R.id.button);
		mTitle = (TextView) view.findViewById(R.id.title);
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) context).finish();
			}
		});
	}

	public void setButtonText(String text) {
		mButton.setText(text);
	}

	public void setmTitleText(String text) {
		mTitle.setText(text);
	}

}
