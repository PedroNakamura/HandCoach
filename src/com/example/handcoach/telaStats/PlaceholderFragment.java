package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.ArcValue;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.view.PieChartView.PieChartOnValueTouchListener;
import DAO.EventoDAO;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.handcoach.R;

public class PlaceholderFragment extends Fragment {
	
	private PieChartJogador context;
	private int tipo_stats;
	private int jogador_id;
	private Jogador jogador;

	private PieChartView chart;
	private PieChartData data;
	private TextView titulo;

	private boolean hasLabels = true;
	private boolean hasLabelsOutside = false;
	private boolean hasCenterCircle = true;
	//private boolean hasCenterText1 = true;
	//private boolean hasCenterText2 = false;
	//private boolean isExploaded = false;
	//private boolean hasArcSeparated = false;
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
		titulo = (TextView) rootView.findViewById(R.id.nomeJogadorStats);
		
		jogador = JogadorDAO.getInstancia(context).buscarPorID(jogador_id);
		
		titulo.setText(jogador.getNome());

		generateData();

		return rootView;
	}
	
	private void generateData() {
		if(tipo_stats == 1) {
			//arremessos
			
			int gols = 0;
			int defesas = 0;
			int foras = 0;
			int gks = 0;

			List<ArcValue> values = new ArrayList<ArcValue>();
			
			gols = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1 AND id_jog="+jogador_id);
			defesas = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2 AND id_jog="+jogador_id);
			foras = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3 AND id_jog="+jogador_id);
			gks = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4 AND id_jog="+jogador_id);
			
			if(gols > 0) {
				ArcValue gol = new ArcValue(gols, Utils.COLOR_RED);
				values.add(gol);
			}
			if(defesas > 0) {
				ArcValue defesa = new ArcValue(defesas, Utils.COLOR_VIOLET);
				values.add(defesa);
			}
			if(foras > 0) {
				ArcValue fora = new ArcValue(foras, Utils.COLOR_GREEN);
				values.add(fora);
			}
			if(gks > 0) {
				ArcValue gk = new ArcValue(gks, Utils.COLOR_ORANGE);
				values.add(gk);
			}

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
			
			int pss_certo = 0;
			int pss_errado = 0;
			
			List<ArcValue> values = new ArrayList<ArcValue>();
			
			pss_certo = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 5 AND id_jog="+jogador_id);
			pss_errado = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 6 AND id_jog="+jogador_id);
			
			if(pss_certo > 0) {
				ArcValue pss_c = new ArcValue(pss_certo, Utils.COLOR_BLUE);
				values.add(pss_c);
			}
			if(pss_errado > 0) {
				ArcValue pss_e = new ArcValue(pss_errado, Utils.COLOR_GREEN);
				values.add(pss_e);
			}
			
			data = new PieChartData(values);
			data.setHasLabels(hasLabels);
			data.setHasLabelsOnlyForSelected(hasLabelForSelected);
			data.setHasLabelsOutside(hasLabelsOutside);
			data.setHasCenterCircle(hasCenterCircle);
			
			data.setCenterText1(getResources().getString(R.string.passes));
			// Get font size from dimens.xml and convert it to sp(library uses sp values).
			data.setCenterText1FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

			chart.setPieChartData(data);
			
		} else if(tipo_stats == 3) {
			//recepções
			
			int rcp_certa = 0;
			int rcp_errada = 0;
			int rcp_rbdbola = 0;
			int rbt = 0;
			
			List<ArcValue> values = new ArrayList<ArcValue>();
			
			rcp_certa = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 7 AND id_jog="+jogador_id);
			rcp_errada = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 8 AND id_jog="+jogador_id);
			rcp_rbdbola = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 9 AND id_jog="+jogador_id);
			rbt = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 20 AND id_jog="+jogador_id);
			
			if(rcp_certa > 0) {
				ArcValue rcp_c = new ArcValue(rcp_certa, Utils.COLOR_VIOLET);
				values.add(rcp_c);
			}
			if(rcp_errada > 0) {
				ArcValue rcp_e = new ArcValue(rcp_errada, Utils.COLOR_GREEN);
				values.add(rcp_e);
			}
			if(rcp_rbdbola > 0) {
				ArcValue rcp_rbd = new ArcValue(rcp_rbdbola, Utils.COLOR_RED);
				values.add(rcp_rbd);
			}
			if(rbt > 0) {
				ArcValue rbtt = new ArcValue(rbt, Utils.COLOR_ORANGE);
				values.add(rbtt);
			}
			
			data = new PieChartData(values);
			data.setHasLabels(hasLabels);
			data.setHasLabelsOnlyForSelected(hasLabelForSelected);
			data.setHasLabelsOutside(hasLabelsOutside);
			data.setHasCenterCircle(hasCenterCircle);
			
			data.setCenterText1(getResources().getString(R.string.recepcoes));
			// Get font size from dimens.xml and convert it to sp(library uses sp values).
			data.setCenterText1FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

			chart.setPieChartData(data);
			
		} else if(tipo_stats == 4) {
			//faltas
			
			int ft_tecnica = 0;
			int ft_defesa = 0;
			int ft_ataque = 0;
			int ft_7m = 0;
			
			List<ArcValue> values = new ArrayList<ArcValue>();
			
			ft_tecnica = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 10 AND id_jog="+jogador_id);
			ft_defesa = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 11 AND id_jog="+jogador_id);
			ft_ataque = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 12 AND id_jog="+jogador_id);
			ft_7m = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 13 AND id_jog="+jogador_id);
			
			if(ft_tecnica > 0) {
				ArcValue ft_tec = new ArcValue(ft_tecnica, Utils.COLOR_VIOLET);
				values.add(ft_tec);
			}
			if(ft_defesa > 0) {
				ArcValue ft_def = new ArcValue(ft_defesa, Utils.COLOR_GREEN);
				values.add(ft_def);
			}
			if(ft_ataque > 0) {
				ArcValue ft_atk = new ArcValue(ft_ataque, Utils.COLOR_RED);
				values.add(ft_atk);
			}
			if(ft_7m > 0) {
				ArcValue ft_7 = new ArcValue(ft_7m, Utils.COLOR_ORANGE);
				values.add(ft_7);
			}
			
			data = new PieChartData(values);
			data.setHasLabels(hasLabels);
			data.setHasLabelsOnlyForSelected(hasLabelForSelected);
			data.setHasLabelsOutside(hasLabelsOutside);
			data.setHasCenterCircle(hasCenterCircle);
			
			data.setCenterText1(getResources().getString(R.string.recepcoes));
			// Get font size from dimens.xml and convert it to sp(library uses sp values).
			data.setCenterText1FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

			chart.setPieChartData(data);
			
		} else if(tipo_stats == 5) {
			//faltasofridas
			
			int sft_atk = 0;
			int sft_def = 0;
			
			List<ArcValue> values = new ArrayList<ArcValue>();
			
			sft_atk = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 16 AND id_jog="+jogador_id);
			sft_def = EventoDAO.getInstancia(context).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 17 AND id_jog="+jogador_id);
			
			if(sft_atk > 0) {
				ArcValue sof_at = new ArcValue(sft_atk, Utils.COLOR_RED);
				values.add(sof_at);
			}
			if(sft_def > 0) {
				ArcValue sof_def = new ArcValue(sft_def, Utils.COLOR_ORANGE);
				values.add(sof_def);
			}
			
			data = new PieChartData(values);
			data.setHasLabels(hasLabels);
			data.setHasLabelsOnlyForSelected(hasLabelForSelected);
			data.setHasLabelsOutside(hasLabelsOutside);
			data.setHasCenterCircle(hasCenterCircle);
			
			data.setCenterText1(getResources().getString(R.string.recepcoes));
			// Get font size from dimens.xml and convert it to sp(library uses sp values).
			data.setCenterText1FontSize(Utils.px2sp(getResources().getDisplayMetrics().scaledDensity, (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

			chart.setPieChartData(data);
			
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
