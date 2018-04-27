package com.example.vip.lrcshow.viewgroud;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 可以看到，ViewGroup中的onLayout()方法竟然是一个抽象方法，
 * 这就意味着所有ViewGroup的子类都必须重写这个方法。
 * 没错，像LinearLayout、RelativeLayout等布局，都是重写了这个方法，
 * 然后在内部按照各自的规则对子视图进行布局的。
 * 由于LinearLayout和RelativeLayout的布局规则都比较复杂，就不单独拿出来进行分析了，
 * 这里我们尝试自定义一个布局，借此来更深刻地理解onLayout()的过程。
 */

public class SimpleLayout extends ViewGroup {

	public SimpleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (getChildCount() > 0) {
			View childView = getChildAt(0);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (getChildCount() > 0) {
			View childView = getChildAt(0);
			childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
		}

	}


}
