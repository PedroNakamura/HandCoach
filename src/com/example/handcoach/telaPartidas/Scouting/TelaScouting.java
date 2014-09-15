package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import java.util.List;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//http://www.vogella.com/tutorials/AndroidListView/article.html
//http://www.guj.com.br/4372-listview-multiselecionavel-com-checkbox-selecionar-varios-itens-de-uma-listview

public class TelaScouting extends Activity {
	
	Intent it;
	Bundle valores;
	List<Integer> listaJog;
	List<Jogador> jogadores;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		jogadores = new ArrayList<Jogador>();
		it = getIntent();
		valores = it.getExtras();
		listaJog = (List<Integer>) valores.getIntegerArrayList("jog_joga");
		
		for (Integer id : listaJog) {
			Jogador jogador = JogadorDAO.getInstancia(TelaScouting.this).buscarPorID(id);
			jogadores.add(jogador);
		}
		
		ArrayAdapter<Jogador> adp = new ArrayAdapter<Jogador>(TelaScouting.this, android.R.layout.simple_list_item_1, jogadores);
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		
		lista.setAdapter(adp);
		
	}

}
