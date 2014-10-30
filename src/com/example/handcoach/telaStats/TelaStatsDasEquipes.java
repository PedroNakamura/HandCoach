package com.example.handcoach.telaStats;

import com.example.handcoach.R;

import DAO.EquipeDAO;
import Entidades.Equipe;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TelaStatsDasEquipes extends Activity {
	
	private Intent it;
	private Bundle valor;
	private int id;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_stats_dasequipes);
		
		it = getIntent();
		valor = it.getExtras();
		id = (Integer) valor.get("eq");
		
		Equipe equipe = EquipeDAO.getInstancia(TelaStatsDasEquipes.this).buscarPorID(id);
		
	}
	
}
