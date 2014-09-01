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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TelaMenuEquipes extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_jogadores);
		
		ListView lista = (ListView) findViewById(R.id.listaEquipesJogadores);
		
        List<Equipe> listaEquipes = EquipeDAO.getInstancia(TelaMenuEquipes.this).buscarTodos();
		
		final ArrayAdapter<Equipe> adp = new ArrayAdapter<Equipe>(TelaMenuEquipes.this, android.R.layout.simple_list_item_1, listaEquipes);
		lista.setAdapter(adp);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it = new Intent(TelaMenuEquipes.this, TelaMenuJog.class);
				it.putExtra("Equipe", adp.getItem(position).getId());
				startActivity(it);
			}
		});
		
	}

}
