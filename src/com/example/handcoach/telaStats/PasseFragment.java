package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
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

public class PasseFragment extends Fragment {
	
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
	
	private ImageView green;
	private ImageView orange;
	private ImageView red;
	private ImageView violet;
	
	private Axis axisX;
	private Axis axisY;
	
	private TextView blueV;
	private TextView greenV;
	private TextView orangeV;
	private TextView redV;
	private TextView violetV;
	
	private List<TextView> listaText = new ArrayList<TextView>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_passes, container, false);
		
		tela = (TelaComparacao) getActivity();
		id_eq = tela.id_eq;
		lista = tela.lista;
		numJog = lista.size();
		ultimasPartidas = PartidaDAO.getInstancia(getActivity()).retornaQuatroUltimas(id_eq);
		tamanho = ultimasPartidas.size();
		
		green = (ImageView) rootView.findViewById(R.id.verdeC1);
		orange = (ImageView) rootView.findViewById(R.id.amareloC1);
		red = (ImageView) rootView.findViewById(R.id.vermelhoC1);
		violet = (ImageView) rootView.findViewById(R.id.violetaC1);
		
		blueV = (TextView) rootView.findViewById(R.id.jogador1C1);
		greenV = (TextView) rootView.findViewById(R.id.jogador2C1);
		orangeV = (TextView) rootView.findViewById(R.id.jogador3C1);
		redV = (TextView) rootView.findViewById(R.id.jogador4C1);
		violetV = (TextView) rootView.findViewById(R.id.jogador5C1);
		
		listaText.add(blueV);
		listaText.add(greenV);
		listaText.add(orangeV);
		listaText.add(redV);
		listaText.add(violetV);
		
		if(numJog == 1) {
			green.setVisibility(View.INVISIBLE);
			orange.setVisibility(View.INVISIBLE);
			red.setVisibility(View.INVISIBLE);
			violet.setVisibility(View.INVISIBLE);
			
			greenV.setVisibility(View.INVISIBLE);
			orangeV.setVisibility(View.INVISIBLE);
			redV.setVisibility(View.INVISIBLE);
			violetV.setVisibility(View.INVISIBLE);
	    } else if(numJog == 2) {
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
		
		chart = (LineChartView) rootView.findViewById(R.id.lineChart_passes);
		
		addCores();

		return rootView;
	}
	
	private void generateData() {
		
		int pss_c = 0;
		
        data = new LineChartData();
		
		List<Line> lines = new ArrayList<Line>();
		
		if(hasAxes) {
			axisX = new Axis();
			axisY = new Axis().setHasLines(true);
			if(hasAxesNames) {
				axisX.setName(getResources().getString(R.string.partidas));
				axisY.setName(getResources().getString(R.string.arremessos));
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}
		
		List<AxisValue> eixoX = new ArrayList<AxisValue>();
		List<AxisValue> eixoY = new ArrayList<AxisValue>();
		
		for(int a = 0; a < tamanho; a++) {
			eixoX.add(new AxisValue(a+1));
		}
		
		for(int y = 0; y < numJog; y++) {	
			
			contador = 0;
			
			listaJog.add(JogadorDAO.getInstancia(getActivity()).buscarPorID(lista.get(y)));
			List<PointValue> values = new ArrayList<PointValue>();
			for(int x = 0; x < tamanho; x++) {
				listaText.get(y).setText(listaJog.get(y).getNome());
				
				pss_c = EventoDAO.getInstancia(getActivity()).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 5 AND id_jog="+listaJog.get(y).getId()+" AND id_ptda="+ultimasPartidas.get(x));	
				
				for(int b = 0; b <= pss_c; b++) {
					eixoY.add(new AxisValue(b));
				}

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
		
		axisX.setValues(eixoX);
		axisY.setValues(eixoY);
		
		data.setLines(lines);
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
