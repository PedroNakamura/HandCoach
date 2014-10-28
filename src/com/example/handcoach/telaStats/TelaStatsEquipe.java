package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.ArcValue;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.view.PieChartView;
import util.LazyAdapterPartida;
import com.example.handcoach.R;
import DAO.EquipeDAO;
import DAO.PartidaDAO;
import Entidades.Equipe;
import Entidades.Partida;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class TelaStatsEquipe extends Activity {
	
	private Intent it;
	private int id;
	private Bundle valor;
	private Equipe equipe;
	private LazyAdapterPartida adap;
	private List<Partida> listaPartidas = new ArrayList<Partida>();
	private Intent itt;
	private PieChartData data;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_stats_equipe);
		
		it = getIntent();
		valor = it.getExtras();
		
		TextView nomeDaEquipe = (TextView) findViewById(R.id.tv_tituloEquipeStats);
		ListView ultimasPartidas = (ListView) findViewById(R.id.lv_ultimasPartidasStats);
		ImageButton btStatsJogadores = (ImageButton) findViewById(R.id.btstatsjog);
		ImageButton btStatsEq = (ImageButton) findViewById(R.id.btstatseq);
		PieChartView pieChart = (PieChartView) findViewById(R.id.ChartRelacaoGols);
		
		id = (Integer) valor.get("eq");
		
		listaPartidas = PartidaDAO.getInstancia(this).buscarUltimosTres(id);
		equipe = EquipeDAO.getInstancia(this).buscarPorID(id);
		adap = new LazyAdapterPartida(this, listaPartidas);
		ultimasPartidas.setAdapter(adap);
		
		nomeDaEquipe.setText(equipe.getNome());

		generateData();
		pieChart.setPieChartData(data);
		
		btStatsJogadores.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				itt = new Intent(TelaStatsEquipe.this, TelaStatsDosJogadores.class);
				itt.putExtra("equipe", id);
			    startActivity(itt);
			}
		});
		
		btStatsEq.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				itt = new Intent(TelaStatsEquipe.this, TelaStatsDasEquipes.class);
				itt.putExtra("equipe", id);
				startActivity(itt);
			}
		});
		
	}
	
	private void generateData() {
		List<ArcValue> valores = new ArrayList<ArcValue>();
		ArcValue golsEq = new ArcValue(PartidaDAO.getInstancia(TelaStatsEquipe.this).retornaGols(id));
		ArcValue golEqAdv = new ArcValue(PartidaDAO.getInstancia(TelaStatsEquipe.this).retornaGolsAdv(id));
		valores.add(golsEq);
		valores.add(golEqAdv);
		
		data = new PieChartData(valores);
		data.setCenterText1(getResources().getString(R.string.relacaoGols));
	}

}
