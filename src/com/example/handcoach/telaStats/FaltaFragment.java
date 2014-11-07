package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.LineChartView;

import com.example.handcoach.R;

import DAO.EventoDAO;
import DAO.JogadorDAO;
import DAO.PartidaDAO;
import Entidades.Jogador;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FaltaFragment extends Fragment {
	
	private LineChartView chart;
	private LineChartData data;
	private List<Integer> lista;
	private TelaComparacao tela;
	
	private List<Integer> ultimasPartidas;
	
	private int numJog;
	private int id_eq;
	private int tamanho;
	
	private int contador = 0;
	
	private List<Integer> listaCores = new ArrayList<Integer>();
	private List<Jogador> listaJog = new ArrayList<Jogador>();
	
	private boolean hasAxes = true;
    private boolean hasAxesNames = true;
	private boolean hasLines = true;
	private boolean hasPoints = true;
	private ValueShape shape = ValueShape.CIRCLE;
	private boolean isFilled = false;
	private boolean hasLabels = false;
	private boolean isCubic = false;
	private boolean hasLabelForSelected = false;
	
	private ImageView blue;
	private ImageView green;
	private ImageView orange;
	private ImageView red;
	private ImageView violet;
	
	private TextView blueV;
	private TextView greenV;
	private TextView orangeV;
	private TextView redV;
	private TextView violetV;
	
	private List<TextView> listaText = new ArrayList<TextView>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_faltas, container, false);
		
		tela = (TelaComparacao) getActivity();
		id_eq = tela.id_eq;
		lista = tela.lista;
		numJog = lista.size();
		ultimasPartidas = PartidaDAO.getInstancia(getActivity()).retornaQuatroUltimas(id_eq);
		tamanho = ultimasPartidas.size();
		
		blue = (ImageView) rootView.findViewById(R.id.azulC3);
		green = (ImageView) rootView.findViewById(R.id.verdeC3);
		orange = (ImageView) rootView.findViewById(R.id.amareloC3);
		red = (ImageView) rootView.findViewById(R.id.vermelhoC3);
		violet = (ImageView) rootView.findViewById(R.id.violetaC3);
		
		blueV = (TextView) rootView.findViewById(R.id.jogador1C3);
		greenV = (TextView) rootView.findViewById(R.id.jogador2C3);
		orangeV = (TextView) rootView.findViewById(R.id.jogador3C3);
		redV = (TextView) rootView.findViewById(R.id.jogador4C3);
		violetV = (TextView) rootView.findViewById(R.id.jogador5C3);
		
		listaText.add(blueV);
		listaText.add(greenV);
		listaText.add(orangeV);
		listaText.add(redV);
		listaText.add(violetV);
		
		if(numJog == 2) {
			orange.setVisibility(View.INVISIBLE);
			red.setVisibility(View.INVISIBLE);
			violet.setVisibility(View.INVISIBLE);
			
			orangeV.setVisibility(View.INVISIBLE);
			redV.setVisibility(View.INVISIBLE);
			violetV.setVisibility(View.INVISIBLE);
		} else if(numJog == 3) {
			red.setVisibility(View.INVISIBLE);
			violet.setVisibility(View.INVISIBLE);
			
			redV.setVisibility(View.INVISIBLE);
			violetV.setVisibility(View.INVISIBLE);
		} else if(numJog == 4) {
			violet.setVisibility(View.INVISIBLE);
			
			violetV.setVisibility(View.INVISIBLE);
		} 
		
		chart = (LineChartView) rootView.findViewById(R.id.lineChart_faltas);
		
		addCores();
		
		return rootView;
	}
	
	private void generateData() {
		
		int ft_tecnica = 0;
		int ft_defesa = 0;
		int ft_ataque = 0;
		int ft_7m = 0;
		
		List<Line> lines = new ArrayList<Line>();
		for(int y = 0; y < numJog; y++) {	
			
			contador = 0;
			
			listaJog.add(JogadorDAO.getInstancia(getActivity()).buscarPorID(lista.get(y)));
			List<PointValue> values = new ArrayList<PointValue>();
			for(int x = 0; x < tamanho; x++) {
				listaText.get(y).setText(listaJog.get(y).getNome());
				
				ft_tecnica = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 10 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				ft_defesa = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 11 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				ft_ataque = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 12 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				ft_7m = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 13 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				
				contador = ft_tecnica + ft_defesa + ft_ataque + ft_7m;
				values.add(new PointValue(x, contador));
				
			}
			Line line = new Line(values);
			line.setColor(listaCores.get(y));
			line.setShape(shape);
			line.setCubic(isCubic);
			line.setFilled(isFilled);
			line.setHasLabels(hasLabels);
			line.setHasLabelsOnlyForSelected(hasLabelForSelected);
			line.setHasLines(hasLines);
			line.setHasPoints(hasPoints);
			lines.add(line);	
		}
		
		data = new LineChartData(lines);
		if(hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if(hasAxesNames) {
				axisX.setName("Jogos");
				axisY.setName("Faltas");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}
		data.setBaseValue(Float.NEGATIVE_INFINITY);
		chart.setLineChartData(data);
		
	}
	
	private void addCores() {
		listaCores.add(Utils.COLOR_BLUE);
		listaCores.add(Utils.COLOR_GREEN);
		listaCores.add(Utils.COLOR_ORANGE);
		listaCores.add(Utils.COLOR_RED);
		listaCores.add(Utils.COLOR_VIOLET);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		generateData();
	}

}
