package com.example.handcoach;

import java.util.List;

import DAO.Equipe;
import DAO.EquipeDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TelaMenuJogadores extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_jogadores);
		
		ListView lista = (ListView) findViewById(R.id.listaEquipesJogadores);
		
        List<Equipe> listaEquipes = EquipeDAO.getInstancia(TelaMenuJogadores.this).buscarTodos();
		
		final ArrayAdapter<Equipe> adp = new ArrayAdapter<Equipe>(TelaMenuJogadores.this, android.R.layout.simple_list_item_1, listaEquipes);
		lista.setAdapter(adp);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it = new Intent(TelaMenuJogadores.this, TelaMenuJog.class);
				it.putExtra("Equipe", adp.getItem(position).getId());
				startActivity(it);
			}
		});
		
	}

}
