package com.example.handcoach.telaPartidas.equipes;

import com.example.handcoach.R;
import DAO.EquipeAdv;
import DAO.EquipeAdvDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaEditarEqAdv extends Activity {
	
	private Intent it;
	private Bundle valor;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_editar_eqadv);
		
		Button btAtualizar = (Button) findViewById(R.id.btAtualizarEquipeAdv);
		final EditText nomeEquipe = (EditText) findViewById(R.id.editText_editarNomeEqAdv);
		
		it = getIntent();
		valor = it.getExtras();
		id = (Integer) valor.get("Equipe");
		Log.i("DEBUG ID:", id+"");
		final EquipeAdv equipe = EquipeAdvDAO.getInstancia(TelaEditarEqAdv.this).buscarPorID(id);
		
		Log.i("DEBUGG!!= ",""+equipe.getNome());
		nomeEquipe.setText(equipe.getNome());
		
		btAtualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EquipeAdv eq = new EquipeAdv(equipe.getId(), nomeEquipe.getText().toString());
				EquipeAdvDAO.getInstancia(TelaEditarEqAdv.this).Editar(equipe, eq);
				Toast.makeText(TelaEditarEqAdv.this, R.string.alertaAtualizado, Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
	}

}
