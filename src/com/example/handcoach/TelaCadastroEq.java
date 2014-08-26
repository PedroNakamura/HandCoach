package com.example.handcoach;


import java.util.List;
import DAO.Equipe;
import DAO.EquipeDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TelaCadastroEq extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro_eq);
		
		Button btCadastro = (Button) findViewById(R.id.botaoCadastroEq);
		ListView lista = (ListView) findViewById(R.id.listEquipeCadastrada);
		final EditText nomeEquipe = (EditText) findViewById(R.id.editText_nomeEquipe);
		
		List<Equipe> listaEquipes = EquipeDAO.getInstancia(TelaCadastroEq.this).buscarTodos();
		
		final ArrayAdapter<Equipe> adp = new ArrayAdapter<Equipe>(TelaCadastroEq.this, android.R.layout.simple_list_item_1, listaEquipes);
		lista.setAdapter(adp);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it = new Intent(TelaCadastroEq.this, TelaEditarEq.class);
				it.putExtra("Equipe", adp.getItem(position).getId());
				startActivity(it);
			}
		});
		
		btCadastro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeEq = nomeEquipe.getText().toString();
                EquipeDAO.getInstancia(TelaCadastroEq.this).Inserir(new Equipe(nomeEq));
                Toast.makeText(TelaCadastroEq.this, R.string.EquipeAdicionada, Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}

}
