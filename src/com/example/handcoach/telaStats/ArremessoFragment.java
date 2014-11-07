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

public class ArremessoFragment extends Fragment {
	
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
		View rootView = inflater.inflate(R.layout.fragment_arremessos, container, false);
		
		tela = (TelaComparacao) getActivity();
		id_eq = tela.id_eq;
		lista = tela.lista;
		numJog = lista.size();
		ultimasPartidas = PartidaDAO.getInstancia(getActivity()).retornaQuatroUltimas(id_eq);
		tamanho = ultimasPartidas.size();
		
		blue = (ImageView) rootView.findViewById(R.id.azulC);
		green = (ImageView) rootView.findViewById(R.id.verdeC);
		orange = (ImageView) rootView.findViewById(R.id.amareloC);
		red = (ImageView) rootView.findViewById(R.id.vermelhoC);
		violet = (ImageView) rootView.findViewById(R.id.violetaC);
		
		blueV = (TextView) rootView.findViewById(R.id.jogador1C);
		greenV = (TextView) rootView.findViewById(R.id.jogador2C);
		orangeV = (TextView) rootView.findViewById(R.id.jogador3C);
		redV = (TextView) rootView.findViewById(R.id.jogador4C);
		violetV = (TextView) rootView.findViewById(R.id.jogador5C);
		
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
		
		chart = (LineChartView) rootView.findViewById(R.id.lineChart_arremessos);
		
		addCores();
		
		return rootView;
	}
	
	private void generateData() {
		
		int gols = 0;
		int defesas = 0;
		int foras = 0;
		int gks = 0;
		
		List<Line> lines = new ArrayList<Line>();
		for(int y = 0; y < numJog; y++) {	
			
			contador = 0;
			
			listaJog.add(JogadorDAO.getInstancia(getActivity()).buscarPorID(lista.get(y)));
			List<PointValue> values = new ArrayList<PointValue>();
			for(int x = 0; x < tamanho; x++) {
				listaText.get(y).setText(listaJog.get(y).getNome());
				
				gols = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				defesas = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				foras = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				gks = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));
				
				contador = gols + defesas + foras + gks;
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
				axisY.setName("Arremessos");
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
