package com.example.handcoach.telaPartidas.jogadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TelaCadastroJog extends Activity {
	
	ImageButton btFoto;
	Bitmap image = null;
	ArrayList<String> posicoes;
	
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
		final RadioGroup radioGroupPos = (RadioGroup) findViewById(R.id.radioPos);
		btFoto = (ImageButton) findViewById(R.id.bt_ftPessoa_img);
		Button btCadastrar = (Button) findViewById(R.id.btOkCadastroJogador);
		
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
				
				try {
					if(isDouble(editAlturaJogador.getText().toString()) && isDate(editNascimentoJogador.getText().toString())) {
						String altura = editAlturaJogador.getText().toString();
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
						
						switch(radioGroupPos.getCheckedRadioButtonId()) {
						case R.id.goleiro:
							jogador.setPos(1);
							Log.i("DEBUG!!!", "GOLEIRO");
							break;
						case R.id.armador:
							jogador.setPos(2);
							Log.i("DEBUG!!!", "ARMADOR");
						    break;
						case R.id.meia:
							jogador.setPos(3);
							Log.i("DEBUG!!!", "MEIA");
							break;
						case R.id.ponta:
							jogador.setPos(4);
							Log.i("DEBUG!!!", "PONTA");
							break;
						case R.id.pivo:
							jogador.setPos(5);
							Log.i("DEBUG!!!", "PIVO");
							break;
						}
						
						jogador.setAltura(Double.parseDouble(altura));
						jogador.setFoto(image);	
						String nascimentoJogador = editNascimentoJogador.getText().toString();
						
						try {
							jogador.setDt_nasc(JogadorDAO.getInstancia(TelaCadastroJog.this).stringToDate(nascimentoJogador));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						JogadorDAO.getInstancia(TelaCadastroJog.this).Inserir(jogador);
						Toast.makeText(TelaCadastroJog.this, R.string.AlertaJogCadastro, Toast.LENGTH_SHORT).show();
						
						finish();
					} else if(!isDouble(editAlturaJogador.getText().toString())) {
						Toast.makeText(TelaCadastroJog.this, R.string.ErroCadastroAltura, Toast.LENGTH_LONG).show();
					} else if(!isDate(editNascimentoJogador.getText().toString())) {
						Toast.makeText(TelaCadastroJog.this, R.string.ErroCadastroData, Toast.LENGTH_LONG).show();
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (NotFoundException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				
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
	
	 private boolean isDouble(String str) {
	        try {
	            Double.parseDouble(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	 }
	 
	 private boolean isDate(String dataStr) throws java.text.ParseException {
	        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
	        try {
	            format.parse(dataStr);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	 }
	
}