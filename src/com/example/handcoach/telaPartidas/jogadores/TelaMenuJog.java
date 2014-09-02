package com.example.handcoach.telaPartidas.jogadores;

import java.util.List;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TelaMenuJog extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_jog);
		
		ListView listaJog = (ListView) findViewById(R.id.listaJogadoresEquipe);
		Button btNovoJogador = (Button) findViewById(R.id.btNovoJogador);
		
		Intent it = getIntent();
		Bundle valor = it.getExtras();
		final int id_eq = valor.getInt("Equipe");
		
        List<Jogador> listaJogadores = JogadorDAO.getInstancia(TelaMenuJog.this).buscarDaEquipe(id_eq);
		
		final ArrayAdapter<Jogador> adp = new ArrayAdapter<Jogador>(TelaMenuJog.this, android.R.layout.simple_list_item_1, listaJogadores);
		adp.notifyDataSetChanged();
		listaJog.setAdapter(adp);
		
		btNovoJogador.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(TelaMenuJog.this, TelaCadastroJog.class);
				it.putExtra("id_eq", id_eq);
				startActivity(it);
			}
		});
		
		listaJog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it = new Intent(TelaMenuJog.this, TelaEditarJog.class);
				it.putExtra("Jogador", adp.getItem(position).getId());
				startActivity(it);
			}
		});
		
	}

}
