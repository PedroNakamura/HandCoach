package com.example.handcoach;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
				
				//O alerta diz a mesma coisa que a opção para partidas! 
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaNav.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.tituloAlertaTreinos);
				alerta.setMessage(R.string.msgAlertaTreino);
				alerta.setNeutralButton(R.string.btAlertatreino, null);
				alerta.show();
				
			}
		});
		
		//Botão Sobre
		btSobre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaNav.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.tituloAlertaSobre);
				alerta.setMessage(R.string.msgAlertaSobre);
				alerta.setPositiveButton(R.string.btSimAlertaSobre, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						Uri uri = Uri.parse("http://pedronakamura.com.br/handcoach");
						Intent it = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(it);
						
					}
				});
				
				alerta.setNegativeButton(R.string.btNaoAlertaSobre, null);
				alerta.show();
				
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
