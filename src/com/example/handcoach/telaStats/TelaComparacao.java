package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.handcoach.R;

public class TelaComparacao extends FragmentActivity implements ActionBar.TabListener  {
	
	private Intent it;
	private Bundle bdt;
	protected List<Integer> lista = new ArrayList<Integer>();
	protected int id_eq;
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_comparacao);
		
		it = getIntent();
		bdt = it.getExtras();
		lista = bdt.getIntegerArrayList("lista");
		id_eq = bdt.getInt("eq");

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		viewPager.setBackgroundColor(Color.RED);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(getResources().getDrawable(R.drawable.fundo_tab));

		actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.arremessos)).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.passes)).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.recepcoes)).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.faltas)).setTabListener(this));

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}

			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction frag) { }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction frag) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction frag) {}
	
}
