package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
import util.LazyAdapter;
import com.example.handcoach.R;
import com.example.handcoach.telaPartidas.Scouting.quickAction.ActionItem;
import com.example.handcoach.telaPartidas.Scouting.quickAction.QuickAction;
import com.example.handcoach.telaPartidas.Scouting.quickAction.QuickAction.OnActionItemClickListener;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TelaStatsSelecaoJog extends Activity {
	
	private Intent it;
	private Bundle valor;
	private int id;
	private Intent itt;
	
	private List<Jogador> listaJogadores = new ArrayList<Jogador>();
	private LazyAdapter adp;
	private ListView listaJog;
	private int posit;
	
	private QuickAction quickStats;
	private ActionItem arremessos = new ActionItem();
	private ActionItem passes = new ActionItem();
	private ActionItem recepcoes = new ActionItem();
	private ActionItem faltas = new ActionItem();
	private ActionItem soffaltas = new ActionItem();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_select_evolucao_jog);
		
		quickStats = new QuickAction(TelaStatsSelecaoJog.this);
		
		itt = new Intent(TelaStatsSelecaoJog.this, TelaEvolucaoJog.class);
		it = getIntent();
		valor = it.getExtras();
		id = (Integer) valor.get("eq");
		
		listaJog = (ListView) findViewById(R.id.listView_selecaoJog_stats);
		
		listaJogadores = JogadorDAO.getInstancia(TelaStatsSelecaoJog.this).buscarDaEquipe(id);
		adp = new LazyAdapter(this, listaJogadores);
		listaJog.setAdapter(adp);
		
		listaJog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
				
				posit = position;
				createQuick();
				
				quickStats.show(v);
				quickStats.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
				
			}
		});
		
	}
	
	private void createQuick() {
		
		arremessos.setTitle(getResources().getString(R.string.arremessos));
		passes.setTitle(getResources().getString(R.string.passes));
		recepcoes.setTitle(getResources().getString(R.string.recepcoes));
		faltas.setTitle(getResources().getString(R.string.faltas));
		soffaltas.setTitle(getResources().getString(R.string.soffaltas));
		
		quickStats.addActionItem(arremessos);
		quickStats.addActionItem(passes);
		quickStats.addActionItem(recepcoes);
		quickStats.addActionItem(faltas);
		quickStats.addActionItem(soffaltas);
		
		quickStats.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					itt.putExtra("stats", 1);
					itt.putExtra("jog", ((Jogador) adp.getItem(posit)).getId());
					startActivity(itt);
				} else if(pos == 1) {
					itt.putExtra("stats", 2);
					itt.putExtra("jog", ((Jogador) adp.getItem(posit)).getId());
					startActivity(itt);
				} else if(pos == 2) {
					itt.putExtra("stats", 3);
					itt.putExtra("jog", ((Jogador) adp.getItem(posit)).getId());
					startActivity(itt);
				} else if(pos == 3) {
					itt.putExtra("stats", 4);
					itt.putExtra("jog", ((Jogador) adp.getItem(posit)).getId());
					startActivity(itt);
				} else if(pos == 4) {
					itt.putExtra("stats", 5);
					itt.putExtra("jog", ((Jogador) adp.getItem(posit)).getId());
					startActivity(itt);
				}
			}
		});
		
	}

}
