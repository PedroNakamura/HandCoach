package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TelaPreJogo extends Activity {

	FragmentTransaction ft;
	FragmentManager fm;
	Fragment fr;
	Fragment frReservas;
	Fragment frTitulares;
	Button btTitulares;
	Button btReservas;
	Button btIniciarJogo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_prejogo);

		btTitulares = (Button) findViewById(R.id.bt_selectTitulares);
		btReservas = (Button) findViewById(R.id.bt_selectReservas);
		btIniciarJogo = (Button) findViewById(R.id.bt_iniciarJogo);

		btIniciarJogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}

	public void selectFrag(View v) {

		if (v == findViewById(R.id.bt_selectTitulares)) {
			fr = new MenuPreJogo_listaTitulares();
		} else if (v == findViewById(R.id.bt_selectReservas)) {
			fr = new MenuPreJogo_listaReservas();
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

