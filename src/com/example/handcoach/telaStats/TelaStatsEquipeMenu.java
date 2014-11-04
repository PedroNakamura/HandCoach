package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
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

public class TelaStatsEquipeMenu extends Activity {
	
	private Intent it;
	private int id;
	private Bundle valor;
	private Equipe equipe;
	private LazyAdapterPartida adap;
	private List<Partida> listaPartidas = new ArrayList<Partida>();
	private Intent itt;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_stats_equipe);
		
		it = getIntent();
		valor = it.getExtras();
		
		TextView nomeDaEquipe = (TextView) findViewById(R.id.tv_tituloEquipeStats);
		ListView ultimasPartidas = (ListView) findViewById(R.id.lv_ultimasPartidasStats);
		ImageButton btStatsJogadores = (ImageButton) findViewById(R.id.btstatsjog);
		ImageButton btStatsEq = (ImageButton) findViewById(R.id.btstatseq);
		ImageButton btStatsData = (ImageButton) findViewById(R.id.btstatsdata);
		ImageButton btComp = (ImageButton) findViewById(R.id.btstatscomp);
		
		id = (Integer) valor.get("eq");
		
		listaPartidas = PartidaDAO.getInstancia(this).buscarUltimosTres(id);
		equipe = EquipeDAO.getInstancia(this).buscarPorID(id);
		adap = new LazyAdapterPartida(this, listaPartidas);
		ultimasPartidas.setAdapter(adap);
		
		nomeDaEquipe.setText(equipe.getNome());
		
		btStatsJogadores.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				itt = new Intent(TelaStatsEquipeMenu.this, TelaStatsDosJogadores.class);
				itt.putExtra("eq", id);
			    startActivity(itt);
			}
		});
		
		btStatsEq.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				itt = new Intent(TelaStatsEquipeMenu.this, TelaStatsDasEquipes.class);
				itt.putExtra("eq", id);
				startActivity(itt);
			}
		});
		
		btStatsData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				itt = new Intent(TelaStatsEquipeMenu.this, TelaStatsSelecaoJog.class);
				itt.putExtra("eq", id);
				startActivity(itt);
			}
		});
		
		btComp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				itt = new Intent(TelaStatsEquipeMenu.this, TelaSelectJogadoresComp.class);
				itt.putExtra("eq", id);
				startActivity(itt);
			}
		});
		
	}

}
