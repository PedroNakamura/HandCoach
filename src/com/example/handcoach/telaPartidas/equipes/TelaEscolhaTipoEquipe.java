package com.example.handcoach.telaPartidas.equipes;

import com.example.handcoach.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TelaEscolhaTipoEquipe extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_escolha_tipo_equipe);
		
		ImageButton equipeEquipe = (ImageButton) findViewById(R.id.btEquipeEquipe);
		ImageButton equipeAdv = (ImageButton) findViewById(R.id.btEquipeAdv);
		
		equipeEquipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Intent it = new Intent(TelaEscolhaTipoEquipe.this, TelaMenuEq.class);
				startActivity(it);
			}
		});
		
		equipeAdv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(TelaEscolhaTipoEquipe.this, TelaMenuEqAdv.class);
				startActivity(it);
			}
		});
		
	}

}
