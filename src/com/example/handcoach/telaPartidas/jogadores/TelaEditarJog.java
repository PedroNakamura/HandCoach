package com.example.handcoach.telaPartidas.jogadores;

import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TelaEditarJog extends Activity {
	
	private Bundle valor;
	private int id;
	private Intent it;
	private Jogador jogador;
	private Bitmap image;
	private ImageButton btFoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_editar_jog);		
		
		it = getIntent();
		valor = it.getExtras();
		id = valor.getInt("Jogador");
		
		final EditText jogadorNome = (EditText) findViewById(R.id.editText_jogadorEditNome);
		final RadioGroup jogadorSexo = (RadioGroup) findViewById(R.id.radioGroup_editJogador);
		RadioButton masculino = (RadioButton) findViewById(R.id.masculinoEdit);
		RadioButton feminino = (RadioButton) findViewById(R.id.femininoEdit);
		final EditText jogadorAltura = (EditText) findViewById(R.id.editText_jogadorEditAltura);
		final EditText jogadorDtNasc = (EditText) findViewById(R.id.editText_jogadorEditDT_Nasc);
		btFoto = (ImageButton) findViewById(R.id.ft_Jogador_edit);
		Button btAtualiza = (Button) findViewById(R.id.btEditJogador_Atualiza);
	 
	    jogador = JogadorDAO.getInstancia(TelaEditarJog.this).buscarPorID(id);
	    jogadorNome.setText(jogador.getNome());
	    
	    image = jogador.getFoto();
	    btFoto.setImageBitmap(image);
	    
	    if(jogador.isSexo()) {
	    	masculino.setChecked(true);
	    } else {
	    	feminino.setChecked(true);
	    }
	    
	    jogadorAltura.setText(jogador.getAltura());
	    jogadorDtNasc.setText(jogador.dateToString());
	    
	    btAtualiza.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Jogador jogador2 = new Jogador();
				
				jogador2.setNome(jogadorNome.getText().toString());

				switch(jogadorSexo.getCheckedRadioButtonId()) {
				case R.id.masculinoEdit:
					jogador.setSexo(1);
					break;
				case R.id.femininoEdit:
					jogador.setSexo(0);
					break;
				}
				
				String dtNascJogador = jogadorDtNasc.getText().toString();
				
			    try {
			    	jogador2.setDt_nasc(JogadorDAO.getInstancia(TelaEditarJog.this).stringToDate(dtNascJogador));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			    
			    jogador2.setAltura(jogadorAltura.getText().toString());
			    jogador2.setId(jogador.getId());
			    jogador2.setIdEq(jogador.getIdEq());
			    jogador2.setFoto(image);
			    
			    JogadorDAO.getInstancia(TelaEditarJog.this).Editar(jogador, jogador2);
			    
				Toast.makeText(TelaEditarJog.this, R.string.alertaAtualizado, Toast.LENGTH_SHORT).show();
				
				finish();
			}
		});
	    
        btFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(i, 0);
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
