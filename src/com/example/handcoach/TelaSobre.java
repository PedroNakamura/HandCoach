package com.example.handcoach;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TelaSobre extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_sobre);
		ImageButton btalerta = (ImageButton) findViewById(R.id.btsobre_alerta);
		btalerta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alerta = new AlertDialog.Builder(TelaSobre.this);
				alerta.setIcon(R.drawable.ic_launcher);
				alerta.setTitle(R.string.tituloAlertaSobre);
				alerta.setMessage(R.string.msgAlertaSobre);
				alerta.setPositiveButton(R.string.btSimAlertaSobre, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						Uri uri = Uri.parse("http://pedronakamura.com.br/handcoach");
						Intent it = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(it);
						
					}
				});
				
				alerta.setNegativeButton(R.string.btNaoAlertaSobre, null);
				alerta.show();
				
			}
		});
	}

}
