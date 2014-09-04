package com.example.handcoach.telaPartidas.jogadores;

import java.util.List;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TelaMenuJog extends Activity {
	
    List<Jogador> listaJogadores;
	LazyAdapter adp;
	Intent it;
	Bundle valor;
	int id_eq;
	ListView listaJog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_jog);
		
		listaJog = (ListView) findViewById(R.id.listaJogadoresEquipe);
		Button btNovoJogador = (Button) findViewById(R.id.btNovoJogador);
		
		it = getIntent();
		valor = it.getExtras();
		id_eq = valor.getInt("Equipe");
		
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
				it.putExtra("Jogador", ((Jogador) adp.getItem(position)).getId());
				startActivity(it);
			}
		});
		
		listaJog.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
				final int posicao = position;
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaMenuJog.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.btDeletar);
				alerta.setMessage(R.string.AlertaCtzDelete);
				alerta.setPositiveButton(R.string.btSimAlertaSobre, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Jogador jogadorSelecionado = (Jogador)adp.getItem(posicao);
						Jogador jogador = (Jogador)JogadorDAO.getInstancia(TelaMenuJog.this).buscarPorID(jogadorSelecionado.getId());
						JogadorDAO.getInstancia(TelaMenuJog.this).Deletar(jogador);
						Toast.makeText(TelaMenuJog.this, R.string.AlertaCtzDelete, Toast.LENGTH_SHORT).show();
						adp.remove(jogador);
						listaJog.setAdapter(adp);
					}
				});
				alerta.setNegativeButton(R.string.btNaoAlertaSobre, null);
				alerta.show();
				return true;
			}
		});
	
	}
	
	@Override
	protected void onResume() {
		super.onResume();
        listaJogadores = JogadorDAO.getInstancia(TelaMenuJog.this).buscarDaEquipe(id_eq);
		adp = new LazyAdapter(this, listaJogadores);
		listaJog.setAdapter(adp);
	}

}
