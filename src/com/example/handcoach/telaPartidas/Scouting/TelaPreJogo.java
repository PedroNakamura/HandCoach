package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import com.example.handcoach.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TelaPreJogo extends Activity {

	FragmentTransaction ft;
	FragmentManager fm;
	Fragment fr;
	Fragment frReservas;
	Fragment frTitulares;
	MenuPreJogo_listaTitulares fragTit;
	MenuPreJogo_listaReservas fragRes;
	Button btTitulares;
	Button btReservas;
	Button btIniciarJogo;
	Intent it;
	Bundle listas = new Bundle();
	boolean autorizacao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_prejogo);
		
        frTitulares = new MenuPreJogo_listaTitulares();
        frReservas = new MenuPreJogo_listaReservas();
        
        fragTit = (MenuPreJogo_listaTitulares) getFragmentManager().findFragmentById(R.id.fragment_place);
        fragRes = (MenuPreJogo_listaReservas) getFragmentManager().findFragmentById(R.id.fragment_place);
        
		btTitulares = (Button) findViewById(R.id.bt_selectTitulares);
		btReservas = (Button) findViewById(R.id.bt_selectReservas);
		btIniciarJogo = (Button) findViewById(R.id.bt_iniciarJogo);

		btIniciarJogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(fragTit.getTitulares().size() != 6) {
					listas.putInt("id_equipe", fragTit.id_eq);
					listas.putIntegerArrayList("jog_joga", (ArrayList<Integer>) fragTit.getTitulares());
					listas.putIntegerArrayList("jog_njoga", (ArrayList<Integer>) fragRes.getReservas());
					it = new Intent(TelaPreJogo.this, TelaScouting.class);
					it.putExtras(listas);
	                startActivity(it);
				} else {
					Toast.makeText(TelaPreJogo.this, R.string.avisoTitInsuf, Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	public void selectFrag(View v) {
		if (v == findViewById(R.id.bt_selectTitulares)) {
			fr = frTitulares;
		} else if (v == findViewById(R.id.bt_selectReservas)) {
			fr = frReservas;
		}

		FragmentManager fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.fragment_place, fr);
		ft.addToBackStack(null);
		ft.commit();
	}


}

/*
 * List<Jogador> listaJogadores; Intent it; Bundle valor; Bundle valor2; int
 * id_eq;
 * 
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); setContentView(R.layout.tela_prejogo);
 * 
 * valor2 = new Bundle(); it = getIntent(); valor = it.getExtras(); id_eq =
 * valor.getInt("id_equipe"); Log.i("DEBUGG", id_eq+" ID da Equipe"); ListView
 * listViewJog = (ListView) findViewById(R.id.preJogoListaJogadoresEquipe);
 * Button btJogar = (Button) findViewById(R.id.bt_preJogo_jogo);
 * 
 * listaJogadores =
 * JogadorDAO.getInstancia(TelaPreJogo.this).buscarDaEquipe(id_eq); final
 * JogadorListaAdapter jogadorAdapter = new JogadorListaAdapter(this,
 * listaJogadores); listViewJog.setAdapter(jogadorAdapter);
 * 
 * btJogar.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) {
 * valor2.putIntegerArrayList("jog_joga", (ArrayList<Integer>)
 * jogadorAdapter.getSelecionados()); valor2.putInt("id_equipe", id_eq); Intent
 * itt = new Intent(TelaPreJogo.this, TelaScouting.class);
 * itt.putExtras(valor2); startActivity(itt); finish(); } });
 * 
 * }
 */

