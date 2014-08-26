package com.example.handcoach;

import DAO.Equipe;
import DAO.EquipeDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaEditarEq extends Activity {
	
	Bundle valor;
	int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_editar_eq);
		
		Button btAtualizar = (Button) findViewById(R.id.btAtualizarEquipe);
		Button btDeletar = (Button) findViewById(R.id.btDeletarEquipe);
		final EditText nomeEquipe = (EditText) findViewById(R.id.editText_editarNomeEq);
		
		Intent intent = getIntent();
		valor = intent.getExtras();
		id = (Integer) valor.get("Equipe");
		final Equipe equipe = EquipeDAO.getInstancia(TelaEditarEq.this).buscarPorID(id);
		
		nomeEquipe.setText(equipe.getNome());
		
		btDeletar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EquipeDAO.getInstancia(TelaEditarEq.this).Deletar(equipe);
				Toast.makeText(TelaEditarEq.this, R.string.alertaDeletado, Toast.LENGTH_SHORT).show();
			}
		});
		
		btAtualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Equipe eq = new Equipe(equipe.getId(), nomeEquipe.getText().toString());
				EquipeDAO.getInstancia(TelaEditarEq.this).Editar(equipe, eq);
				Toast.makeText(TelaEditarEq.this, R.string.alertaAtualizado, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
