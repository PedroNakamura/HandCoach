package com.example.handcoach;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TelaNav extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_nav);
		//teste commit
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_nav, menu);
		return true;
	}

}
