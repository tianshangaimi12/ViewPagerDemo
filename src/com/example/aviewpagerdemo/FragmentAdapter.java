package com.example.aviewpagerdemo;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> mfragments;

	public FragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.mfragments=fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mfragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mfragments.size();
	}
}
