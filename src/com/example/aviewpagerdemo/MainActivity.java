package com.example.aviewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private List<Fragment> mFragments;
	private TextView mTextViewRed;
	private TextView mTextViewGreen;
	private TextView mTextViewBlue;
	private ImageView mImageView;
	private ViewPager mViewPager;
	private int currentIndex;
	private int mImageViewWidth;
	private int offsetWidth;
	private int mcurrentWidth;
	private MyPoint myPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findInit();
        mFragments=new ArrayList<Fragment>();
        mFragments.add(new FragmentOne());
        mFragments.add(new FragmentTwo());
        mFragments.add(new FragmentThree());
        FragmentManager fm=getSupportFragmentManager();
        FragmentAdapter adapter=new FragmentAdapter(fm, mFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				resetTextColor();
				switch (arg0) {
				case 0:
					mTextViewRed.setTextColor(Color.RED);
					break;
				case 1:
					mTextViewGreen.setTextColor(Color.GREEN);
					break;
				case 2:
					mTextViewBlue.setTextColor(Color.BLUE);
					break;
				}
				myPoint.setCurrentIndex(arg0);
				currentIndex=arg0;
			}
			
			
			//左滑时页面向右移动arg0和当前页面一致，右滑时页面向左移动arg0和目标页面一致
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) mImageView.getLayoutParams();
				if(currentIndex==0&&arg0==0)
					params.leftMargin=(int) (arg1*mcurrentWidth)+offsetWidth;
				else if(currentIndex==1&&arg0==1)
					params.leftMargin=(int) (arg1*mcurrentWidth)+mImageViewWidth+3*offsetWidth;
				else if(currentIndex==1&&arg0==0)
					params.leftMargin=-(int) ((1-arg1)*mcurrentWidth)+mImageViewWidth+3*offsetWidth;
				else if(currentIndex==2&&arg0==1)
					params.leftMargin=-(int) ((1-arg1)*mcurrentWidth)+2*mImageViewWidth+5*offsetWidth;
				mImageView.setLayoutParams(params);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
					
			}
		});
        initTitleBar();
    }
    
    public void findInit()
    {
    	mViewPager=(ViewPager)this.findViewById(R.id.viewpager);
    	mTextViewRed=(TextView)this.findViewById(R.id.txt_red);
    	mTextViewGreen=(TextView)this.findViewById(R.id.txt_green);
    	mTextViewBlue=(TextView)this.findViewById(R.id.txt_blue);
    	mImageView=(ImageView)findViewById(R.id.img_title);
    	myPoint=(MyPoint)findViewById(R.id.mypoint);
    	mTextViewRed.setOnClickListener(this);
    	mTextViewBlue.setOnClickListener(this);
    	mTextViewGreen.setOnClickListener(this);
    }
    
    public void initTitleBar()
    {
    	DisplayMetrics metrics=new DisplayMetrics();
    	getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
    	offsetWidth=metrics.widthPixels/12;
    	mImageViewWidth=2*offsetWidth;
    	mcurrentWidth=mImageViewWidth+2*offsetWidth;
    	LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) mImageView.getLayoutParams();
    	params.width=mImageViewWidth;
    	params.leftMargin=offsetWidth;
    	mImageView.setLayoutParams(params);
    }
    
    public void resetTextColor()
    {
    	mTextViewRed.setTextColor(Color.BLACK);
    	mTextViewBlue.setTextColor(Color.BLACK);
    	mTextViewGreen.setTextColor(Color.BLACK);
    }
    
    @Override
    public void onClick(View v) {
    	switch (v.getId()) {
		case R.id.txt_red:
			mViewPager.setCurrentItem(0,true);
			break;
		case R.id.txt_green:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.txt_blue:
			mViewPager.setCurrentItem(2, true);
			break;
		}
    }
}
