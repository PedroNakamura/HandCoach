package com.example.handcoach.telaStats;

import com.example.handcoach.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaStatsDasEquipes extends Activity {
	
	private Intent it;
	private Bundle valor;
	private int ideq;
	
	private int id_artilheiro;
	private int id_melhorpassador;
	private int id_maisfaltoso;
	private int id_maisrecep;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_stats_dasequipes);
		
		it = getIntent();
		valor = it.getExtras();
		ideq = (Integer) valor.get("eq");
		
		ImageView fotoArtilheiro = (ImageView) findViewById(R.id.fotoArtilheiro);
		TextView nomeArtilheiro = (TextView) findViewById(R.id.nomeArtilheiro);
		TextView descrArtilheiro = (TextView) findViewById(R.id.descrArtilheiro);
		TextView numArtilheiro = (TextView) findViewById(R.id.numArtilheiro);
		TextView infoArtilheiro = (TextView) findViewById(R.id.infoArtilheiro);
		
		ImageView fotoMelhorPassador = (ImageView) findViewById(R.id.fotoMelhorPassador);
		TextView nomeMelhorPassador = (TextView) findViewById(R.id.nomeMelhorPassador);
		TextView descrMelhorPassador = (TextView) findViewById(R.id.descrMelhorPassador);
		TextView numMelhorPassador = (TextView) findViewById(R.id.numMelhorPassador);
		TextView infoMelhorPassador = (TextView) findViewById(R.id.infoMelhorPassador);
		
		ImageView fotoMaisFaltoso = (ImageView) findViewById(R.id.fotoMaisFaltoso);
		TextView nomeMaisFaltoso = (TextView) findViewById(R.id.nomeMaisFaltoso);
		TextView descrMaisFaltoso = (TextView) findViewById(R.id.descrMaisFaltoso);
		TextView numMaisFaltoso = (TextView) findViewById(R.id.numMaisFaltoso);
		TextView infoMaisFaltoso = (TextView) findViewById(R.id.infoMaisFaltoso);
		
		ImageView fotoMaisRecep = (ImageView) findViewById(R.id.fotoMaisRecep);
		TextView nomeMaisRecep = (TextView) findViewById(R.id.nomeMaisRecep);
		TextView descrMaisRecep = (TextView) findViewById(R.id.descrMaisRecep);
		TextView numMaisRecep = (TextView) findViewById(R.id.numMaisRecep);
		TextView infoMaisRecep = (TextView) findViewById(R.id.infoMaisRecep);
		
	}
	
}
