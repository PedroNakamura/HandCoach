package com.example.handcoach.telaPartidas.equipes;

import java.util.List;

import util.LazyAdapterEqAdv;

import com.example.handcoach.R;
import DAO.EquipeAdvDAO;
import Entidades.EquipeAdv;
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

public class TelaMenuEqAdv extends Activity {
	
	private List<EquipeAdv> listaEquipesAdv;
	private LazyAdapterEqAdv adp;
	private ListView lista;
	private Button btTelaCadastroEq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_menu_eqadv);
		
		btTelaCadastroEq = (Button) findViewById(R.id.botaoTelaCadastroEqAdv);
		lista = (ListView) findViewById(R.id.listEquipeCadastradaAdv);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				EquipeAdv equipeSelecionado = (EquipeAdv) adp.getItem(position);
				Intent it = new Intent(TelaMenuEqAdv.this, TelaEditarEqAdv.class);
				it.putExtra("Equipe", equipeSelecionado.getId());
				startActivity(it);
			}
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				final int posicao = position;
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaMenuEqAdv.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.btDeletar);
				alerta.setMessage(R.string.AlertaCtzDelete);
				alerta.setPositiveButton(R.string.btSimAlertaSobre, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EquipeAdv equipeSelecionado = (EquipeAdv) adp.getItem(posicao);
						EquipeAdv equipe = EquipeAdvDAO.getInstancia(TelaMenuEqAdv.this).buscarPorID(equipeSelecionado.getId());
						EquipeAdvDAO.getInstancia(TelaMenuEqAdv.this).Deletar(equipe);
						Toast.makeText(TelaMenuEqAdv.this, R.string.alertaDeletado, Toast.LENGTH_SHORT).show();
						adp.remove(equipeSelecionado);
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
				Intent it = new Intent(TelaMenuEqAdv.this, TelaCadastroEqAdv.class);
				startActivity(it);
			}
		});
	}
	
	protected void onResume() {
		super.onResume();
        listaEquipesAdv = EquipeAdvDAO.getInstancia(TelaMenuEqAdv.this).buscarTodos();
		adp = new LazyAdapterEqAdv(this, listaEquipesAdv);
		lista.setAdapter(adp);
	}

}
