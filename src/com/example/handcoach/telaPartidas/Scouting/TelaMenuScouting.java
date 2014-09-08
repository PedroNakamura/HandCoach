package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;
import com.example.handcoach.R;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

public class TelaMenuScouting extends Activity {
	
	List<Partida> listaPartidas;
	List<Equipe> listaEquipes;
	List<EquipeAdv> listaEquipesAdv;
	Intent it;
	Bundle valores;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_scouting);
		
		Spinner sp_equipes = (Spinner) findViewById(R.id.spinner_equipes);
		Spinner sp_equipesadv = (Spinner) findViewById(R.id.spinner_equipes_adv);
		AutoCompleteTextView autoCompleteLocal = (AutoCompleteTextView) findViewById(R.id.autoComplete_local);
		Button btIniciarPartida = (Button) findViewById(R.id.bt_iniciarptda);
		
		//fazer o resto!
		
		btIniciarPartida.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
	}

}
