package com.example.handcoach.telaPartidas.Scouting;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.example.handcoach.R;
import DAO.Jogador;
import DAO.JogadorDAO;
import DAO.Partida;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
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
	Date data;
	
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
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_scouting);
		
		ImageButton btTempo = (ImageButton) findViewById(R.id.btTempo_1min);
		jogadores = new ArrayList<Jogador>();
		it = getIntent();
		valores = it.getExtras();
		
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
		String strDate = sdf.format(c.getTime());	
		//Pronto!
		
		try {
			Partida partida = new Partida(id_eq, id_eqadv, local, stringToDate(strDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Cria actionItem - phone
		ActionItem actionPhone = new ActionItem();
		actionPhone.setTitle("Telefone");
		actionPhone.setIcon(getResources().getDrawable(R.drawable.phone));
		
		//Cria actionItem - Gmail
		ActionItem actionMail = new ActionItem();
		actionMail.setTitle("GMAIL");
		actionMail.setIcon(getResources().getDrawable(R.drawable.gmail));
		
		//Cria actionItem - Talk
		ActionItem actionTalk = new ActionItem();
		actionTalk.setTitle("Fala, vivente!");
		actionTalk.setIcon(getResources().getDrawable(R.drawable.talk));
		
		//Instancia QuickAction
		final QuickAction mQuickAction = new QuickAction(this);
		mQuickAction.addActionItem(actionPhone);
		mQuickAction.addActionItem(actionMail);
		mQuickAction.addActionItem(actionTalk);		
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
		});
		
		//setOnClickListener para bt
		btTempo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
	}

}
