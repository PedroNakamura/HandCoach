package com.example.handcoach.telaPartidas.Scouting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.handcoach.R;

import DAO.PartidaDAO;
import Entidades.Partida;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TelaScouting extends FragmentActivity {
	
	//http://stackoverflow.com/questions/9741300/charts-for-android
	//http://developer.android.com/training/basics/fragments/fragment-ui.html
	
	protected ScoutingCountdown cronometroJogo;
	private FragmentTransaction ft;
	private Fragment fr;
    private Fragment onPauseFragment = new OnPauseFragment();
    private Fragment onTimeFragment = new OnTimeFragment();
    private Fragment onPlayingFragment = new OnPlayingFragment();
    private Fragment onNonPlayingFragment = new OnNonPlayingFragment();
    private ImageButton btPlayPause;
    private TextView cronosJogo;
    //private ExpandableListView listaJogadores;
    private ImageButton btTempo;
    private TextView placarEq;
    private TextView placarEqAdv;
    private Context context = this;
 
    private Bundle valores;
    private Intent it;
    private int id_eq;
	private int id_eqadv;
	private String local;
	private int id_ptda;
	private Partida partida = new Partida();
	
	private int placarEqCont = 0;
	private int placarEqAdvCont = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		it = getIntent();
		valores = it.getExtras();
		id_eq = valores.getInt("id_equipe"); 
		id_eqadv = valores.getInt("id_equipeAdv");
		local = valores.getString("Local");
		
		placarEq = (TextView) findViewById(R.id.placar_Eq);
		placarEqAdv = (TextView) findViewById(R.id.placar_EqAdv);
		btPlayPause = (ImageButton) findViewById(R.id.btPlayOrPause);
		cronosJogo = (TextView) findViewById(R.id.cronosTempoJogo);
		//listaJogadores = (ExpandableListView) findViewById(R.id.ExpListViewJogadores);
		btTempo = (ImageButton) findViewById(R.id.btTempo);
		
		placarEq.setText(""+placarEqCont);
		placarEqAdv.setText(""+placarEqAdvCont);
		
		//Cria partida e recupera ID
		try {
			partida.setId_eq(id_eq);
			partida.setId_eqadv(id_eqadv);
			partida.setLocal(local);
			partida.setGol_eq(0);
			partida.setGol_adv(0);
			partida.setData(PartidaDAO.getInstancia(context).stringToDate(getDataHoje()));
			PartidaDAO.getInstancia(this).Inserir(partida);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Partida pt_criada = (Partida) PartidaDAO.getInstancia(context).buscarMaiorID();
		id_ptda = pt_criada.getId();
		
		//Cria cron�metros
		cronometroJogo = new ScoutingCountdown(1500000, 1000, false, 1, TelaScouting.this);
		cronometroJogo.setText(cronosJogo);
		cronometroJogo.create();
		
		//Bot�es
        btPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cronometroJogo.isPaused() || !cronometroJogo.hasBeenStarted()) {
					cronometroJogo.resume();
					habilitaPlayPause(true);
					replaceFragment(onPlayingFragment);
				} else if(cronometroJogo.isRunning()) {
					cronometroJogo.pause();
					habilitaPlayPause(false);
					replaceFragment(onPauseFragment);
				}
			}
		});
        
        btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(TelaScouting.this, R.string.tempo, Toast.LENGTH_SHORT).show();
				habilitaPlayPause(false);
				cronometroJogo.pause();
				replaceFragment(onTimeFragment);
			}
		});
		
	}
	
	//M�todos private
	private void addFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
	}
	
	private void replaceFragment(Fragment fragment) {
		FragmentTransaction  transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
	}
	
	private void onClickOnPauseFragment(View view) {
		replaceFragment(onPauseFragment);
	}
	
	private void onClickOnTimeFragment(View view) {
		replaceFragment(onTimeFragment);
	}
	
	protected void onClickOnPauseFrag() {
		replaceFragment(onPauseFragment);
	}
	
	private void habilitaPlayPause(boolean hab) {
		btPlayPause.setActivated(hab);
	}
	
	public void selectFrag(View v) {
		if(v == findViewById(R.id.btPlayPause)) {
			fr = onPauseFragment;
		} else if(v == findViewById(R.id.btTempo)) {
			fr = onTimeFragment;
		}
		FragmentManager fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.fragment_place, fr);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	private String getDataHoje() {
		//getDataDeHoje
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(c.getTime());	
		Log.i("DEBUG: ------ ", ""+strDate);
		return strDate;
	}

}
