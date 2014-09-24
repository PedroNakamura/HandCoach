package com.example.handcoach.telaPartidas.Scouting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import DAO.Evento;
import DAO.EventoDAO;
import DAO.Jogador;
import DAO.JogadorDAO;
import DAO.Partida;
import DAO.PartidaDAO;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handcoach.R;
import com.example.handcoach.telaPartidas.jogadores.LazyAdapter;

//http://www.vogella.com/tutorials/AndroidListView/article.html
//http://www.guj.com.br/4372-listview-multiselecionavel-com-checkbox-selecionar-varios-itens-de-uma-listview

public class TelaScouting extends Activity {
	
	private Intent it;
	private Bundle valores;
	private List<Integer> listaJog;
	private List<Jogador> jogadores;
	private String local;
	private int id_eq;
	private int id_eqadv;
	private int id_ptda;
	private int onClickJog = 0;
	private Context context = TelaScouting.this;
	private Partida partida = new Partida();
	private ListView listaJogadores;
	private Button btFalta;
	private Button btPerdaBola;
	private Button btAr;
	private Button btCtAmarelo;
	private Button bt2min;
	private ImageButton btTempo;
	private ImageButton btPlayPause;
	private TextView placarEq;
	private TextView placarEqAdv;
	protected TextView cronosTempo;
	protected TextView cronosJogo;
	protected FinalCountdown cronometroTempo;
	protected FinalCountdown cronometroJogo;
	
	//com bola começa com true;
	private boolean comBola = true;
	private int placarEqCont = 0;
	private int placarEqAdvCont = 0;
	
	//variáveis dos eventos
	private int eventoAR_Gol = 1;
	private int eventoAR_Defesa = 2;
	private int eventoAR_Fora = 3;
	private int eventoAR_Gk = 4;
	private int eventoPSS_Certo = 5;
	private int eventoPSS_Errado = 6;
	private int eventoRCP_Certa = 7;
	private int eventoRCP_Errada = 8;
	private int eventoRCP_Rbdabola = 9;
	private int eventoFT_tecnica = 10;
	private int eventoFT_defesa = 11;
	private int eventoFT_ataque = 12;
	private int eventoFT_7m = 13;
	private int eventoCT_amarelo = 14;
	private int evento2min = 15;
	private int eventoSftAtk = 16;
	private int eventoSftDef = 17;
	private int eventoPB_equipe = 18;
	private int eventoPB_equipeadv = 19;
	private int eventoRBT_rebote = 20;
    
	// MÁGICA COMEÇA A ACONTECER!
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		btTempo = (ImageButton) findViewById(R.id.btTempo_1min);
		btPlayPause = (ImageButton) findViewById(R.id.btPlayPause);
		placarEq = (TextView) findViewById(R.id.placarEq);
		placarEqAdv = (TextView) findViewById(R.id.PlacarEqAdv);
		cronosJogo = (TextView) findViewById(R.id.cronos_tempoJogo);
		cronosTempo = (TextView) findViewById(R.id.cronos_tempo);	
		listaJogadores = (ListView) findViewById(R.id.listViewScouting);
		jogadores = new ArrayList<Jogador>();
		it = getIntent();
		valores = it.getExtras();		
		btFalta = (Button) findViewById(R.id.btFalta);
		btPerdaBola = (Button) findViewById(R.id.btPerdaBola);
		btAr = (Button) findViewById(R.id.btAr);
		btCtAmarelo = (Button) findViewById(R.id.ctAmarelo);
		bt2min = (Button) findViewById(R.id.bt2min);
		
		//cria cronômetros
		cronometroTempo = new FinalCountdown(10000, 1000, false, 2, this);
		cronometroTempo.setText(cronosTempo);
		cronometroTempo.create();
		
		cronometroJogo = new FinalCountdown(750000, 1000, false, 1, this);
		cronometroJogo.setText(cronosJogo);
		cronometroJogo.create();
		
			
		//pega o restante dos valores do bundle
		local = valores.getString("Local");
		id_eq = valores.getInt("id_equipe");
		id_eqadv = valores.getInt("id_equipeAdv");
		
		//setPlacares
		placarEq.setText(placarEqCont+"");
		placarEqAdv.setText(placarEqAdvCont+"");
		
		//visibilidade
		cronosTempo.setVisibility(View.INVISIBLE);
		
		//pega os jogadores selecionados para o jogo vindos da última tela
		listaJog = (List<Integer>) valores.getIntegerArrayList("jog_joga");
		for (Integer id : listaJog) {
			Jogador jogador = JogadorDAO.getInstancia(TelaScouting.this).buscarPorID(id);
			jogadores.add(jogador);
		}
		
		//getDataDeHoje
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(c.getTime());	
		Log.i("DEBUG: ------ ", ""+strDate);
		//Pronto!
		
		try {
			partida.setId_eq(id_eq);
			partida.setId_eqadv(id_eqadv);
			partida.setLocal(local);
			partida.setGol_eq(0);
			partida.setGol_adv(0);
			partida.setData(PartidaDAO.getInstancia(TelaScouting.this).stringToDate(strDate));
			PartidaDAO.getInstancia(this).Inserir(partida);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Partida pt_criada = (Partida) PartidaDAO.getInstancia(TelaScouting.this).buscarMaiorID();
		id_ptda = pt_criada.getId();
		
		final LazyAdapter jogadorLista = new LazyAdapter(TelaScouting.this, jogadores);
		listaJogadores.setAdapter(jogadorLista);
		
		//Arremesos!
		ActionItem actionGol = new ActionItem();
		actionGol.setTitle("GOL!");
		actionGol.setIcon(getResources().getDrawable(R.drawable.phone));
		ActionItem actionGoleiro = new ActionItem();
		actionGoleiro.setTitle("GOLEIRO PEGOU!");
		actionGoleiro.setIcon(getResources().getDrawable(R.drawable.gmail));
		ActionItem actionFora = new ActionItem();
		actionFora.setTitle("PRA FORA!");
		actionFora.setIcon(getResources().getDrawable(R.drawable.talk));
		ActionItem actionDefesa = new ActionItem();
		actionDefesa.setTitle("DEFESA!");
		actionDefesa.setIcon(getResources().getDrawable(R.drawable.ic_launcher));		
		final QuickAction arQuick = new QuickAction(this);
		arQuick.addActionItem(actionGol);
		arQuick.addActionItem(actionGoleiro);
		arQuick.addActionItem(actionDefesa);
		arQuick.addActionItem(actionFora);
		arQuick.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			public void onItemClick(int pos) {
				if (pos == 0) { 
					Evento eventoGol = new Evento(eventoAR_Gol, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoGol);
					Log.i("EventoGol ---- ", eventoGol.getId_cat()+"");
					placarEqCont++;
					placarEq.setText(placarEqCont+"");
					placarEq.refreshDrawableState();
					comBola = false;
				} else if (pos == 1) { 
					Evento eventoGoleiro = new Evento(eventoAR_Gk, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoGoleiro);
					Log.i("EventoGoleiro ---- ", eventoGoleiro.getId_cat()+"");
					comBola = false;
				} else if (pos == 2) { 
					Evento eventoFora = new Evento(eventoAR_Fora, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFora);
					Log.i("EventoFora ---- ", eventoFora.getId_cat()+"");
					comBola = false;
				} else if (pos == 3) {
					Evento eventoDefesa = new Evento(eventoAR_Defesa, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoDefesa);
					Log.i("EventoDefesa ---- ", eventoDefesa.getId_cat()+"");
					comBola = false;
				}
			}
		});
		
		//Faltas!
		ActionItem actionFaltaTec = new ActionItem();
		actionFaltaTec.setTitle("Falta Técnica");
		actionFaltaTec.setIcon(getResources().getDrawable(R.drawable.phone));
		ActionItem actionFaltaDef = new ActionItem();
		actionFaltaDef.setTitle("Falta Defesa!");
		actionFaltaDef.setIcon(getResources().getDrawable(R.drawable.gmail));
		ActionItem actionFaltaAtk = new ActionItem();
		actionFaltaAtk.setTitle("Falta Ataque!");
		actionFaltaAtk.setIcon(getResources().getDrawable(R.drawable.talk));
		ActionItem actionFalta7m = new ActionItem();
		actionFalta7m.setTitle("7 metros!");
        actionFalta7m.setIcon(getResources().getDrawable(R.drawable.ic_launcher));		
		final QuickAction ftQuick = new QuickAction(this);
		ftQuick.addActionItem(actionFaltaTec);
		ftQuick.addActionItem(actionFaltaDef);
		ftQuick.addActionItem(actionFaltaAtk);
		ftQuick.addActionItem(actionFalta7m);
		ftQuick.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			public void onItemClick(int pos) {
				if (pos == 0) { 
					Evento eventoFaltaTec = new Evento(eventoFT_tecnica, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFaltaTec);
					Log.i("EventoFaltaTec ---- ", eventoFaltaTec.getId_cat()+"");
					comBola = false;
				} else if (pos == 1) { 
					Evento eventoFaltaDef = new Evento(eventoFT_defesa, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFaltaDef);
					Log.i("EventoFaltaDef ---- ", eventoFaltaDef.getId_cat()+"");
					comBola = false;
				} else if (pos == 2) { 
					Evento eventoFaltaAtk= new Evento(eventoFT_ataque, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFaltaAtk);
					Log.i("EventoFaltaAtk ---- ", eventoFaltaAtk.getId_cat()+"");
					comBola = false;
				} else if (pos == 3) {
					Evento evento7m = new Evento(eventoFT_7m, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(evento7m);
					Log.i("EventoFalta7m ---- ", evento7m.getId_cat()+"");
					comBola = false;
				}
			}
		});
		//
		
		listaJogadores.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(onClickJog == 0) {
					onClickJog = ((Jogador) jogadorLista.getItem(position)).getId();
				} else {
					int jogadorQuePassou = onClickJog;
					onClickJog = ((Jogador) jogadorLista.getItem(position)).getId();
					Evento passeCerto = new Evento(eventoPSS_Certo, jogadorQuePassou, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(passeCerto);
					Log.i("PASSE CERTO: ", "De "+jogadorQuePassou+" para "+onClickJog);
					Evento rcpCerta = new Evento(eventoRCP_Certa, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(rcpCerta);
					Log.i("RECEPÇÃO CERTA: ", "De "+onClickJog);
				}
			}
			
		});	
		
		//Action Item...
		ActionItem actionPasseErrado = new ActionItem();
		actionPasseErrado.setTitle("Passe errado");
		actionPasseErrado.setIcon(getResources().getDrawable(R.drawable.gmail));
		ActionItem actionRcpErrado = new ActionItem();
		actionRcpErrado.setTitle("Recepção errada");
		actionRcpErrado.setIcon(getResources().getDrawable(R.drawable.ic_launcher));
		final QuickAction quickActionPerdaBola = new QuickAction(this);
		quickActionPerdaBola.addActionItem(actionPasseErrado);
		quickActionPerdaBola.addActionItem(actionRcpErrado);
		quickActionPerdaBola.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento eventoPasseErrado = new Evento(eventoPSS_Errado, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(context).Inserir(eventoPasseErrado);
					comBola = false;
				} else if(pos == 1) {
					//isso pode gerar bugs? qual a melhor maneira de se fazer isso? 
					Evento eventoRcpErrado = new Evento(eventoRCP_Errada, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(context).Deletar(EventoDAO.getInstancia(context).buscarMaiorID());
					EventoDAO.getInstancia(context).Inserir(eventoRcpErrado);
					comBola = false;
				}
			}
		});
		btPerdaBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				quickActionPerdaBola.show(v);
				quickActionPerdaBola.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		btAr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				arQuick.show(v);
				arQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		btFalta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ftQuick.show(v);
				ftQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
			
		});
		
		btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(TelaScouting.this, R.string.tempo, Toast.LENGTH_SHORT).show();
				if(!cronometroTempo.isFinished()) {
					cronometroJogo.pause();
					cronosTempo.setVisibility(View.VISIBLE);
					habilitaBotoes(false);
					habilitaPlayPause(false);
					cronometroTempo.resume();
				} else {
					FinalCountdown cronometro2 = new FinalCountdown(10000, 1000, false, 2, TelaScouting.this);
					cronometro2.setText(cronosTempo);
					cronometroTempo = cronometro2;
					cronometroJogo.pause();
					cronosTempo.setVisibility(View.VISIBLE);
					habilitaBotoes(false);
					habilitaPlayPause(false);
					cronometroTempo.resume();
				}
			}
		});
		
		btPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cronometroJogo.isPaused() || !cronometroJogo.hasBeenStarted()) {
					cronometroJogo.resume();
					habilitaBotoes(true);
				} else if(cronometroJogo.isRunning()) {
					cronometroJogo.pause();
					habilitaBotoes(false);
				}
			}
		});
		
		btCtAmarelo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			   Evento eventoCtAmarelo = new Evento(eventoCT_amarelo, onClickJog, id_ptda, 0, 0);
			   EventoDAO.getInstancia(context).Inserir(eventoCtAmarelo);
			   Log.i("Cartão amarelo", ""+onClickJog);
			}		
			
		});
		
		bt2min.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Evento evento2m = new Evento(evento2min, onClickJog, id_ptda, 0, 0);
				EventoDAO.getInstancia(context).Inserir(evento2m);
				Log.i("2 minutos", ""+onClickJog);
			}
		});
		
	}
	
	protected void habilitaBotoes(boolean habilita) {
		
		btAr.setClickable(habilita);
		btPerdaBola.setClickable(habilita);
		btCtAmarelo.setClickable(habilita);
		btFalta.setClickable(habilita);
		bt2min.setClickable(habilita);
	    if(!habilita) {
	    	listaJogadores.setVisibility(View.INVISIBLE);
	    } else {
	    	listaJogadores.setVisibility(View.VISIBLE);
	    }
		
	}
	
	protected void habilitaPlayPause(boolean hab) {
		btPlayPause.setClickable(hab);
	}

}


