package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.ArcValue;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.view.PieChartView.PieChartOnValueTouchListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.handcoach.R;

public class PlaceholderFragment extends Fragment {
	
	private PieChartJogador context;

	private PieChartView chart;
	private PieChartData data;

	private boolean hasLabels = true;
	private boolean hasLabelsOutside = false;
	private boolean hasCenterCircle = false;
	private boolean hasCenterText1 = false;
	private boolean hasCenterText2 = true;
	private boolean isExploaded = false;
	private boolean hasArcSeparated = false;
	private boolean hasLabelForSelected = false;

	public PlaceholderFragment() {	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);
		
		context = (PieChartJogador) getActivity();

		chart = (PieChartView) rootView.findViewById(R.id.chart);
		chart.setOnValueTouchListener(new ValueTouchListener());

		generateData();

		return rootView;
	}
	
	private void generateData() {
		int numValues = 6;

		List<ArcValue> values = new ArrayList<ArcValue>();
		for (int i = 0; i < numValues; ++i) {
			ArcValue arcValue = new ArcValue((float) Math.random() * 30 + 15, Utils.pickColor());

			if (isExploaded) {
				arcValue.setArcSpacing(24);
			}

			if (hasArcSeparated && i == 0) {
				arcValue.setArcSpacing(32);
			}

			values.add(arcValue);
		}

		data = new PieChartData(values);
		data.setHasLabels(hasLabels);
		data.setHasLabelsOnlyForSelected(hasLabelForSelected);
		data.setHasLabelsOutside(hasLabelsOutside);
		data.setHasCenterCircle(hasCenterCircle);

		if (hasCenterText1) {
			data.setCenterText1("Hello!");
			// Get font size from dimens.xml and convert it to sp(library uses sp values).
			data.setCenterText1FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
		}

		if (hasCenterText2) {
			data.setCenterText2("Charts (Roboto Italic)");
			data.setCenterText2FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
		}

		chart.setPieChartData(data);
	}
	
	private class ValueTouchListener implements PieChartOnValueTouchListener {

		@Override
		public void onValueTouched(int selectedArc, ArcValue value) {
			Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingTouched() {	}

	}
	
}	
