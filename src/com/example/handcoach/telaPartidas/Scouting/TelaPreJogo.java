package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import java.util.List;
import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TelaPreJogo extends Activity {

	private FragmentTransaction ft;
	private FragmentManager fm;
	private Fragment fr;
	private Fragment frReservas;
	private Fragment frTitulares;
	private List<Jogador> listaJogadoresDisponiveis;
	private Button btTitulares;
	private Button btReservas;
	private Button btIniciarJogo;
	private Intent it;
	private Bundle valor;
	private Bundle listas = new Bundle();
	int id_eq;
	boolean autorizacao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_prejogo);
		valor = getIntent().getExtras();
		id_eq = valor.getInt("id_equipe");
		listaJogadoresDisponiveis = JogadorDAO.getInstancia(TelaPreJogo.this).buscarDaEquipe(id_eq);
		Log.i("DEBUG!", "BUSCOU DO BD A LISTA!");
        frTitulares = new MenuPreJogo_listaTitulares(listaJogadoresDisponiveis);
        frReservas = new MenuPreJogo_listaReservas(listaJogadoresDisponiveis);
        
		btTitulares = (Button) findViewById(R.id.bt_selectTitulares);
		btReservas = (Button) findViewById(R.id.bt_selectReservas);
		btIniciarJogo = (Button) findViewById(R.id.bt_iniciarJogo);

		btIniciarJogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(id_eq != 6) {
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
			
			for (Jogador joga : listaJogadoresDisponiveis) {
				
				Log.i("Teste", joga.isTitular()+ "  " + joga.isReserva());
			}
			
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

