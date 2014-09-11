package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import java.util.List;
import com.example.handcoach.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import DAO.Jogador;
import DAO.JogadorDAO;

public class TelaPreJogo extends Activity {
	
	List<Jogador> listaJogadores;
	Intent it;
	Bundle valor;
	int id_eq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_prejogo);
		
		it = getIntent();
		valor = it.getExtras();
		id_eq = valor.getInt("id_equipe");
		
		ListView listViewJog = (ListView) findViewById(R.id.preJogoListaJogadoresEquipe);
		Button btJogar = (Button) findViewById(R.id.bt_preJogo_jogo);
		
		listaJogadores = JogadorDAO.getInstancia(TelaPreJogo.this).buscarDaEquipe(id_eq);
		final JogadorListaAdapter JogadorAdapter = new JogadorListaAdapter(this, listaJogadores);
		listViewJog.setAdapter(JogadorAdapter);
		
		btJogar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				valor.putSerializable("jog_joga", (ArrayList<Jogador>) JogadorAdapter.getSelecionados());
				Intent itt = new Intent(TelaPreJogo.this, TelaScouting.class);
				itt.putExtras(valor);
				startActivity(itt);
				finish();
			}
		});
		
	}

}
