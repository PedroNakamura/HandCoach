package com.example.handcoach;

import DAO.CategoriaDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TelaPartidas extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_partidas);
		TextView txtv = (TextView) findViewById(R.id.textView1);
		txtv.setText(CategoriaDAO.getInstancia(this).buscarTodos().size()+"");
		
		Button btScouting = (Button) findViewById(R.id.button1);
		Button btCadastroEq = (Button) findViewById(R.id.button2);
		Button btCadastroJog = (Button) findViewById(R.id.button3);
		
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
				Intent intent = new Intent(TelaPartidas.this, TelaCadastroJog.class);
				startActivity(intent);
			}
		});
		
	}

}
