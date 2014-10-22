package com.example.handcoach.telaPartidas.Scouting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import util.ExpandableListAdapter;
import util.ScoutingCountdown;
import com.example.handcoach.R;
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
    protected static List<Jogador> jogadores = new ArrayList<Jogador>();
    private List<Jogador> listReservas;
    private ImageButton btTempo;
    private TextView placarEq;
    private TextView placarEqAdv;
    private Context context = this;
    public int contTime = 0;
    
    protected int jogadorComBola = 0;
    protected boolean posseBola;
    public boolean segundoTempo = false;
    public boolean terminarPartida = false;
 
    private Bundle valores;
    private Intent it;
    private int id_eq;
	private int id_eqadv;
	private String local;
	protected int id_ptda;
	private long tempoJogo;
	private long time;
	protected Partida partida = new Partida();
	
	protected int placarEqCont = 0;
	protected int placarEqAdvCont = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		addFragment(new FragmentInitScout());
		
		//Get Intent, etc;
		it = getIntent();
		valores = it.getExtras();
		id_eq = valores.getInt("id_equipe"); 
		id_eqadv = valores.getInt("id_equipeAdv");
		local = valores.getString("Local");
		tempoJogo = valores.getLong("tempo");
		
		idJogadores = ((List<Jogador>) valores.getSerializable("jog_joga"));
		Log.i("Tamanho IDJogadores", idJogadores.size()+"");
		for (Jogador joga : idJogadores) {
			Jogador jogador = joga;
			jogador.setFoto(joga.getOutput());
			jogadores.add(jogador);
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
		
		cronosJogo.setText(tempoJogo+":00");
		
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
		
		tempoToMillis(tempoJogo);
		
		//Cria cronômetros
		resetCronometroJogo();
		
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
					onPauseTroca();
				}
			}
		});
        
        btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(contTime == 3) {
					Toast.makeText(TelaScouting.this, R.string.limiteTempo, Toast.LENGTH_LONG).show();
				} else {
					contTime++;
					Toast.makeText(TelaScouting.this, R.string.tempo, Toast.LENGTH_SHORT).show();
					onTime();
				}
			}
		});
        
        //eventos da expandable list view
        listaJogadores.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setTitular(true);
				listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setReserva(false);
				listDataHeader.get(groupPosition).setTitular(false);
				listDataHeader.get(groupPosition).setReserva(true);
				refreshLista();
				listaJogadores.setAdapter(new ExpandableListAdapter(TelaScouting.this, listDataHeader, listDataChild));
				if(!posseBola) {
					replaceFragment(new OnNonPlayingFragment());
				} else {
					replaceFragment(new OnPlayingFragment());
				}
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
	
	public void onPauseTroca() {
		if(!cronometroJogo.isPaused()) {
			cronometroJogo.pause();
		}
		habilitaPlayPause(false);
		replaceFragment(new OnPauseFragment());
	}
	
	protected void comBola() {
		if(!cronometroJogo.isRunning()) {
			cronometroJogo.resume();
		}
		posseBola = true;
		habilitaPlayPause(true);
		replaceFragment(new OnPlayingFragment());
	}
	
	protected final void comBolaS() {
		comBola();
	}
	
	protected void semBola() {
		if(!cronometroJogo.isRunning()) {
			cronometroJogo.resume();
		}
		posseBola = false;
		habilitaPlayPause(true);
		replaceFragment(new OnNonPlayingFragment());
	}
	
	protected final void semBolaS() {
		semBola();
	}
	
	protected void habilitaPlayPause(boolean hab) {
		btPlayPause.setClickable(hab);
		btTempo.setClickable(hab);
	}
	
	protected List<Jogador> getJogadoresTitulares() {
		List<Jogador> titulares = new ArrayList<Jogador>();
		for(Jogador jog : jogadores) {
			if(jog.isTitular()) {
				titulares.add(jog);
				Log.i("Jogador", jog.getNome()+"");
			}
		}
		return titulares;
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
	
	private void refreshLista() {
		listDataChild.clear();
		listDataHeader.clear();
		listReservas.clear();
		
		for(Jogador jog : jogadores) {
        	if(jog.isTitular()) {
        		listDataHeader.add(jog);
        	}
        }
		
		for(Jogador jog : jogadores) {
        	if(jog.isReserva()) {
        		listReservas.add(jog);
        	}
        }
		
		listDataChild.put(listDataHeader.get(0), listReservas);
        listDataChild.put(listDataHeader.get(1), listReservas);
        listDataChild.put(listDataHeader.get(2), listReservas);
        listDataChild.put(listDataHeader.get(3), listReservas);
        listDataChild.put(listDataHeader.get(4), listReservas);
        listDataChild.put(listDataHeader.get(5), listReservas);
        listDataChild.put(listDataHeader.get(6), listReservas);   
	}
	
	protected final void setJogadorComBola(int jogadorComBola) {
		this.jogadorComBola = jogadorComBola;
	}
	
	protected void golEqAdv() {
		placarEqAdvCont++;
		placarEqAdv.setText(placarEqAdvCont+"");
		placarEqAdv.refreshDrawableState();
		comBola();
	}
	
	protected void golEq() {
		placarEqCont++;
		placarEq.setText(placarEqCont+"");
		placarEq.refreshDrawableState();
		semBola();
	}
	
	private void tempoToMillis(long tempoJogo) {
		time = tempoJogo * 60000;
	}
	
	public void resetCronometroJogo() {
		if(!terminarPartida) {
			cronometroJogo = new ScoutingCountdown(time, 1000, false, 1, TelaScouting.this);
			cronometroJogo.setText(cronosJogo);
			cronometroJogo.create();
		} else {
			cronosJogo.setText(getResources().getString(R.string.avisoFimFim));
		}
	}

	

	
}
