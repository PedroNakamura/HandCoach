package com.example.handcoach.telaPartidas.equipes;

import java.util.List;

import com.example.handcoach.R;
import com.example.handcoach.telaPartidas.jogadores.LazyAdapterEq;
import DAO.Equipe;
import DAO.EquipeDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class TelaMenuEq extends Activity {
	
	private List<Equipe> listaEquipes;
	private LazyAdapterEq adp;
	private ListView lista;
	private Button btTelaCadastroEq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_eq);
		
		btTelaCadastroEq = (Button) findViewById(R.id.botaoTelaCadastroEq);
		lista = (ListView) findViewById(R.id.listEquipeCadastrada);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Equipe equipeSelecionado = (Equipe) adp.getItem(position);
				Intent it = new Intent(TelaMenuEq.this, TelaEditarEq.class);
				it.putExtra("Equipe", equipeSelecionado.getId());
				startActivity(it);
			}
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				final int posicao = position;
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaMenuEq.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.btDeletar);
				alerta.setMessage(R.string.AlertaCtzDelete);
				alerta.setPositiveButton(R.string.btSimAlertaSobre, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Equipe equipeSelecionado = (Equipe) adp.getItem(posicao);
						Equipe equipe = EquipeDAO.getInstancia(TelaMenuEq.this).buscarPorID(equipeSelecionado.getId());
						EquipeDAO.getInstancia(TelaMenuEq.this).Deletar(equipe);
						Toast.makeText(TelaMenuEq.this, R.string.alertaDeletado, Toast.LENGTH_SHORT).show();
						adp.remove(equipe);
						lista.setAdapter(adp);
					}
				});
				alerta.setNegativeButton(R.string.btNaoAlertaSobre, null);
				alerta.show();
				return true;
			}
		});
		
		btTelaCadastroEq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(TelaMenuEq.this, TelaCadastroEq.class);
				startActivity(it);
			}
		});
	}
	
	protected void onResume() {
		super.onResume();
        listaEquipes = EquipeDAO.getInstancia(TelaMenuEq.this).buscarTodos();
		adp = new LazyAdapterEq(this, listaEquipes);
		lista.setAdapter(adp);
	}

}
