package com.example.handcoach.telaPartidas.jogadores;

import java.util.Date;
import java.text.ParseException;

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
import android.widget.RadioGroup;
import android.widget.Toast;

public class TelaCadastroJog extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro_jog);
		
		Intent it = getIntent();
		Bundle bdt = it.getExtras();
		final int id_eq = bdt.getInt("id_eq");
		
		final EditText editNomeJogador = (EditText) findViewById(R.id.editTextNomeJogador);
		final RadioGroup radioGroupSexo = (RadioGroup) findViewById(R.id.radioGroupSexo);
		final EditText editAlturaJogador = (EditText) findViewById(R.id.editTextAlturaJogador);
		final EditText editNascimentoJogador = (EditText) findViewById(R.id.editTextNascimentoJogador);
		Button btCadastrar = (Button) findViewById(R.id.btOkCadastroJogador);
		
		btCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeJogador = editNomeJogador.getText().toString();
				boolean sexoJogador = false;
				switch(radioGroupSexo.getCheckedRadioButtonId()) {
				case R.id.masculino:
					sexoJogador = true;
					break;
				case R.id.feminino:
					sexoJogador = false;
					break;	
				}
				String alturaJogador = editAlturaJogador.getText().toString();	
				String nascimentoJogador = editNascimentoJogador.getText().toString();	
				Date nascJog = new Date();
				try {
					nascJog = JogadorDAO.getInstancia(TelaCadastroJog.this).stringToDate(nascimentoJogador);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				JogadorDAO.getInstancia(TelaCadastroJog.this).Inserir(new Jogador(id_eq, nomeJogador, sexoJogador, null, alturaJogador, nascJog));
				Toast.makeText(TelaCadastroJog.this, R.string.AlertaJogCadastrado, Toast.LENGTH_SHORT).show();
			}		
		});
	
	

}
}