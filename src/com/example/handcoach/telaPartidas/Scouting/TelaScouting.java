package com.example.handcoach.telaPartidas.Scouting;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.example.handcoach.R;
import com.example.handcoach.telaPartidas.jogadores.LazyAdapter;

import DAO.Evento;
import DAO.Jogador;
import DAO.JogadorDAO;
import DAO.Partida;
import DAO.PartidaDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

//http://www.vogella.com/tutorials/AndroidListView/article.html
//http://www.guj.com.br/4372-listview-multiselecionavel-com-checkbox-selecionar-varios-itens-de-uma-listview

public class TelaScouting extends Activity {
	
	Intent it;
	Bundle valores;
	List<Integer> listaJog;
	List<Jogador> jogadores;
	String local;
	int id_eq;
	int id_eqadv;
	int id_ptda;
	Date data;
	int onClickJog;
	
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
	
	//GAMBIARRA AFFE
    public Date stringToDate(String dataStr) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try {
                data = (Date) format.parse(dataStr);
        } catch (ParseException e) {
                e.printStackTrace();
        }
        return data;
    }
    //GAMBIARRA
    
	// MÁGICA COMEÇA A ACONTECER!
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		ImageButton btTempo = (ImageButton) findViewById(R.id.btTempo_1min);
		ListView listaJogadores = (ListView) findViewById(R.id.listViewScouting);
		jogadores = new ArrayList<Jogador>();
		it = getIntent();
		valores = it.getExtras();
		
		Button btFalta = (Button) findViewById(R.id.btFalta);
		Button btPasse = (Button) findViewById(R.id.btPasse);
		Button btAr = (Button) findViewById(R.id.btAr);
		Button btCtAmarelo = (Button) findViewById(R.id.ctAmarelo);
		Button bt2min = (Button) findViewById(R.id.bt2min);
			
		//pega o restante dos valores do bundle
		local = valores.getString("Local");
		id_eq = valores.getInt("id_equipe");
		id_eqadv = valores.getInt("id_equipeAdv");
		
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
		
		if(PartidaDAO.getInstancia(this).buscarTodos() == null) {
			id_ptda = 1;
		} else {
			int id_atual = PartidaDAO.getInstancia(this).buscarMaiorID();
			id_ptda = id_atual+1;
		}
		
		try {
			Partida partida = new Partida(id_ptda, id_eq, id_eqadv, local, stringToDate(strDate));
			PartidaDAO.getInstancia(this).Inserir(partida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
					Evento eventoGol; //faz o resto depois, te fode ae te achando
				} else if (pos == 1) { // Gmail item selected
					Toast.makeText(TelaScouting.this, "GMAIL item selected",Toast.LENGTH_SHORT).show();
                                        // Place code handling for Gmail action here
				} else if (pos == 2) { // Talk item selected
					Toast.makeText(TelaScouting.this, "TALK selected",Toast.LENGTH_SHORT).show();
                                        // Place code handling for Talk action here
				} else if (pos == 3) {
					
				}
			}
		});
		
		listaJogadores.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onClickJog = ((Jogador) jogadorLista.getItem(position)).getId();
			}
			
		});	
		
		//setOnClickListener para bt
		
		
		/*btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});*/
		
	}

}
