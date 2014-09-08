package com.example.handcoach.telaPartidas.jogadores;

import java.text.ParseException;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
				Jogador jogador = new Jogador();
				
				jogador.setIdEq(id_eq);
				jogador.setNome(editNomeJogador.getText().toString());
				Log.i("DEBUG!!!", radioGroupSexo.getCheckedRadioButtonId()+"");
				
				switch(radioGroupSexo.getCheckedRadioButtonId()) {
				case R.id.masculino:
					jogador.setSexo(1);
					Log.i("DEBUG!!!", "MASCULINO");
					break;
				case R.id.feminino:
					jogador.setSexo(0);
					Log.i("DEBUG!!!", "FEMININO");
					break;	
				}
				
				jogador.setAltura(editAlturaJogador.getText().toString());
				String nascimentoJogador = editNascimentoJogador.getText().toString();	
				
				try {
					jogador.setDt_nasc(JogadorDAO.getInstancia(TelaCadastroJog.this).stringToDate(nascimentoJogador));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				JogadorDAO.getInstancia(TelaCadastroJog.this).Inserir(jogador);
				Toast.makeText(TelaCadastroJog.this, R.string.AlertaJogCadastro, Toast.LENGTH_SHORT).show();
				
				finish();
				
			}		
		});
	
	

}
}