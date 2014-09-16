package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;
import DAO.Equipe;
import DAO.EquipeAdv;
import DAO.EquipeAdvDAO;
import DAO.EquipeDAO;
import DAO.Partida;
import DAO.PartidaDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import com.example.handcoach.R;

public class TelaMenuScouting extends Activity {
	
	List<Partida> listaPartidas;
	List<Equipe> listaEquipes;
	List<EquipeAdv> listaEquipesAdv;
	int id_eq;
	int id_eqadv;
	Equipe equipe;
	EquipeAdv equipeAdv;
	Intent it;
	Bundle valores;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_scouting);
		
		final Spinner sp_equipes = (Spinner) findViewById(R.id.spinner_equipes);
		final Spinner sp_equipesadv = (Spinner) findViewById(R.id.spinner_equipes_adv);
		final AutoCompleteTextView autoCompleteLocal = (AutoCompleteTextView) findViewById(R.id.autoComplete_local);
		Button btIniciarPartida = (Button) findViewById(R.id.bt_iniciarptda);
		
		listaPartidas = PartidaDAO.getInstancia(TelaMenuScouting.this).buscarTodos();
		ArrayAdapter<Partida> actv_adp = new ArrayAdapter<Partida>(TelaMenuScouting.this, android.R.layout.simple_dropdown_item_1line, listaPartidas);
		autoCompleteLocal.setAdapter(actv_adp);
		
		//check Equipes no BD;
		listaEquipes = EquipeDAO.getInstancia(TelaMenuScouting.this).buscarTodos();
		ArrayAdapter<Equipe> speq_adp = new ArrayAdapter<Equipe>(TelaMenuScouting.this, android.R.layout.simple_spinner_dropdown_item, listaEquipes);
		sp_equipes.setAdapter(speq_adp);
		
		//check EquipesAdv no BD;
		listaEquipesAdv = EquipeAdvDAO.getInstancia(TelaMenuScouting.this).buscarTodos();
		ArrayAdapter<EquipeAdv> speqadv_adp = new ArrayAdapter<EquipeAdv>(TelaMenuScouting.this, android.R.layout.simple_spinner_dropdown_item, listaEquipesAdv);
		sp_equipesadv.setAdapter(speqadv_adp);
		
		
		sp_equipes.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
				equipe = (Equipe) listaEquipes.get(position);
				id_eq = equipe.getId();		
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> adapter) {	}
		});
		
		sp_equipesadv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
				equipeAdv = (EquipeAdv) listaEquipesAdv.get(position);
				id_eqadv = equipeAdv.getId();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {	}
		});
		
		btIniciarPartida.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				valores = new Bundle();
				valores.putString("Local", autoCompleteLocal.getText().toString());
				valores.putInt("id_equipe", id_eq);
				valores.putInt("id_equipeAdv", id_eqadv);
				it = new Intent(TelaMenuScouting.this, TelaPreJogo.class);
				it.putExtras(valores);
				startActivity(it);
				finish();
			}
		});
		
	}

}
