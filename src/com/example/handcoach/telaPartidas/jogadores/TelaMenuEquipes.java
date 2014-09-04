package com.example.handcoach.telaPartidas.jogadores;

import java.util.List;
import com.example.handcoach.R;
import DAO.Equipe;
import DAO.EquipeDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TelaMenuEquipes extends Activity {
	
	ListView lista;
	List<Equipe> listaEquipes;
	LazyAdapterEq adp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_jogadores);
		
		lista = (ListView) findViewById(R.id.listaEquipesJogadores);
	
        listaEquipes = EquipeDAO.getInstancia(TelaMenuEquipes.this).buscarTodos();
		adp = new LazyAdapterEq(this, listaEquipes);
		lista.setAdapter(adp);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it = new Intent(TelaMenuEquipes.this, TelaMenuJog.class);
				Equipe equipeSelecionada = (Equipe) adp.getItem(position);
				it.putExtra("Equipe", equipeSelecionada.getId());
				startActivity(it);
			}
		});
		
	}

}
