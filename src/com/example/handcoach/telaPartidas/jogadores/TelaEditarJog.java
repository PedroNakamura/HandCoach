package com.example.handcoach.telaPartidas.jogadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		final RadioGroup radioGroupPos = (RadioGroup) findViewById(R.id.radioPosEdit);
		RadioButton goleiro = (RadioButton) findViewById(R.id.goleiroEdit);
		RadioButton armador = (RadioButton) findViewById(R.id.armadorEdit);
		RadioButton meia = (RadioButton) findViewById(R.id.meiaEdit);
		RadioButton ponta = (RadioButton) findViewById(R.id.pontaEdit);
		RadioButton pivo = (RadioButton) findViewById(R.id.pivoEdit);
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
	    
	    //testa Posições
	    if(jogador.getPos() == 1) {
	       goleiro.setChecked(true);
	    }
        if(jogador.getPos() == 2) {
	       armador.setChecked(true);
	    }
        if(jogador.getPos() == 3) {
	    	meia.setChecked(true);
	    }
        if(jogador.getPos() == 4) {
	    	ponta.setChecked(true);
	    }
        if(jogador.getPos() == 5) {
	    	pivo.setChecked(true);
	    }
        //ufa!
	    
	    jogadorAltura.setText(jogador.getAltura()+"");
	    jogadorDtNasc.setText(jogador.dateToString());
	    
	    btAtualiza.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
                String dtNascJogador = jogadorDtNasc.getText().toString();
                String altura = jogadorAltura.getText().toString();
                
                try {
					if(isDouble(altura) && isDate(dtNascJogador)) {
						Jogador jogador2 = new Jogador();
						
						jogador2.setNome(jogadorNome.getText().toString());

						switch(jogadorSexo.getCheckedRadioButtonId()) {
						case R.id.masculinoEdit:
							jogador2.setSexo(1);
							Log.i("DEBUG!!!", "MASCULINO");
							break;
						case R.id.femininoEdit:
							jogador2.setSexo(0);
							Log.i("DEBUG!!!", "FEMININO");
							break;
						}
						
						switch(radioGroupPos.getCheckedRadioButtonId()) {
						case R.id.goleiroEdit:
							jogador2.setPos(1);
							Log.i("DEBUG!!!", "GOLEIRO");
							break;
						case R.id.armadorEdit:
							jogador2.setPos(2);
							Log.i("DEBUG!!!", "ARMADOR");
						    break;
						case R.id.meiaEdit:
							jogador2.setPos(3);
							Log.i("DEBUG!!!", "MEIA");
							break;
						case R.id.pontaEdit:
							jogador2.setPos(4);
							Log.i("DEBUG!!!", "PONTA");
							break;
						case R.id.pivoEdit:
							jogador2.setPos(5);
							Log.i("DEBUG!!!", "PIVO");
							break;
						}
						
					    try {
					    	jogador2.setDt_nasc(JogadorDAO.getInstancia(TelaEditarJog.this).stringToDate(dtNascJogador));
						} catch (java.text.ParseException e) {
							e.printStackTrace();
						}
					    
					    //jogador2.setPos(positionJ);
					    jogador2.setAltura(Double.parseDouble(altura));
					    jogador2.setId(jogador.getId());
					    jogador2.setIdEq(jogador.getIdEq());
					    jogador2.setFoto(image);
					    
					    JogadorDAO.getInstancia(TelaEditarJog.this).Editar(jogador, jogador2);
					    
						Toast.makeText(TelaEditarJog.this, R.string.alertaAtualizado, Toast.LENGTH_SHORT).show();
						
						finish();
					} else if(!isDate(dtNascJogador)) {
						Toast.makeText(TelaEditarJog.this, R.string.ErroCadastroData, Toast.LENGTH_LONG).show();
					} else if(!isDouble(altura)) {
						Toast.makeText(TelaEditarJog.this, R.string.ErroCadastroAltura, Toast.LENGTH_LONG).show();
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
