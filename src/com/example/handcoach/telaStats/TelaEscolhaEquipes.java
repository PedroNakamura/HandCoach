package com.example.handcoach.telaStats;

import java.util.List;
import util.LazyAdapterEq;
import com.example.handcoach.R;
import DAO.EquipeDAO;
import DAO.PartidaDAO;
import Entidades.Equipe;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TelaEscolhaEquipes extends Activity {
	
	private ListView lista;
	private List<Equipe> listaEquipes;
	private LazyAdapterEq adp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_escolha_equipes_stats);
	    
		if(PartidaDAO.getInstancia(this).buscarTodos().size() == 0) {
			
			Toast.makeText(this, R.string.avisoPartida, Toast.LENGTH_LONG).show();
			
		} else {
			
			lista = (ListView) findViewById(R.id.listaEquipesJogadoresStats);
			
	        listaEquipes = EquipeDAO.getInstancia(TelaEscolhaEquipes.this).buscarTodos();
			adp = new LazyAdapterEq(this, listaEquipes);
			lista.setAdapter(adp);
			
			lista.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent it = new Intent(TelaEscolhaEquipes.this, TelaStatsEquipeMenu.class);
					Equipe equipeSelecionada = (Equipe) adp.getItem(position);
					it.putExtra("eq", equipeSelecionada.getId());
					startActivity(it);
				}
			});
		}
		
	}

}
