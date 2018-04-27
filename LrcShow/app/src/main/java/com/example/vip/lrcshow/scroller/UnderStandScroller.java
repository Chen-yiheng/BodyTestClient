package com.example.vip.lrcshow.scroller;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.vip.lrcshow.R;

/**
 *
 * Created by vip on 2017/5/20.
 */

public class UnderStandScroller extends AppCompatActivity {
	private static final String TAG = "TestScrollerActivity";
	LinearLayout lay1, lay2, lay0;
	private Scroller mScroller;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScroller = new Scroller(this);
		lay1 = new MyLinearLayout(this);
		lay2 = new MyLinearLayout(this);

		lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.playing));
		lay2.setBackgroundColor(this.getResources().getColor(android.R.color.holo_red_light));
		lay0 = new ContentLinearLayout(this);
		lay0.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams p0 = new LinearLayout.LayoutParams(
			   LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		this.setContentView(lay0, p0);

		LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(
			   LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		p1.weight = 1;
		lay0.addView(lay1, p1);
		LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
			   LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		p2.weight = 1;
		lay0.addView(lay2, p2);

		MyButton btn1 = new MyButton(this);
		MyButton btn2 = new MyButton(this);
		btn1.setText("btn in layout1");
		btn2.setText("btn in layout2");
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				mScroller.startScroll(0, 0, 300, -300, 3000);
				//开始滚动，设置初始位置--》结束位置 & 持续时间
				lay1.scrollBy(-50, -70);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				mScroller.startScroll(20, 20, -200, -200, 1000);
				lay2.scrollBy(-30, -40);
			}
		});
		lay1.addView(btn1);
		lay2.addView(btn2);
	}


	class MyButton extends Button {
		public MyButton(Context ctx) {
			super(ctx);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Log.d(TAG, "MyButton: onDraw------");
		}
	}

	class MyLinearLayout extends LinearLayout {
		public MyLinearLayout(Context ctx) {
			super(ctx);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Log.d(TAG, "MyLinearLayout: onDraw-----------");
		}

		@Override
		public void computeScroll() {
			Log.d(TAG, "MyLinearLayout: computeScroll-----------");
			if (mScroller.computeScrollOffset())//如果mScroller没有调用startScroll，这里将会返回false。
			{
				//因为调用computeScroll函数的是MyLinearLayout实例，
				//所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例
				scrollTo(mScroller.getCurrX(), 0);
				Log.d(TAG, "getCurrX = " + mScroller.getCurrX());

				//继续让系统重绘
				getChildAt(0).invalidate();
			}
		}

	}

	class ContentLinearLayout extends LinearLayout {
		public ContentLinearLayout(Context ctx) {
			super(ctx);
		}

		@Override
		protected void dispatchDraw(Canvas canvas) {
			Log.d(TAG, "ContentLinearLayout: dispatchDraw");
			super.dispatchDraw(canvas);
		}

		@Override
		public void computeScroll() {
			Log.i(TAG, "ContentLinearLayout:computeScroll");
		}

	}


}

