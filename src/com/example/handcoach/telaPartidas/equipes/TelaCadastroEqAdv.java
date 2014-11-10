package com.example.handcoach.telaPartidas.equipes;

import com.example.handcoach.R;
import DAO.EquipeAdvDAO;
import Entidades.EquipeAdv;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastroEqAdv extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro_eqadv);
		
		Button btCadastro = (Button) findViewById(R.id.botaoCadastroEqAdv);
		final EditText nomeEquipe = (EditText) findViewById(R.id.editText_nomeEquipeAdv);
		
		btCadastro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeEq = nomeEquipe.getText().toString();
				EquipeAdv equipe = new EquipeAdv(nomeEq);
                EquipeAdvDAO.getInstancia(TelaCadastroEqAdv.this).Inserir(equipe);
                Toast.makeText(TelaCadastroEqAdv.this, R.string.EquipeAdvAdicionada, Toast.LENGTH_SHORT).show();
                finish();
			}
		});
		
	}

}
