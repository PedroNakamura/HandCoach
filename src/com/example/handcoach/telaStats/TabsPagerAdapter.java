package com.example.handcoach.telaStats;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch(index) {
		case 0:
			return new ArremessoFragment();
		case 1:
			return new PasseFragment();
		case 2:
			return new RecepcaoFragment();
		case 3:
			return new FaltaFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 4;
	}

}
