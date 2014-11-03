package com.example.handcoach.telaStats;

import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaStatsDasEquipes extends Activity {
	
	//private Intent it;
	//private Bundle valor;
	//private int ideq;
	
	private int id_artilheiro;
	private int id_melhorpassador;
	private int id_maisrecep;
	
	private Jogador artilheiro;
	private Jogador melhorpassador;
	private Jogador maisrecep;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_stats_dasequipes);
		
		//it = getIntent();
		//valor = it.getExtras();
		//ideq = (Integer) valor.get("eq");
		
		id_artilheiro = JogadorDAO.getInstancia(this).retornaJogMaiorIncidencia(1);
		artilheiro = JogadorDAO.getInstancia(this).buscarPorID(id_artilheiro);
		
		ImageView fotoArtilheiro = (ImageView) findViewById(R.id.fotoArtilheiro);
		TextView nomeArtilheiro = (TextView) findViewById(R.id.nomeArtilheiro);
		TextView descrArtilheiro = (TextView) findViewById(R.id.descrArtilheiro);
		TextView numArtilheiro = (TextView) findViewById(R.id.numArtilheiro);
		TextView infoArtilheiro = (TextView) findViewById(R.id.infoArtilheiro);
		
		fotoArtilheiro.setImageBitmap(artilheiro.getFoto());
		nomeArtilheiro.setText(artilheiro.getNome()+"");
		descrArtilheiro.setText(artilheiro.getPos()+"");
		numArtilheiro.setText(artilheiro.dateToString()+"");
		infoArtilheiro.setText(artilheiro.getAltura()+"cm");
		
		id_melhorpassador = JogadorDAO.getInstancia(this).retornaJogMaiorIncidencia(5);
		melhorpassador = JogadorDAO.getInstancia(this).buscarPorID(id_melhorpassador);
		
		ImageView fotoMelhorPassador = (ImageView) findViewById(R.id.fotoMelhorPassador);
		TextView nomeMelhorPassador = (TextView) findViewById(R.id.nomeMelhorPassador);
		TextView descrMelhorPassador = (TextView) findViewById(R.id.descrMelhorPassador);
		TextView numMelhorPassador = (TextView) findViewById(R.id.numMelhorPassador);
		TextView infoMelhorPassador = (TextView) findViewById(R.id.infoMelhorPassador);
		
		fotoMelhorPassador.setImageBitmap(melhorpassador.getFoto());
		nomeMelhorPassador.setText(melhorpassador.getNome()+"");
		descrMelhorPassador.setText(melhorpassador.getPos()+"");
		numMelhorPassador.setText(melhorpassador.dateToString()+"");
		infoMelhorPassador.setText(melhorpassador.getAltura()+"cm");
		
		id_maisrecep = JogadorDAO.getInstancia(this).retornaJogMaiorIncidencia(7);
		maisrecep = JogadorDAO.getInstancia(this).buscarPorID(id_maisrecep);
		
		ImageView fotoMaisRecep = (ImageView) findViewById(R.id.fotoMaisRecep);
		TextView nomeMaisRecep = (TextView) findViewById(R.id.nomeMaisRecep);
		TextView descrMaisRecep = (TextView) findViewById(R.id.descrMaisRecep);
		TextView numMaisRecep = (TextView) findViewById(R.id.numMaisRecep);
		TextView infoMaisRecep = (TextView) findViewById(R.id.infoMaisRecep);
		
		fotoMaisRecep.setImageBitmap(maisrecep.getFoto());
		nomeMaisRecep.setText(maisrecep.getNome()+"");
		descrMaisRecep.setText(maisrecep.getPos()+"");
		numMaisRecep.setText(maisrecep.dateToString()+"");
		infoMaisRecep.setText(maisrecep.getAltura()+"cm");
		
	}
	
}
