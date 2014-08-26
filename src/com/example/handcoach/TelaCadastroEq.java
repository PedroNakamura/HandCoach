package com.example.handcoach;

import java.util.List;

import DAO.Equipe;
import DAO.EquipeDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TelaCadastroEq extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro_eq);
		
		Button btCadastro = (Button) findViewById(R.id.botaoCadastroEq);
		final EditText nomeEquipe = (EditText) findViewById(R.id.editText_nomeEquipe);
		
		List<Equipe> listaEquipes = EquipeDAO.getInstancia(TelaCadastroEq.this).buscarTodos();
		
		btCadastro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeEq = nomeEquipe.getText().toString();
                EquipeDAO.getInstancia(TelaCadastroEq.this).Inserir(new Equipe(nomeEq));
			}
		});
		
		
	}

}
