package com.example.handcoach.telaPartidas.jogadores;

import com.example.handcoach.R;

import DAO.Jogador;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class TelaEditarJog extends Activity {
	
	Bundle valor;
	int id;
	Intent it;
	Jogador jogador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_editar_jog);		
		
		it = getIntent();
		valor = it.getExtras();
		final int id_eq = valor.getInt("id_eq");
		
		EditText jogadorNome = (EditText) findViewById(R.id.editText_jogadorEditNome);
		RadioGroup jogadorSexo = (RadioGroup) findViewById(R.id.radioGroup_editJogador);
		EditText jogadorAltura = (EditText) findViewById(R.id.editText_jogadorEditAltura);
		EditText jogadorDtNasc = (EditText) findViewById(R.id.editText_jogadorEditDT_Nasc);
		Button btAtualiza = (Button) findViewById(R.id.btEditJogador_Atualiza);
	    Button btDeleta = (Button) findViewById(R.id.btEditJogador_Deleta);
	 
		
	}

}
