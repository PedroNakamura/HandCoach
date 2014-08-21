package com.example.handcoach;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {
	
	protected boolean _active = true;
	protected int _splashTime = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		Thread splashTread = new Thread() {
		    @Override
		    public void run() {
		      try {
		         int waited = 0;
		  	     while (_active && (waited < _splashTime)) {
			        sleep(100);
			        if (_active) {
			           waited += 100;
			        }
			     }
		      } catch (InterruptedException e) {
			  } finally {
			       startActivity(new Intent(SplashScreen.this, TelaNav.class));
			       finish();
			  }
		  }
		};
		splashTread.start();
		}
	}


