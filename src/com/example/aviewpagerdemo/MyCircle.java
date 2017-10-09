package com.example.aviewpagerdemo;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyCircle extends View{
	private int[] colors = {Color.RED,Color.GRAY,Color.GREEN,Color.BLUE,Color.BLACK};
	private int length = 0;
	private int decent =0;
	private Paint paint;
	private RectF rect;
	private boolean clearFlag = false;
	
	public MyCircle(Context context)
	{
		this(context, null);
	}
	
	public MyCircle(Context context,AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	
	public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(3);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(clearFlag)
			setClear(canvas);
		else {
			drawCircle(canvas, decent, rect);
		}
	}
	
	public void drawCircle(Canvas canvas ,int decent,RectF rect)
	{
		for(int i=0;i<decent/360+1;i++)
		{
			paint.setColor(colors[i%colors.length]);
			length = 40+60*i;
			rect = new RectF(getWidth()/2-length, getHeight()/2-length, getWidth()/2+length, getHeight()/2+length);
			if(i==decent/360)
			{
				canvas.drawArc(rect, 0, (float)decent%360, false, paint);
			}
			else 
			{
				canvas.drawArc(rect, 0, 360f, false, paint);
			}
		}
	}
	
	public void setDecent(int decent,boolean clearFlag)
	{
		this.decent = decent;
		this.clearFlag = clearFlag;
		postInvalidate();
	}
	
	public void setClear(Canvas canvas)
	{
		canvas.drawColor(Color.WHITE);
	}

}
