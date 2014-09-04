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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TelaCadastroEq extends Activity {
	
	private List<Equipe> listaEquipes;
	private LazyAdapterEq adp;
	private ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro_eq);
		
		Button btCadastro = (Button) findViewById(R.id.botaoCadastroEq);
		lista = (ListView) findViewById(R.id.listEquipeCadastrada);
		final EditText nomeEquipe = (EditText) findViewById(R.id.editText_nomeEquipe);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Equipe equipeSelecionado = (Equipe) adp.getItem(position);
				Intent it = new Intent(TelaCadastroEq.this, TelaEditarEq.class);
				it.putExtra("Equipe", equipeSelecionado.getId());
				startActivity(it);
			}
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				final int posicao = position;
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaCadastroEq.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.btDeletar);
				alerta.setMessage(R.string.AlertaCtzDelete);
				alerta.setPositiveButton(R.string.btSimAlertaSobre, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Equipe equipeSelecionado = (Equipe) adp.getItem(posicao);
						Equipe equipe = EquipeDAO.getInstancia(TelaCadastroEq.this).buscarPorID(equipeSelecionado.getId());
						EquipeDAO.getInstancia(TelaCadastroEq.this).Deletar(equipe);
						Toast.makeText(TelaCadastroEq.this, R.string.alertaDeletado, Toast.LENGTH_SHORT).show();
						adp.remove(equipe);
						lista.setAdapter(adp);
					}
				});
				alerta.setNegativeButton(R.string.btNaoAlertaSobre, null);
				alerta.show();
				return true;
			}
		});
		
		btCadastro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeEq = nomeEquipe.getText().toString();
				Equipe equipe = new Equipe(nomeEq);
                EquipeDAO.getInstancia(TelaCadastroEq.this).Inserir(equipe);
                Toast.makeText(TelaCadastroEq.this, R.string.EquipeAdicionada, Toast.LENGTH_SHORT).show();
                nomeEquipe.setText("");
                adp.add(equipe);
                lista.setAdapter(adp);
			}
		});
		
	}
	
	protected void onResume() {
		super.onResume();
        listaEquipes = EquipeDAO.getInstancia(TelaCadastroEq.this).buscarTodos();
		adp = new LazyAdapterEq(this, listaEquipes);
		lista.setAdapter(adp);
	}

}
