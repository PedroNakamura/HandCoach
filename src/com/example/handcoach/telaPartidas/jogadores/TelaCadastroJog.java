package com.example.handcoach.telaPartidas.jogadores;

import java.text.ParseException;
import java.util.ArrayList;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class TelaCadastroJog extends Activity {
	
	ImageButton btFoto;
	Bitmap image = null;
	ArrayList<String> posicoes;
	String positionJ = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro_jog);
		
		Intent it = getIntent();
		Bundle bdt = it.getExtras();
		final int id_eq = bdt.getInt("id_eq");
		
		final EditText editNomeJogador = (EditText) findViewById(R.id.editTextNomeJogador);
		final RadioGroup radioGroupSexo = (RadioGroup) findViewById(R.id.radioGroupSexo);
		final EditText editAlturaJogador = (EditText) findViewById(R.id.editTextAlturaJogador);
		final EditText editNascimentoJogador = (EditText) findViewById(R.id.editTextNascimentoJogador);
		final Spinner posJogador = (Spinner) findViewById(R.id.spinnerdeposicoes);
		btFoto = (ImageButton) findViewById(R.id.bt_ftPessoa_img);
		Button btCadastrar = (Button) findViewById(R.id.btOkCadastroJogador);
		
		posicoes.add(getResources().getString(R.string.goleiro));
		posicoes.add(getResources().getString(R.string.armador));
		posicoes.add(getResources().getString(R.string.meia));
		posicoes.add(getResources().getString(R.string.ponta));
		posicoes.add(getResources().getString(R.string.pivo));
		
		ArrayAdapter<String> adp = new ArrayAdapter<String>(TelaCadastroJog.this, android.R.layout.simple_spinner_dropdown_item, posicoes);
		posJogador.setAdapter(adp);
		
		posJogador.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adp, View view, int position, long id) {
				positionJ = posicoes.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		btFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(i, 0);
			}
		});
		
		btCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Jogador jogador = new Jogador();
				
				jogador.setIdEq(id_eq);
				jogador.setNome(editNomeJogador.getText().toString());
				Log.i("DEBUG!!!", radioGroupSexo.getCheckedRadioButtonId()+"");
				
				switch(radioGroupSexo.getCheckedRadioButtonId()) {
				case R.id.masculino:
					jogador.setSexo(1);
					Log.i("DEBUG!!!", "MASCULINO");
					break;
				case R.id.feminino:
					jogador.setSexo(0);
					Log.i("DEBUG!!!", "FEMININO");
					break;	
				}
				
				jogador.setAltura(editAlturaJogador.getText().toString());
				jogador.setFoto(image);
				jogador.setPos(positionJ);
				String nascimentoJogador = editNascimentoJogador.getText().toString();	
				
				try {
					jogador.setDt_nasc(JogadorDAO.getInstancia(TelaCadastroJog.this).stringToDate(nascimentoJogador));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				JogadorDAO.getInstancia(TelaCadastroJog.this).Inserir(jogador);
				Toast.makeText(TelaCadastroJog.this, R.string.AlertaJogCadastro, Toast.LENGTH_SHORT).show();
				
				finish();
				
			}		
		});
	
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null) {
			Bundle bundle = data.getExtras();
			if(bundle != null) {
				Bitmap bitmap = (Bitmap) bundle.get("data");
				image = bitmap;
				btFoto.setImageBitmap(bitmap);
			}
		}
	}
	
}