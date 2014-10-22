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
	private Fragment fr;
	private Fragment frReservas;
	private Fragment frTitulares;
	private List<Jogador> listaJogadoresDisponiveis;
	private ArrayList<Jogador> idVaiProJogo = new ArrayList<Jogador>();
	private Button btIniciarJogo;
	private Intent it;
	private Bundle valor;
	private Bundle listas = new Bundle();
	private String local;
	private int id_eqAdv;
	private int id_eq;
	private int contador;
	private long tempo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_prejogo);
		valor = getIntent().getExtras();
		id_eq = valor.getInt("id_equipe");
		id_eqAdv = valor.getInt("id_equipeAdv");
		local = valor.getString("Local");
		tempo = Long.parseLong(valor.getString("tempo"));
		
		listaJogadoresDisponiveis = JogadorDAO.getInstancia(TelaPreJogo.this).buscarDaEquipe(id_eq);
		Log.i("DEBUG!", "BUSCOU DO BD A LISTA!");
        frTitulares = new MenuPreJogo_listaTitulares(listaJogadoresDisponiveis);
        frReservas = new MenuPreJogo_listaReservas(listaJogadoresDisponiveis);
        
		btIniciarJogo = (Button) findViewById(R.id.bt_iniciarJogo);

		btIniciarJogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				contador = 0;
				for(Jogador joga : listaJogadoresDisponiveis) {
					if(joga.isTitular()) {
						contador++;
					}
				}
				if(contador == 7) {
					for(Jogador joga : listaJogadoresDisponiveis) {
						if(joga.isTitular() || joga.isReserva()) {
							Jogador jogador = joga;
							jogador.setFoto(null);
							jogador.setOutput(joga.getFoto());
							idVaiProJogo.add(jogador);
						}
					}
					listas.putSerializable("jog_joga", (ArrayList<Jogador>) idVaiProJogo);
					listas.putString("Local", local);
					listas.putInt("id_equipe", id_eq);
					listas.putInt("id_equipeAdv", id_eqAdv);
					listas.putLong("tempo", tempo);
					it = new Intent(TelaPreJogo.this, TelaScouting.class);
					it.putExtras(listas);
					startActivity(it);
					finish();
				} else {
					Toast.makeText(TelaPreJogo.this, R.string.avisoTitInsuf, Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	public void selectFrag(View v) {
		if (v == findViewById(R.id.bt_selectTitulares)) {
			fr = frTitulares;
		} else if (v == findViewById(R.id.bt_selectReservas)) {
			for (Jogador joga : listaJogadoresDisponiveis) {
				Log.i("JOGADOR ", joga.getNome()+" - TIT: "+joga.isTitular()+" RES:"+joga.isReserva());
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

