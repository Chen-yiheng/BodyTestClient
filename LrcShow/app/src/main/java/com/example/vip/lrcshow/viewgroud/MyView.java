package com.example.vip.lrcshow.viewgroud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ，View是不会帮我们绘制内容部分的，因此需要每个视图根据想要展示的内容来自行绘制。
 * 如果你去观察TextView、ImageView等类的源码，你会发现它们都有重写onDraw()这个方法，
 * 并且在里面执行了相当不少的绘制逻辑。绘制的方式主要是借助Canvas这个类，
 * 它会作为参数传入到onDraw()方法中，供给每个视图使用。Canvas这个类的用法非常丰富，
 * 基本可以把它当成一块画布，在上面绘制任意的东西，那么我们就来尝试一下吧。
 */

public class MyView extends View {
	private Paint mPaint;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.RED);
		mPaint.setTextSize(20);
		String text = "天高志远";
		canvas.drawText(text, 0, getHeight() / 2, mPaint);
	}
}
