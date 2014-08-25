package com.example.handcoach;

import DAO.CategoriaDAO;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaPartidas extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_partidas);
		TextView txtv = (TextView) findViewById(R.id.textView1);
		txtv.setText(CategoriaDAO.getInstancia(this).buscarTodos().size());
	}

}
