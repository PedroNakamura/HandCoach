package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import java.util.List;
import com.example.handcoach.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	Bundle valor2;
	int id_eq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_prejogo);
		
		valor2 = new Bundle();
		it = getIntent();
		valor = it.getExtras();
		id_eq = valor.getInt("id_equipe");
		Log.i("DEBUGG", id_eq+" ID da Equipe");
		ListView listViewJog = (ListView) findViewById(R.id.preJogoListaJogadoresEquipe);
		Button btJogar = (Button) findViewById(R.id.bt_preJogo_jogo);
		
		listaJogadores = JogadorDAO.getInstancia(TelaPreJogo.this).buscarDaEquipe(id_eq);
		final JogadorListaAdapter jogadorAdapter = new JogadorListaAdapter(this, listaJogadores);
		listViewJog.setAdapter(jogadorAdapter);
		
		btJogar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				valor2.putIntegerArrayList("jog_joga", (ArrayList<Integer>) jogadorAdapter.getSelecionados());
				valor2.putInt("id_equipe", id_eq);
				Intent itt = new Intent(TelaPreJogo.this, TelaScouting.class);
				itt.putExtras(valor2);
				startActivity(itt);
				finish();
			}
		});
		
	}

}
