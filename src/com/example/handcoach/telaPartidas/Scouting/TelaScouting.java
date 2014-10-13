package com.example.handcoach.telaPartidas.Scouting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.example.handcoach.R;

import DAO.JogadorDAO;
import DAO.PartidaDAO;
import Entidades.Partida;
import Entidades.Jogador;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TelaScouting extends Activity {
	
	//http://stackoverflow.com/questions/9741300/charts-for-android
	//http://developer.android.com/training/basics/fragments/fragment-ui.html
	
	protected ScoutingCountdown cronometroJogo;
    private ImageButton btPlayPause;
    private TextView cronosJogo;
    private ExpandableListView listaJogadores;
    private ExpandableListAdapter listAdapter;
    private List<Jogador> listDataHeader;
    private HashMap<Jogador, List<Jogador>> listDataChild;
    private List<Jogador> idJogadores;
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private List<Jogador> listReservas;
    private ImageButton btTempo;
    private TextView placarEq;
    private TextView placarEqAdv;
    private Context context = this;
    protected int contTime = 0;
    
    protected boolean posseBola;
    protected boolean segundoTempo = false;
    protected boolean terminarPartida = false;
 
    private Bundle valores;
    private Intent it;
    private int id_eq;
	private int id_eqadv;
	private String local;
	private int id_ptda;
	private Partida partida = new Partida();
	
	private int placarEqCont = 0;
	private int placarEqAdvCont = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		addFragment(new FragmentInitScout());
		
		//Get Intent, etc;
		it = getIntent();
		valores = it.getExtras();
		id_eq = valores.getInt("id_equipe"); 
		id_eqadv = valores.getInt("id_equipeAdv");
		local = valores.getString("Local");
		idJogadores = ((List<Jogador>) valores.getSerializable("jog_joga"));
		for (Jogador joga : idJogadores) {
			jogadores.add(joga);
		}
		
		//Instancia layout e botões
		placarEq = (TextView) findViewById(R.id.placar_Eq);
		placarEqAdv = (TextView) findViewById(R.id.placar_EqAdv);
		btPlayPause = (ImageButton) findViewById(R.id.btPlayOrPause);
		cronosJogo = (TextView) findViewById(R.id.cronosTempoJogo);
		listaJogadores = (ExpandableListView) findViewById(R.id.ExpListViewJogadores);
		btTempo = (ImageButton) findViewById(R.id.btTempo);
		
		placarEq.setText(""+placarEqCont);
		placarEqAdv.setText(""+placarEqAdvCont);
		
		habilitaPlayPause(false);
		
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
		
		//Seta os ExpandableListView
		prepararLista();
		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		listaJogadores.setAdapter(listAdapter);
		
		//Cria cronômetros
		cronometroJogo = new ScoutingCountdown(900000, 1000, false, 1, TelaScouting.this);
		cronometroJogo.setText(cronosJogo);
		cronometroJogo.create();
		
		//Botões
        btPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cronometroJogo.isPaused() || !cronometroJogo.hasBeenStarted()) {
					cronometroJogo.resume();;
					if(posseBola) {
						comBola();
					} else {
						semBola();
					}
				} else if(cronometroJogo.isRunning()) {
					habilitaPlayPause(false);
					onPause();
				}
			}
		});
        
        btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(contTime <= 2) {
					contTime++;
					Toast.makeText(TelaScouting.this, R.string.tempo, Toast.LENGTH_SHORT).show();
					habilitaPlayPause(false);
					onTime();
				} else {
					Toast.makeText(TelaScouting.this, R.string.limiteTempo, Toast.LENGTH_LONG).show();
				}
			}
		});
        
        //eventos da expandable list view
        listaJogadores.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Jogador sai = listDataHeader.get(groupPosition);
				Jogador entra = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
				listDataChild.get(listDataHeader.get(groupPosition)).set(childPosition, sai);
				listDataHeader.set(groupPosition, entra);
				refreshLista(listDataChild.get(listDataHeader.get(groupPosition)), listDataHeader);
				listaJogadores.setAdapter(new ExpandableListAdapter(TelaScouting.this, listDataHeader, listDataChild));
				return false;
			}
		});
        
        listaJogadores.setOnGroupExpandListener(new OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup){
                    listaJogadores.collapseGroup(previousGroup);
                }    
                previousGroup = groupPosition;
            }
        });
		
	}
	//saiu do OnCreate();
	
	
	//Métodos 
	private void addFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
	}
	
	private void replaceFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContent, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
	}
	
	protected void onTime() {
		if(!cronometroJogo.isPaused()) {
			cronometroJogo.pause();
		}
		habilitaPlayPause(false);
		replaceFragment(new OnTimeFragment());
	}
	
	protected void onPause() {
		if(!cronometroJogo.isPaused()) {
			cronometroJogo.pause();
		}
		habilitaPlayPause(false);
		replaceFragment(new OnPauseFragment());
	}
	
	protected void comBola() {
		posseBola = true;
		habilitaPlayPause(true);
		replaceFragment(new OnPlayingFragment());
	}
	
	protected void semBola() {
		posseBola = false;
		habilitaPlayPause(true);
		replaceFragment(new OnNonPlayingFragment());
	}
	
	protected void habilitaPlayPause(boolean hab) {
		btPlayPause.setClickable(hab);
		btTempo.setClickable(hab);
	}
	
	private String getDataHoje() {
		//getDataDeHoje
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(c.getTime());	
		Log.i("DEBUG: ------ ", ""+strDate);
		return strDate;
	}
	
	private void prepararLista() {
		listDataHeader = new ArrayList<Jogador>();
        listDataChild = new HashMap<Jogador, List<Jogador>>();
        listReservas = new ArrayList<Jogador>();
        
        //add DataHeader
        for(Jogador jog : jogadores) {
        	if(jog.isTitular()) {
        		listDataHeader.add(jog);
        	}
        }
        
        //cria Reservas e data child
        for(Jogador jog : jogadores) {
        	if(jog.isReserva()) {
        		listReservas.add(jog);
        	}
        }
        
        //add DataChild
        listDataChild.put(listDataHeader.get(0), listReservas);
        listDataChild.put(listDataHeader.get(1), listReservas);
        listDataChild.put(listDataHeader.get(2), listReservas);
        listDataChild.put(listDataHeader.get(3), listReservas);
        listDataChild.put(listDataHeader.get(4), listReservas);
        listDataChild.put(listDataHeader.get(5), listReservas);
        listDataChild.put(listDataHeader.get(6), listReservas);   
	}
	
	private void refreshLista(List<Jogador> listaChild, List<Jogador> listaHeader) {
		listDataChild.clear();

        //add DataChild
		listDataChild.put(listaHeader.get(0), listaChild);
		listDataChild.put(listaHeader.get(1), listaChild);
		listDataChild.put(listaHeader.get(2), listaChild);
		listDataChild.put(listaHeader.get(3), listaChild);
		listDataChild.put(listaHeader.get(4), listaChild);
		listDataChild.put(listaHeader.get(5), listaChild);
		listDataChild.put(listaHeader.get(6), listaChild);
	}

}
