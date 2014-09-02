package com.example.handcoach.telaPartidas.jogadores;

import java.util.Date;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
		final int id = valor.getInt("Jogador");
		
		final EditText jogadorNome = (EditText) findViewById(R.id.editText_jogadorEditNome);
		final RadioGroup jogadorSexo = (RadioGroup) findViewById(R.id.radioGroup_editJogador);
		RadioButton masculino = (RadioButton) findViewById(R.id.masculinoEdit);
		RadioButton feminino = (RadioButton) findViewById(R.id.femininoEdit);
		final EditText jogadorAltura = (EditText) findViewById(R.id.editText_jogadorEditAltura);
		final EditText jogadorDtNasc = (EditText) findViewById(R.id.editText_jogadorEditDT_Nasc);
		Button btAtualiza = (Button) findViewById(R.id.btEditJogador_Atualiza);
	    Button btDeleta = (Button) findViewById(R.id.btEditJogador_Deleta);
	 
	    jogador = JogadorDAO.getInstancia(TelaEditarJog.this).buscarPorID(id);
	    jogadorNome.setText(jogador.getNome());

	    if(jogador.isSexo()) {
	    	masculino.setSelected(true);
	    } else {
	    	feminino.setSelected(true);
	    }
	    jogadorAltura.setText(jogador.getAltura());
	    jogadorDtNasc.setText(jogador.dateToString());
	    
	    btDeleta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JogadorDAO.getInstancia(TelaEditarJog.this).Deletar(jogador);
				Toast.makeText(TelaEditarJog.this, R.string.alertaDeletado, Toast.LENGTH_SHORT).show();
			}
		});
	    
	    btAtualiza.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeJogador = jogadorNome.getText().toString();
				boolean sexoJogador = false;
				switch(jogadorSexo.getCheckedRadioButtonId()) {
				case R.id.masculinoEdit:
					sexoJogador = true;
					break;
				case R.id.femininoEdit:
					sexoJogador = false;
					break;
				}
				String alturaJogador = jogadorAltura.getText().toString();
				String dtNascJogador = jogadorDtNasc.getText().toString();
				
				Date nascJog = new Date();
			    try {
					nascJog = JogadorDAO.getInstancia(TelaEditarJog.this).stringToDate(dtNascJogador);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			    Jogador jogador2 = new Jogador();
			    
			    jogador2.setId(jogador.getId());
			    jogador2.setIdEq(jogador.getIdEq());
			    jogador2.setNome(nomeJogador);
			    jogador2.setSexo(sexoJogador);
			    jogador2.setAltura(alturaJogador);
			    jogador2.setDt_nasc(nascJog);
			    
			    JogadorDAO.getInstancia(TelaEditarJog.this).Editar(jogador, jogador2);
			    
				Toast.makeText(TelaEditarJog.this, R.string.alertaAtualizado, Toast.LENGTH_SHORT).show();
			}
		});
	    
	}

}
