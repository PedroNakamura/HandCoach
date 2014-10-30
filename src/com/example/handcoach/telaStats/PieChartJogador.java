package com.example.handcoach.telaStats;

import com.example.handcoach.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PieChartJogador extends Activity {
	
	private Intent it;
	private Bundle valores;
    protected int jogador_id;
    protected int tipo_stats;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.piechart_jogador);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		it = getIntent();
		valores = it.getExtras();
		jogador_id = (Integer) valores.get("jog");
		tipo_stats = (Integer) valores.get("stats");
		
	}

}

