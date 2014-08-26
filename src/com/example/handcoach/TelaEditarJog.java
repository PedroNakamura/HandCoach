package com.example.handcoach;

import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TelaEditarJog extends Activity {
	
	Bundle valor;
	int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_editar_jog);
		
		Intent intent = getIntent();
		valor = intent.getExtras();
		id = valor.getInt("Jogador");
		
		final Jogador jogador = JogadorDAO.getInstancia(TelaEditarJog.this).buscarPorID(id);
		
		
		
	}

}
