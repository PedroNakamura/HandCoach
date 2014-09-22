package com.example.handcoach.telaPartidas.Scouting;

import java.sql.Date;
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
	private Date data;
	private int onClickJog = 0;
	private Context context = TelaScouting.this;
	
	private Partida partida;
	private ListView listaJogadores;
	private Button btFalta;
	private Button btPasse;
	private Button btAr;
	private Button btCtAmarelo;
	private Button bt2min;
	private ImageButton btTempo;
	private ImageButton btPlayPause;
	private TextView cronosTempo;
	private TextView cronosJogo;
	private FinalCountdown cronometroTempo;
	private FinalCountdown cronometroJogo;
	
	//variáveis dos eventos
	int eventoAR_Gol = 1;
	int eventoAR_Defesa = 2;
	int eventoAR_Fora = 3;
	int eventoAR_Gk = 4;
	int eventoPSS_Certo = 5;
	int eventoPSS_Errado = 6;
	int eventoRCP_Certa = 7;
	int eventoRCP_Errada = 8;
	int eventoRCP_Rbdabola = 9;
	int eventoFT_tecnica = 10;
	int eventoFT_defesa = 11;
	int eventoFT_ataque = 12;
	int eventoFT_7m = 13;
	int eventoCT_amarelo = 14;
	int evento2min = 15;
	int eventoSftAtk = 16;
	int eventoSftDef = 17;
	int eventoPB_equipe = 18;
	int eventoPB_equipeadv = 19;
    
	// MÁGICA COMEÇA A ACONTECER!
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		btTempo = (ImageButton) findViewById(R.id.btTempo_1min);
		btPlayPause = (ImageButton) findViewById(R.id.btPlayPause);
		cronosJogo = (TextView) findViewById(R.id.cronos_tempoJogo);
		cronosTempo = (TextView) findViewById(R.id.cronos_tempo);	
		listaJogadores = (ListView) findViewById(R.id.listViewScouting);
		jogadores = new ArrayList<Jogador>();
		it = getIntent();
		valores = it.getExtras();		
		btFalta = (Button) findViewById(R.id.btFalta);
		btPasse = (Button) findViewById(R.id.btPasse);
		btAr = (Button) findViewById(R.id.btAr);
		btCtAmarelo = (Button) findViewById(R.id.ctAmarelo);
		bt2min = (Button) findViewById(R.id.bt2min);
		
		//cria cronômetros
		cronometroTempo = new FinalCountdown(100000, 1000);
		cronometroTempo.setText(cronosTempo);
		
		cronometroJogo = new FinalCountdown(1500000, 1000);
		cronometroJogo.setText(cronosJogo);
			
		//pega o restante dos valores do bundle
		local = valores.getString("Local");
		id_eq = valores.getInt("id_equipe");
		id_eqadv = valores.getInt("id_equipeAdv");
		
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
		String strDate = sdf.format(c.getTime());	
		Log.i("DEBUG: ------ ", ""+strDate);
		//Pronto!
		
		try {
			
			partida.setId_eq(id_eq);
			partida.setId_eqadv(id_eqadv);
			partida.setLocal(local);
			partida.setGol_eq(0);
			partida.setGol_adv(0);
			partida.setData((Date) PartidaDAO.getInstancia(TelaScouting.this).stringToDate(strDate));
			PartidaDAO.getInstancia(this).Inserir(partida);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		id_ptda = PartidaDAO.getInstancia(TelaScouting.this).buscarMaiorID();
		
		final LazyAdapter jogadorLista = new LazyAdapter(TelaScouting.this, jogadores);
		listaJogadores.setAdapter(jogadorLista);
		
		/*Cria actionItem - phone
		ActionItem actionGol = new ActionItem();
		actionGol.setTitle("Telefone");
		actionGol.setIcon(getResources().getDrawable(R.drawable.phone));
		
		//Cria actionItem - Gmail
		ActionItem actionGoal = new ActionItem();
		actionGoal.setTitle("GMAIL");
		actionGoal.setIcon(getResources().getDrawable(R.drawable.gmail));
		
		//Cria actionItem - Talk
		ActionItem actionFora = new ActionItem();
		actionFora.setTitle("Fala, vivente!");
		actionFora.setIcon(getResources().getDrawable(R.drawable.talk));
		*/
		/*Instancia QuickAction
		final QuickAction mQuickAction = new QuickAction(this);
		mQuickAction.addActionItem(actionGol);
		mQuickAction.addActionItem(actionGoal);
		mQuickAction.addActionItem(actionFora);		
		mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			public void onItemClick(int pos) {
				if (pos == 0) { // Phone item selected
					Toast.makeText(TelaScouting.this, "PHONE item selected",Toast.LENGTH_SHORT).show();
                                        // Place code handling for Phone action here
				} else if (pos == 1) { // Gmail item selected
					Toast.makeText(TelaScouting.this, "GMAIL item selected",Toast.LENGTH_SHORT).show();
                                        // Place code handling for Gmail action here
				} else if (pos == 2) { // Talk item selected
					Toast.makeText(TelaScouting.this, "TALK selected",Toast.LENGTH_SHORT).show();
                                        // Place code handling for Talk action here
				}
			}
		});*/
		
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
					Evento eventoGol = new Evento(1, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoGol);
					Log.i("EventoGol ---- ", eventoGol.getId_cat()+"");
				} else if (pos == 1) { 
					Evento eventoGoleiro = new Evento(4, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoGoleiro);
					Log.i("EventoGoleiro ---- ", eventoGoleiro.getId_cat()+"");
				} else if (pos == 2) { 
					Evento eventoFora = new Evento(3, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFora);
					Log.i("EventoFora ---- ", eventoFora.getId_cat()+"");
				} else if (pos == 3) {
					Evento eventoDefesa = new Evento(2, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoDefesa);
					Log.i("EventoDefesa ---- ", eventoDefesa.getId_cat()+"");
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
					Evento eventoFaltaTec = new Evento(10, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFaltaTec);
					Log.i("EventoFaltaTec ---- ", eventoFaltaTec.getId_cat()+"");
				} else if (pos == 1) { 
					Evento eventoFaltaDef = new Evento(11, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFaltaDef);
					Log.i("EventoFaltaDef ---- ", eventoFaltaDef.getId_cat()+"");
				} else if (pos == 2) { 
					Evento eventoFaltaAtk= new Evento(12, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(eventoFaltaAtk);
					Log.i("EventoFaltaAtk ---- ", eventoFaltaAtk.getId_cat()+"");
				} else if (pos == 3) {
					Evento evento7m = new Evento(13, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(evento7m);
					Log.i("EventoFalta7m ---- ", evento7m.getId_cat()+"");
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
					Evento passeCerto = new Evento(5, jogadorQuePassou, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(passeCerto);
					Log.i("PASSE CERTO: ", "De "+jogadorQuePassou+" para "+onClickJog);
					Evento rcpCerta = new Evento(7, onClickJog, id_ptda, 0, 0);
					EventoDAO.getInstancia(TelaScouting.this).Inserir(rcpCerta);
					Log.i("RECEPÇÃO CERTA: ", "De "+onClickJog);
				}
			}
			
		});	
		
		btAr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				arQuick.show(v);
				arQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		//setOnClickListener para bt	
		
		
		/*btPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cronosJogo.isActivated()) {
					cronosJogo.start();
					habilitaBotoes(true);
				}else {
					cronosJogo.stop();
					habilitaBotoes(false);
				}
                				
			}
		});*/
		
		/*btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cronosTempo.start();
				habilitaBotoes(false);
				cronosJogo.stop();
			}
		});*/
		
	}
	
	/*private void habilitaBotoes(boolean habilita) {
		if(habilita) {
			cronosTempo.setVisibility(View.INVISIBLE);
			cronosTempo.stop();
		} else {
			cronosTempo.setVisibility(View.VISIBLE);
			cronosTempo.start();
		}
		bt2min.setActivated(habilita);
		btAr.setActivated(habilita);
		btPasse.setActivated(habilita);
		btCtAmarelo.setActivated(habilita);
		btFalta.setActivated(habilita);
		btPlayPause.setActivated(habilita);
		listaJogadores.setActivated(habilita);
	}*/

}
