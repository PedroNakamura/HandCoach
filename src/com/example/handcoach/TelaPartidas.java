package com.example.handcoach;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TelaPartidas extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_partidas);
		
		ImageButton btScouting = (ImageButton) findViewById(R.id.btMenuPartidas);
		ImageButton btCadastroEq = (ImageButton) findViewById(R.id.btMenuEquipes);
		ImageButton btCadastroJog = (ImageButton) findViewById(R.id.btMenuJogadores);
		ImageButton btTorneios = (ImageButton) findViewById(R.id.btMenuTorneios);
		
		btScouting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TelaPartidas.this, TelaScouting.class);
				startActivity(intent);
			}
		});
		
		btCadastroEq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TelaPartidas.this, TelaCadastroEq.class);
				startActivity(intent);
			}
		});
		
		btCadastroJog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TelaPartidas.this, TelaMenuJogadores.class);
				startActivity(intent);
			}
		});
		
		btTorneios.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaPartidas.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.tituloAlertaTorneios);
				alerta.setMessage(R.string.msgAlertaTreino);
				alerta.setNeutralButton(R.string.btAlertatreino, null);
				alerta.show();
			}
		});
		
	}

}
