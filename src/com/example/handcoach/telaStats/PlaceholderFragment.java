package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.ArcValue;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.view.PieChartView.PieChartOnValueTouchListener;
import DAO.EventoDAO;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.handcoach.R;

public class PlaceholderFragment extends Fragment {
	
	private PieChartJogador context;
	private int tipo_stats;
	private int jogador_id;

	private PieChartView chart;
	private PieChartData data;

	private boolean hasLabels = true;
	private boolean hasLabelsOutside = false;
	private boolean hasCenterCircle = false;
	private boolean hasCenterText1 = true;
	private boolean hasCenterText2 = false;
	private boolean isExploaded = false;
	private boolean hasArcSeparated = false;
	private boolean hasLabelForSelected = false;

	public PlaceholderFragment() {	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);
		
		context = (PieChartJogador) getActivity();
		
		tipo_stats = context.tipo_stats;
		jogador_id = context.jogador_id;

		chart = (PieChartView) rootView.findViewById(R.id.chart);
		chart.setOnValueTouchListener(new ValueTouchListener());

		generateData();

		return rootView;
	}
	
	private void generateData() {
		if(tipo_stats == 1) {
			//arremessos

			List<ArcValue> values = new ArrayList<ArcValue>();
			
			ArcValue gol = new ArcValue(EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1"));
			ArcValue defesa = new ArcValue(EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2"));
			ArcValue fora = new ArcValue(EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3"));
			ArcValue gk = new ArcValue(EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4"));
			
			values.add(gol);
			values.add(defesa);
			values.add(fora);
			values.add(gk);

			data = new PieChartData(values);
			data.setHasLabels(hasLabels);
			data.setHasLabelsOnlyForSelected(hasLabelForSelected);
			data.setHasLabelsOutside(hasLabelsOutside);
			data.setHasCenterCircle(hasCenterCircle);
			
			data.setCenterText1(getResources().getString(R.string.arremessos));
			// Get font size from dimens.xml and convert it to sp(library uses sp values).
			data.setCenterText1FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

			chart.setPieChartData(data);
			
		} else if(tipo_stats == 2) {
			//passes
			
		} else if(tipo_stats == 3) {
			//recepções
			
		} else if(tipo_stats == 4) {
			//faltas
		} else if(tipo_stats == 5) {
			//faltasofridas
		}
	}
	
	private class ValueTouchListener implements PieChartOnValueTouchListener {

		@Override
		public void onValueTouched(int selectedArc, ArcValue value) {
			Toast.makeText(getActivity(), "Selecionado: " + value, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingTouched() {	}

	}
	
}	
