package com.example.handcoach;

import com.example.handcoach.telaPartidas.TelaPartidas;
import com.example.handcoach.telaStats.TelaEscolhaEquipes;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TelaNav extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_nav);
		
		ImageButton btPartidas = (ImageButton) findViewById(R.id.btPartidas);
		ImageButton btTreinos = (ImageButton) findViewById(R.id.btTreinos);
		ImageButton btSobre = (ImageButton) findViewById(R.id.btSobre);
		ImageButton btStats = (ImageButton) findViewById(R.id.btStats);
		
		//Botão Partidas
		btPartidas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(TelaNav.this, TelaPartidas.class);
				startActivity(intent);
				
			}
		});
		
		//Botão Treinos
		btTreinos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaNav.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.tituloAlertaTreinos);
				alerta.setMessage(R.string.msgAlertaTreino);
				alerta.setNeutralButton(R.string.btAlertatreino, null);
				alerta.show();
				
			}
		});
		
		//Botão Estatísticas
		btStats.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent it = new Intent(TelaNav.this, TelaEscolhaEquipes.class);
				startActivity(it);
				
			}
		});
		
		//Botão Sobre
		btSobre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent it = new Intent(TelaNav.this, TelaSobre.class);
				startActivity(it);
				
			}
		});
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_nav, menu);
		return true;
	}

}
