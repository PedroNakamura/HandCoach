package com.example.handcoach.telaStats;

import java.util.ArrayList;
import java.util.List;
import util.JogadorListaAdapter;
import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TelaSelectJogadoresComp extends Activity {
	
	private Button btComparar;
	private ListView listaJogadores;
	
	private Intent it;
	private Intent outraTela;
	private Bundle bdt;
	private int id;
	
	private List<Jogador> listaJog = new ArrayList<Jogador>();
	private JogadorListaAdapter listaAdp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_select_comp);
		
		it = getIntent();
		bdt = it.getExtras();
		id = (Integer) bdt.get("eq");
		
		btComparar = (Button) findViewById(R.id.btCompararJogadores);
		listaJogadores = (ListView) findViewById(R.id.listSelectComparar);
		
		listaJog = JogadorDAO.getInstancia(TelaSelectJogadoresComp.this).buscarDaEquipe(id);
		listaAdp = new JogadorListaAdapter(TelaSelectJogadoresComp.this, listaJog);
		listaJogadores.setAdapter(listaAdp);
		
		btComparar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listaAdp.getSelecionados().size() < 0) {
					outraTela = new Intent(TelaSelectJogadoresComp.this, TelaComparacao.class);
					outraTela.putIntegerArrayListExtra("lista", (ArrayList<Integer>) listaAdp.getSelecionados());
					startActivity(outraTela);
				} else {
					Toast.makeText(TelaSelectJogadoresComp.this, R.string.avisoComparacao, Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

}
