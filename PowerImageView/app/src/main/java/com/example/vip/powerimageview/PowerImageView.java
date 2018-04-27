package com.example.vip.powerimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.icu.text.DateFormat;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * 自定义的继承ImageView的具有播放gif图片的view
 *
 *
 */

public class PowerImageView extends ImageView implements View.OnClickListener{
	private Movie mMovie;
	private Bitmap mStartButton;
	private long mMovieStart;
	private int mImageWidth;
	private int mImageHeight;
	private boolean isPlaying;
	private boolean isAutoPlay;


	public PowerImageView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public PowerImageView(Context context) {
		super(context);
	}

	public PowerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
			   .PowerImageView);
		int resourceId=getResourceId(typedArray,context,attrs);
		if(resourceId!=0){
			InputStream is=getResources().openRawResource(resourceId);
			mMovie=Movie.decodeStream(is);
			if(mMovie!=null){
				isAutoPlay=typedArray.getBoolean(R.styleable.PowerImageView_auto_play,false);
				Bitmap bitmap= BitmapFactory.decodeStream(is);
				mImageHeight=bitmap.getHeight();
				mImageWidth=bitmap.getWidth();
				bitmap.recycle();
				if(!isAutoPlay){
					mStartButton=BitmapFactory.decodeResource(getResources(),R.drawable.start_play);
					setOnClickListener(this);
				}
			}
		}
	}
	private int getResourceId(TypedArray a,Context context,AttributeSet attributeSet){
		try {
			Field field=TypedArray.class.getDeclaredField("mValue");
			field.setAccessible(true);
			TypedValue value= (TypedValue) field.get(a);
			return  value.resourceId;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			if(a!=null){
				a.recycle();
			}
		}
		return 0;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==getId()){
			isPlaying=true;
			invalidate();
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(mMovie!=null){
			setMeasuredDimension(mImageWidth,mImageHeight);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(mMovie==null) {
			super.onDraw(canvas);
		}else {
			if(isAutoPlay){
				playMovice(canvas);
				invalidate();
			}else {
				if(isPlaying){
					if(playMovice(canvas)){
						isPlaying=false;
					}
					invalidate();
				}else {
					mMovie.setTime(0);
					mMovie.draw(canvas,0,0);
					int offsetW=(mImageWidth-mStartButton.getWidth())/2;
					int offsetH=(mImageHeight-mStartButton.getHeight())/2;
					canvas.drawBitmap(mStartButton,offsetW,offsetH,null);
				}
			}

		}
	}

	private boolean playMovice(Canvas canvas){
		long now= SystemClock.uptimeMillis();
		if(mMovieStart==0){
			mMovieStart=now;
		}
		int duration=mMovie.duration();
		if(duration==0){
			duration=1000;
		}
		int relTime=(int) ((now - mMovieStart)%duration);
		mMovie.setTime(relTime);
		mMovie.draw(canvas,0,0);
		if((now-mMovieStart)>=duration){
			mMovieStart=0;
			return true;
		}
		return false;

	}
	
	
}
