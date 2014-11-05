package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ColumnValue;
import lecho.lib.hellocharts.util.Utils;
import lecho.lib.hellocharts.view.ColumnChartView;

import com.example.handcoach.R;

import DAO.EventoDAO;
import DAO.JogadorDAO;
import DAO.PartidaDAO;
import Entidades.Jogador;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaEvolucaoJog extends Activity {
	
	private Intent it;
	private Bundle valores;
    private int jogador_id;
    private int tipo_stats;
    private int id_eq;
    
    private ColumnChartView columnChart;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    
    private TextView campo1;
    private TextView campo2;
    private TextView campo3;
    private TextView campo4;
    
    private TextView nome;
    
    private ImageView verm;
    private ImageView viol;
    private ImageView verd;
    private ImageView lara;
    
    private Integer partida1;
    private Integer partida2;
    private Integer partida3;
    private Integer partida4;
    
    private Jogador jogador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telaevolucaojog);
		
		it = getIntent();
		valores = it.getExtras();
		jogador_id = (Integer) valores.get("jog");
		tipo_stats = (Integer) valores.get("stats");
		id_eq = (Integer) valores.get("eq");
		
	    columnChart = (ColumnChartView) findViewById(R.id.comboLineChart);
	    nome = (TextView) findViewById(R.id.nomeDoMalandro);
	    
	    verm = (ImageView) findViewById(R.id.imageView1);
	    viol = (ImageView) findViewById(R.id.imageView2);
	    verd = (ImageView) findViewById(R.id.imageView3);
	    lara = (ImageView) findViewById(R.id.imageView4);
		
		campo1 = (TextView) findViewById(R.id.campoVermelho);
		campo2 = (TextView) findViewById(R.id.campoVioleta);
		campo3 = (TextView) findViewById(R.id.campoVerde);
		campo4 = (TextView) findViewById(R.id.campoLaranja);
		
		jogador = JogadorDAO.getInstancia(TelaEvolucaoJog.this).buscarPorID(jogador_id);
		
		nome.setText(jogador.getNome());

		generateData();
	    
	}
	
	private void generateData() {
		
		List<Integer> ultimasPartidas = PartidaDAO.getInstancia(TelaEvolucaoJog.this).retornaQuatroUltimas(id_eq);
		if(ultimasPartidas.get(0) != null) {
			partida1 = ultimasPartidas.get(0);
		}
		if(ultimasPartidas.get(1) != null) {
			partida2 = ultimasPartidas.get(1);
		}
    	if(ultimasPartidas.get(2) != null) {
			partida3 = ultimasPartidas.get(2);
		}	
		if(ultimasPartidas.get(3) != null) {
			partida4 = ultimasPartidas.get(3);
		}
		
		if(tipo_stats == 1) {
			//arrremessos
			
			int gols = 0;
			int defesas = 0;
			int foras = 0;
			int gks = 0;
			
			List<Column> columns = new ArrayList<Column>();
			
			if(partida1 > 0) {
				List<ColumnValue> values = new ArrayList<ColumnValue>();
				
				gols = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				defesas = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				foras = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				gks = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				
				values.add(new ColumnValue(gols, Utils.COLOR_RED));
				values.add(new ColumnValue(defesas, Utils.COLOR_VIOLET));
				values.add(new ColumnValue(foras, Utils.COLOR_GREEN));
				values.add(new ColumnValue(gks, Utils.COLOR_ORANGE));
				
				Column coluna = new Column(values);
				coluna.setHasLabels(hasLabels);
				coluna.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna);			
			}
			
			if(partida2 > 0) {
                List<ColumnValue> values1 = new ArrayList<ColumnValue>();
				
				gols = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				defesas = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				foras = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				gks = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				
				values1.add(new ColumnValue(gols, Utils.COLOR_RED));
				values1.add(new ColumnValue(defesas, Utils.COLOR_VIOLET));
				values1.add(new ColumnValue(foras, Utils.COLOR_GREEN));
				values1.add(new ColumnValue(gks, Utils.COLOR_ORANGE));
				
				Column coluna1 = new Column(values1);
				coluna1.setHasLabels(hasLabels);
				coluna1.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna1);
			}
			
			if(partida3 > 0) {
                List<ColumnValue> values2 = new ArrayList<ColumnValue>();
				
				gols = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				defesas = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				foras = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				gks = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				
				values2.add(new ColumnValue(gols, Utils.COLOR_RED));
				values2.add(new ColumnValue(defesas, Utils.COLOR_VIOLET));
				values2.add(new ColumnValue(foras, Utils.COLOR_GREEN));
				values2.add(new ColumnValue(gks, Utils.COLOR_ORANGE));
				
				Column coluna2 = new Column(values2);
				coluna2.setHasLabels(hasLabels);
				coluna2.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna2);
			}
			
			if(partida4 > 0) {
                List<ColumnValue> values3 = new ArrayList<ColumnValue>();
				
				gols = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 1 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				defesas = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 2 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				foras = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 3 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				gks = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 4 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				
				values3.add(new ColumnValue(gols, Utils.COLOR_RED));
				values3.add(new ColumnValue(defesas, Utils.COLOR_VIOLET));
				values3.add(new ColumnValue(foras, Utils.COLOR_GREEN));
				values3.add(new ColumnValue(gks, Utils.COLOR_ORANGE));
				
				Column coluna3 = new Column(values3);
				coluna3.setHasLabels(hasLabels);
				coluna3.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna3);
			}
			
			data = new ColumnChartData(columns);
			data.setStacked(true);
			
			if(hasAxes) {
			   Axis axisX = new Axis();
		   	   Axis axisY = new Axis().setHasLines(true);
			   if(hasAxesNames) {
			      axisX.setName("Eixo X");
			      axisY.setName("Eixo Y");
			   }
			   data.setAxisXBottom(axisX);
			   data.setAxisYLeft(axisY);
			} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
			}
			
			columnChart.setColumnChartData(data);
			
		}
		
	}
	
}
