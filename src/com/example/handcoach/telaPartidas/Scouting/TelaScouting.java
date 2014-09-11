package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;
import com.example.handcoach.R;
import DAO.Jogador;
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
	List<Jogador> listaJog;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		it = getIntent();
		valores = it.getExtras();
		listaJog = (List<Jogador>) valores.getSerializable("jog_joga");
		
		ArrayAdapter<Jogador> adp = new ArrayAdapter<Jogador>(TelaScouting.this, android.R.layout.simple_list_item_1, listaJog);
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		
		lista.setAdapter(adp);
		
	}

}
