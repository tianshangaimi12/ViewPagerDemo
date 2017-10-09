package com.example.aviewpagerdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment {
	private Button mButton;
	private MyCircle myCircle;
	private boolean isFirstClick = true;
	private Timer mTimer;
	private int decent;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
	
		};
	};
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.layout_one, null);
		mButton = (Button)view.findViewById(R.id.btn);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isFirstClick)
				{
					decent = 0;
					mTimer = new Timer();
					mButton.setText("Stop");
					isFirstClick = false;
					mTimer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							decent+=2;
							if(decent >= 5*360)
								decent = 5*360;
							myCircle.setDecent(decent,false);
							/*Message msg = new Message();
							msg.obj =decent;
							handler.sendMessage(msg);*/
						}
					}, 0,30);
				}
				else {
					mButton.setText("Start");
					myCircle.setDecent(decent, true);
					isFirstClick = true;
					mTimer.cancel();
				}
			}
		});
		myCircle = (MyCircle)view.findViewById(R.id.myCircle);
		return view;
	}

}
