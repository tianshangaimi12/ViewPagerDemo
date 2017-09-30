package com.example.aviewpagerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MyPoint extends View {
	private Paint mPaint1,mPaint2,mPaint3;
	private int currentIndex=0;
	
	public MyPoint(Context context)
	{
		this(context,null);
	}

	public MyPoint(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint1=new Paint();
		mPaint2=new Paint();
		mPaint3=new Paint();
		mPaint1.setColor(Color.YELLOW);
		mPaint2.setColor(Color.YELLOW);
		mPaint3.setColor(Color.YELLOW);
		mPaint1.setAntiAlias(true);
		mPaint2.setAntiAlias(true);
		mPaint3.setAntiAlias(true);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (currentIndex==0) {
			mPaint1.setStyle(Style.FILL);
			mPaint2.setStyle(Style.STROKE);
			mPaint3.setStyle(Style.STROKE);
		}
		else if(currentIndex==1)
		{
			mPaint2.setStyle(Style.FILL);
			mPaint1.setStyle(Style.STROKE);
			mPaint3.setStyle(Style.STROKE);
		}
		else {
			mPaint3.setStyle(Style.FILL);
			mPaint1.setStyle(Style.STROKE);
			mPaint2.setStyle(Style.STROKE);
		}
		canvas.drawCircle(getWidth()/2, getHeight()*0.9f, 10, mPaint2);
		canvas.drawCircle(getWidth()/2-45, getHeight()*0.9f,10, mPaint1);
		canvas.drawCircle(getWidth()/2+45,getHeight()*0.9f,10,mPaint3);
	}
	
	public void setCurrentIndex(int index)
	{
		currentIndex=index;
		postInvalidate();
	}

}
