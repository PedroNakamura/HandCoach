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
    
    private TextView nome;
    
    //private ImageView vermelho;
    private ImageView laranja;
    //private ImageView violeta;
    private ImageView verde;
    private TextView campoVm;
    private TextView campoLj;
    private TextView campoVl;
    private TextView campoVe;
    
    private Integer partida1 = 0;
    private Integer partida2 = 0;
    private Integer partida3 = 0;
    private Integer partida4 = 0;
    
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
	    
	    //vermelho = (ImageView) findViewById(R.id.imageView1);
	    //violeta = (ImageView) findViewById(R.id.imageView2);
	    verde = (ImageView) findViewById(R.id.imageView3);
	    laranja = (ImageView) findViewById(R.id.imageView4);
	    campoVm = (TextView) findViewById(R.id.campoVermelho);
	    campoLj = (TextView) findViewById(R.id.campoLaranja);
	    campoVe = (TextView) findViewById(R.id.campoVerde);
	    campoVl = (TextView) findViewById(R.id.campoVioleta);
		
		jogador = JogadorDAO.getInstancia(TelaEvolucaoJog.this).buscarPorID(jogador_id);
		
		nome.setText(jogador.getNome());

		generateData();
	    
	}
	
	private void generateData() {
		
		List<Integer> ultimasPartidas = PartidaDAO.getInstancia(TelaEvolucaoJog.this).retornaQuatroUltimas(id_eq);
		int tamanho = ultimasPartidas.size();
		
		if(tamanho > 0) {
			partida1 = ultimasPartidas.get(0);
		}
		if(tamanho > 1) {
			partida2 = ultimasPartidas.get(1);
		}
    	if(tamanho > 2) {
			partida3 = ultimasPartidas.get(2);
		}	
		if(tamanho > 3) {
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
		
		if(tipo_stats == 2) {
			//passes

			int pss_certo = 0;
			int pss_errado = 0;
			
			List<Column> columns = new ArrayList<Column>();
			
			campoLj.setVisibility(View.INVISIBLE);
			campoVe.setVisibility(View.INVISIBLE);
		    laranja.setVisibility(View.INVISIBLE);
		    verde.setVisibility(View.INVISIBLE);
		    
		    campoVm.setText(getResources().getString(R.string.passecerto));
		    campoVl.setText(getResources().getString(R.string.passerrado));
			
			if(partida1 > 0) {
				List<ColumnValue> values = new ArrayList<ColumnValue>();
				
				pss_certo = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 5 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				pss_errado = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 6 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				
				values.add(new ColumnValue(pss_certo, Utils.COLOR_RED));
				values.add(new ColumnValue(pss_errado, Utils.COLOR_VIOLET));
				
				Column coluna = new Column(values);
				coluna.setHasLabels(hasLabels);
				coluna.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna);			
			}
			
			if(partida2 > 0) {
                List<ColumnValue> values1 = new ArrayList<ColumnValue>();
				
                pss_certo = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 5 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
                pss_errado = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 6 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				
				values1.add(new ColumnValue(pss_certo, Utils.COLOR_RED));
				values1.add(new ColumnValue(pss_errado, Utils.COLOR_VIOLET));
				
				Column coluna1 = new Column(values1);
				coluna1.setHasLabels(hasLabels);
				coluna1.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna1);
			}
			
			if(partida3 > 0) {
                List<ColumnValue> values2 = new ArrayList<ColumnValue>();
				
                pss_certo = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 5 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
                pss_errado = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 6 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				
				values2.add(new ColumnValue(pss_certo, Utils.COLOR_RED));
				values2.add(new ColumnValue(pss_errado, Utils.COLOR_VIOLET));
				
				Column coluna2 = new Column(values2);
				coluna2.setHasLabels(hasLabels);
				coluna2.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna2);
			}
			
			if(partida4 > 0) {
                List<ColumnValue> values3 = new ArrayList<ColumnValue>();
				
                pss_certo = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 5 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
                pss_errado = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 6 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				
				values3.add(new ColumnValue(pss_certo, Utils.COLOR_RED));
				values3.add(new ColumnValue(pss_errado, Utils.COLOR_VIOLET));
				
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
		
		if(tipo_stats == 3) {
			//recepções
			
			int rcp_certa = 0;
			int rcp_errada = 0;
			int rcp_rbdbola = 0;
			int rbt = 0;
			
			List<Column> columns = new ArrayList<Column>();
			
			campoVm.setText(getResources().getString(R.string.rcpcerta));
		    campoVl.setText(getResources().getString(R.string.rcperrada));
		    campoVe.setText(getResources().getString(R.string.desarme));
		    campoLj.setText(getResources().getString(R.string.btRebote));
		    
		    if(partida1 > 0) {
				List<ColumnValue> values = new ArrayList<ColumnValue>();
				
				rcp_certa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 7 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				rcp_errada = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 8 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				rcp_rbdbola = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 9 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				rbt = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 20 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				
				values.add(new ColumnValue(rcp_certa, Utils.COLOR_RED));
				values.add(new ColumnValue(rcp_errada, Utils.COLOR_VIOLET));
				values.add(new ColumnValue(rcp_rbdbola, Utils.COLOR_GREEN));
				values.add(new ColumnValue(rbt, Utils.COLOR_ORANGE));
				
				Column coluna = new Column(values);
				coluna.setHasLabels(hasLabels);
				coluna.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna);			
			}
			
			if(partida2 > 0) {
                List<ColumnValue> values1 = new ArrayList<ColumnValue>();
				
				rcp_certa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 7 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				rcp_errada = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 8 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				rcp_rbdbola = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 9 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				rbt = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 20 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				
				values1.add(new ColumnValue(rcp_certa, Utils.COLOR_RED));
				values1.add(new ColumnValue(rcp_errada, Utils.COLOR_VIOLET));
				values1.add(new ColumnValue(rcp_rbdbola, Utils.COLOR_GREEN));
				values1.add(new ColumnValue(rbt, Utils.COLOR_ORANGE));
				
				Column coluna1 = new Column(values1);
				coluna1.setHasLabels(hasLabels);
				coluna1.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna1);
			}
			
			if(partida3 > 0) {
                List<ColumnValue> values2 = new ArrayList<ColumnValue>();
				
				rcp_certa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 7 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				rcp_errada = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 8 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				rcp_rbdbola = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 9 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				rbt = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 20 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				
				values2.add(new ColumnValue(rcp_certa, Utils.COLOR_RED));
				values2.add(new ColumnValue(rcp_errada, Utils.COLOR_VIOLET));
				values2.add(new ColumnValue(rcp_rbdbola, Utils.COLOR_GREEN));
				values2.add(new ColumnValue(rbt, Utils.COLOR_ORANGE));
				
				Column coluna2 = new Column(values2);
				coluna2.setHasLabels(hasLabels);
				coluna2.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna2);
			}
			
			if(partida4 > 0) {
                List<ColumnValue> values3 = new ArrayList<ColumnValue>();
				
				rcp_certa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 7 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				rcp_errada = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 8 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				rcp_rbdbola = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 9 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				rbt = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 20 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				
				values3.add(new ColumnValue(rcp_certa, Utils.COLOR_RED));
				values3.add(new ColumnValue(rcp_errada, Utils.COLOR_VIOLET));
				values3.add(new ColumnValue(rcp_rbdbola, Utils.COLOR_GREEN));
				values3.add(new ColumnValue(rbt, Utils.COLOR_ORANGE));
				
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
		
		if(tipo_stats == 4) {
			//faltas
			
			int ft_tecnica = 0;
			int ft_defesa = 0;
			int ft_ataque = 0;
			int ft_7m = 0;
			
			List<Column> columns = new ArrayList<Column>();
			
			campoVm.setText(getResources().getString(R.string.faltatecnica));
		    campoVl.setText(getResources().getString(R.string.faltadefesa));
		    campoVe.setText(getResources().getString(R.string.faltaatk));
		    campoLj.setText(getResources().getString(R.string.falta7m));
		    
		    if(partida1 > 0) {
                List<ColumnValue> values = new ArrayList<ColumnValue>();
				
                ft_tecnica = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 10 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
                ft_defesa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 11 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
                ft_ataque = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 12 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
                ft_7m = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 13 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				
				values.add(new ColumnValue(ft_tecnica, Utils.COLOR_RED));
				values.add(new ColumnValue(ft_defesa, Utils.COLOR_VIOLET));
				values.add(new ColumnValue(ft_ataque, Utils.COLOR_GREEN));
				values.add(new ColumnValue(ft_7m, Utils.COLOR_ORANGE));
				
				Column coluna = new Column(values);
				coluna.setHasLabels(hasLabels);
				coluna.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna);
		    }
		    
		    if(partida2 > 0) {
                List<ColumnValue> values1 = new ArrayList<ColumnValue>();
				
                ft_tecnica = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 10 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
                ft_defesa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 11 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
                ft_ataque = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 12 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
                ft_7m = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 13 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				
				values1.add(new ColumnValue(ft_tecnica, Utils.COLOR_RED));
				values1.add(new ColumnValue(ft_defesa, Utils.COLOR_VIOLET));
				values1.add(new ColumnValue(ft_ataque, Utils.COLOR_GREEN));
				values1.add(new ColumnValue(ft_7m, Utils.COLOR_ORANGE));
				
				Column coluna1 = new Column(values1);
				coluna1.setHasLabels(hasLabels);
				coluna1.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna1);
		    }
		    
		    if(partida3 > 0) {
                List<ColumnValue> values2 = new ArrayList<ColumnValue>();
				
                ft_tecnica = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 10 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
                ft_defesa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 11 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
                ft_ataque = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 12 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
                ft_7m = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 13 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				
				values2.add(new ColumnValue(ft_tecnica, Utils.COLOR_RED));
				values2.add(new ColumnValue(ft_defesa, Utils.COLOR_VIOLET));
				values2.add(new ColumnValue(ft_ataque, Utils.COLOR_GREEN));
				values2.add(new ColumnValue(ft_7m, Utils.COLOR_ORANGE));
				
				Column coluna2 = new Column(values2);
				coluna2.setHasLabels(hasLabels);
				coluna2.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna2);
		    }
		    
		    if(partida4 > 0) {
                List<ColumnValue> values3 = new ArrayList<ColumnValue>();
				
                ft_tecnica = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 10 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
                ft_defesa = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 11 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
                ft_ataque = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 12 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
                ft_7m = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 13 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				
				values3.add(new ColumnValue(ft_tecnica, Utils.COLOR_RED));
				values3.add(new ColumnValue(ft_defesa, Utils.COLOR_VIOLET));
				values3.add(new ColumnValue(ft_ataque, Utils.COLOR_GREEN));
				values3.add(new ColumnValue(ft_7m, Utils.COLOR_ORANGE));
				
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
		
		if(tipo_stats == 5) {
			//faltas sofridas
			
			int sft_atk = 0;
			int sft_def = 0;
			
			List<Column> columns = new ArrayList<Column>();
			
			campoLj.setVisibility(View.INVISIBLE);
			campoVe.setVisibility(View.INVISIBLE);
		    laranja.setVisibility(View.INVISIBLE);
		    verde.setVisibility(View.INVISIBLE);
		    
		    campoVm.setText(getResources().getString(R.string.sofreatk));
		    campoVl.setText(getResources().getString(R.string.sofredef));
		    
		    if(partida1 > 0) {
                List<ColumnValue> values = new ArrayList<ColumnValue>();
				
                sft_atk = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 16 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
                sft_def = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 17 AND id_jog="+jogador_id+" AND id_ptda="+partida1);
				
				values.add(new ColumnValue(sft_atk, Utils.COLOR_RED));
				values.add(new ColumnValue(sft_def, Utils.COLOR_VIOLET));
				
				Column coluna = new Column(values);
				coluna.setHasLabels(hasLabels);
				coluna.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna);	
		    }
		    
		    if(partida2 > 0) {
                List<ColumnValue> values1 = new ArrayList<ColumnValue>();
				
                sft_atk = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 16 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
                sft_def = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 17 AND id_jog="+jogador_id+" AND id_ptda="+partida2);
				
				values1.add(new ColumnValue(sft_atk, Utils.COLOR_RED));
				values1.add(new ColumnValue(sft_def, Utils.COLOR_VIOLET));
				
				Column coluna1 = new Column(values1);
				coluna1.setHasLabels(hasLabels);
				coluna1.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna1);
		    }
		    
		    if(partida3 > 0) {
                List<ColumnValue> values2 = new ArrayList<ColumnValue>();
				
                sft_atk = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 16 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
                sft_def = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 17 AND id_jog="+jogador_id+" AND id_ptda="+partida3);
				
				values2.add(new ColumnValue(sft_atk, Utils.COLOR_RED));
				values2.add(new ColumnValue(sft_def, Utils.COLOR_VIOLET));
				
				Column coluna2 = new Column(values2);
				coluna2.setHasLabels(hasLabels);
				coluna2.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(coluna2);
		    }
		    
		    if(partida4 > 0) {
                List<ColumnValue> values3 = new ArrayList<ColumnValue>();
				
                sft_atk = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 16 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
                sft_def = EventoDAO.getInstancia(TelaEvolucaoJog.this).retornaContadorQuery("SELECT * FROM evento WHERE id_cat = 17 AND id_jog="+jogador_id+" AND id_ptda="+partida4);
				
				values3.add(new ColumnValue(sft_atk, Utils.COLOR_RED));
				values3.add(new ColumnValue(sft_def, Utils.COLOR_VIOLET));
				
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
