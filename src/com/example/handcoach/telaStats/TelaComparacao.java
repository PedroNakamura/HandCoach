package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.LineChartView;
import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class TelaComparacao extends FragmentActivity implements TabListener {
	
	private Intent it;
	private Bundle bdt;
	private List<Jogador> listaJog = new ArrayList<Jogador>();
	private List<Integer> lista = new ArrayList<Integer>();
	
	private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_comparacao);
		
		// PARANAUÊS DO LECHO E DO ANDROID HIVE
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

			public void onPageSelected(int position) {
			   actionBar.setSelectedNavigationItem(position);
			}
		});
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener((android.app.ActionBar.TabListener) this));
			}
		// FIM DOS PARANAUÊS E COMEÇO DOS MEUS
		
		it = getIntent();
		bdt = it.getExtras();
		lista = bdt.getIntegerArrayList("lista");
		
		for(Integer id : lista) {
			listaJog.add(JogadorDAO.getInstancia(TelaComparacao.this).buscarPorID(id));
		}
		
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		
	}
	
	//SECTION PAGER ADAPTER
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		public SectionsPagerAdapter(FragmentManager fm) {
		   super(fm);
		}
		
		public Fragment getItem(int position) {
		   // getItem is called to instantiate the fragment for the given page.
		   // Return a PlaceholderFragment (defined as a static inner class below).
		   return holderFragment.newInstance(position + 1);
		}
		
		public int getCount() {
		   return 5;
		}
		
		public CharSequence getPageTitle(int position) {
		   switch (position) {
		   case 0:
		     return "LineChart";
		   case 1:
		     return "ColumnChart";
		   case 2:
		     return "BubbleChart";
		   case 3:
		     return "PreviewLineChart";
		   case 4:
		     return "PieChart";
		}
		return null;
		}
	}
	
	/**
	* A placeholder fragment containing a simple view.
	*/
	public static class holderFragment extends Fragment {
	/**
	* The fragment argument representing the section number for this fragment.
	*/
	   private static final String ARG_SECTION_NUMBER = "section_number";
	/**
	* Returns a new instance of this fragment for the given section number.
	*/
	   public static holderFragment newInstance(int sectionNumber) {
	      holderFragment fragment = new holderFragment();
	      Bundle args = new Bundle();
	      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
	      fragment.setArguments(args);
	      return fragment;
	   }
	   
	   public holderFragment() { }
	   
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	      View rootView = inflater.inflate(R.layout.fragment_view_pager_charts, container, false);
	      RelativeLayout layout = (RelativeLayout) rootView;
	      int sectionNum = getArguments().getInt(ARG_SECTION_NUMBER);
	      switch (sectionNum) {
	      case 1:
	         LineChartView lineChartView = new LineChartView(getActivity());
	         lineChartView.setLineChartData(generateLineChartData());
	         lineChartView.setZoomType(ZoomType.HORIZONTAL);
	         /** Note: Chart is within ViewPager so enable container scroll mode. **/
	         lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
	         layout.addView(lineChartView);
	         break;
	      case 2:
//	         ColumnChartView columnChartView = new ColumnChartView(getActivity());
//	         columnChartView.setColumnChartData(generateColumnChartData());
//	         columnChartView.setZoomType(ZoomType.HORIZONTAL);
//	         /** Note: Chart is within ViewPager so enable container scroll mode. **/
//	         columnChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//	         layout.addView(columnChartView);
//	         break;
	      case 3:
//	         BubbleChartView bubbleChartView = new BubbleChartView(getActivity());
//	         bubbleChartView.setBubbleChartData(generateBubbleChartData());
//	         bubbleChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
//	         /** Note: Chart is within ViewPager so enable container scroll mode. **/
//	         bubbleChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//	         layout.addView(bubbleChartView);
//	         break;
	      case 4:
//	         PreviewLineChartView previewLineChartView = new PreviewLineChartView(getActivity());
//	         previewLineChartView.setLineChartData(generatePreviewLineChartData());
//	         /** Note: Chart is within ViewPager so enable container scroll mode. **/
//	         previewLineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//	         Viewport tempViewport = new Viewport(previewLineChartView.getMaximumViewport());
//	         float dx = tempViewport.width() / 6;
//	         tempViewport.inset(dx, 0);
//	         previewLineChartView.setCurrentViewport(tempViewport, false);
// 	         previewLineChartView.setZoomType(ZoomType.HORIZONTAL);
//	         layout.addView(previewLineChartView);
//	         break;
	      case 5:
//	         PieChartView pieChartView = new PieChartView(getActivity());
//	         pieChartView.setPieChartData(generatePieChartData());
//	         /** Note: Chart is within ViewPager so enable container scroll mode. **/
//	         pieChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//	         layout.addView(pieChartView);
//	         break;
	   }
	   return rootView;
	}
	}
	
	private static LineChartData generateLineChartData() {
		int numValues = 20;
		List<PointValue> values = new ArrayList<PointValue>();
		for (int i = 0; i < numValues; ++i) {
		   values.add(new PointValue(i, (float) Math.random() * 100f));
		}
		Line line = new Line(values);
		line.setColor(Utils.COLOR_GREEN);
		List<Line> lines = new ArrayList<Line>();
		lines.add(line);
		LineChartData data = new LineChartData(lines);
		data.setAxisXBottom(new Axis().setName("Axis X"));
		data.setAxisYLeft(new Axis().setName("Axis Y").setHasLines(true));
		return data;
	}


}
