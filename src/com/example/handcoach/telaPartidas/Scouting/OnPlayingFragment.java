package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import java.util.List;
import com.example.handcoach.R;
import com.example.handcoach.telaPartidas.Scouting.quickAction.ActionItem;
import com.example.handcoach.telaPartidas.Scouting.quickAction.QuickAction;
import com.example.handcoach.telaPartidas.Scouting.quickAction.QuickAction.OnActionItemClickListener;
import DAO.EventoDAO;
import Entidades.Evento;
import Entidades.Jogador;
import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class OnPlayingFragment extends Fragment {
	
	private TelaScouting telaS;
	private List<Jogador> joga = new ArrayList<Jogador>();
	private int id_ptda = TelaScouting.id_ptda;
	private Jogador jogadorComBola;
	
	private QuickAction arrQuick;
	private QuickAction ftQuick;
	private QuickAction jogFtQuick;
	private QuickAction amarQuick;
	private QuickAction minQuick;
	
	private ActionItem jogador0;
	private ActionItem jogador1;
	private ActionItem jogador2;
	private ActionItem jogador3;
	private ActionItem jogador4;
	private ActionItem jogador5;
	private ActionItem jogador6;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onplayingfragment, container, false);
		
		telaS = (TelaScouting) getActivity();
		
		Button btArremesso = (Button) v.findViewById(R.id.btArremessoCB);
		Button btFalta = (Button) v.findViewById(R.id.btFaltaCB);
		Button btPerdaBola = (Button) v.findViewById(R.id.btPerdaBolaCB);
		Button btCtAmarelo = (Button) v.findViewById(R.id.btCtAmareloCB);
		Button bt2min = (Button) v.findViewById(R.id.bt2minutosCB);
		ImageButton btJogadorCB = (ImageButton) v.findViewById(R.id.jogadorCB);
		ImageButton btJogadorSB1 = (ImageButton) v.findViewById(R.id.jogadorSB1);
		ImageButton btJogadorSB2 = (ImageButton) v.findViewById(R.id.jogadorSB2);
		ImageButton btJogadorSB3 = (ImageButton) v.findViewById(R.id.jogadorSB3);
		ImageButton btJogadorSB4 = (ImageButton) v.findViewById(R.id.jogadorSB4);
		ImageButton btJogadorSB5 = (ImageButton) v.findViewById(R.id.jogadorSB5);
		ImageButton btJogadorSB6 = (ImageButton) v.findViewById(R.id.jogadorSB6);
		
		getJogadores();
		criaItensJogadores();
		criaQuickAr();
		criaQuickFt();	
		criaAmarQuick();
		criaMinQuick();
		
		btArremesso.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				arrQuick.show(v);
				arrQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		btFalta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ftQuick.show(v);
				ftQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		btPerdaBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		btCtAmarelo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				amarQuick.show(v);
				amarQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		bt2min.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				minQuick.show(v);
				minQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		return v;
	}
	//saiu do onCreate
	
	private void getJogadores() {
		for(Jogador jog : TelaScouting.jogadores) {
			if(jog.isTitular()) {
				Log.i("Jogador: ", jog.getNome()+" ");
				joga.add(jog);
			}
		}
	}
	
	private void criaQuickAr() {
		arrQuick = new QuickAction(getActivity());
		
		ActionItem arGol = new ActionItem();
		arGol.setTitle(getResources().getString(R.string.gol));
		
		ActionItem arGK = new ActionItem();
		arGK.setTitle(getResources().getString(R.string.goleiro));
		
		ActionItem arFora = new ActionItem();
		arFora.setTitle(getResources().getString(R.string.fora));
		
		ActionItem arDefesa = new ActionItem();
		arDefesa.setTitle(getResources().getString(R.string.defense));
		
		arrQuick.addActionItem(arGol);
		arrQuick.addActionItem(arGK);
		arrQuick.addActionItem(arFora);
		arrQuick.addActionItem(arDefesa);
		
		arrQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento evento = new Evento(1, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					telaS.golEq();
				} else if(pos == 1) {
					Evento evento = new Evento(4, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					telaS.semBolaS();
				} else if(pos == 2) {
					Evento evento = new Evento(3, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					telaS.semBolaS();
				} else if(pos == 3) {
					Evento evento = new Evento(2, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					telaS.semBolaS();
				}
			}
		});
	}
	
	private void criaQuickFt() {
		ftQuick = new QuickAction(getActivity());
		jogFtQuick = new QuickAction(getActivity());
		
		ActionItem ftAtk = new ActionItem();
		ftAtk.setTitle(getResources().getString(R.string.faltaatk));
		
		ActionItem ftTec = new ActionItem();
		ftTec.setTitle(getResources().getString(R.string.faltatecnica));
		
		ftQuick.addActionItem(ftAtk);
		ftQuick.addActionItem(ftTec);
		
		ftQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					
				} else if(pos == 1) {
					
				}
			}
		});
	}
	
	private void criaAmarQuick() {
		amarQuick = new QuickAction(getActivity());
	}
	
	private void criaMinQuick() {
		minQuick = new QuickAction(getActivity());
	}
	
	private void criaItensJogadores() {
		jogador0 = new ActionItem();
		jogador0.setId(joga.get(0).getId());
		jogador0.setTitle(joga.get(0).getNome());
		jogador0.setIcon(new BitmapDrawable(getResources(), joga.get(0).getFoto()));
		
		jogador1 = new ActionItem();
		jogador1.setId(joga.get(1).getId());
		jogador1.setTitle(joga.get(1).getNome());
		jogador1.setIcon(new BitmapDrawable(getResources(), joga.get(1).getFoto()));
		
		jogador2 = new ActionItem();
		jogador2.setId(joga.get(2).getId());
		jogador2.setTitle(joga.get(2).getNome());
		jogador2.setIcon(new BitmapDrawable(getResources(), joga.get(2).getFoto()));
		
		jogador3 = new ActionItem();
		jogador3.setId(joga.get(3).getId());
		jogador3.setTitle(joga.get(3).getNome());
		jogador3.setIcon(new BitmapDrawable(getResources(), joga.get(3).getFoto()));
		
		jogador4 = new ActionItem();
		jogador4.setId(joga.get(4).getId());
		jogador4.setTitle(joga.get(4).getNome());
		jogador4.setIcon(new BitmapDrawable(getResources(), joga.get(4).getFoto()));
		
		jogador5 = new ActionItem();
		jogador5.setId(joga.get(5).getId());
		jogador5.setTitle(joga.get(5).getNome());
		jogador5.setIcon(new BitmapDrawable(getResources(), joga.get(5).getFoto()));
		
		jogador6 = new ActionItem();
		jogador6.setId(joga.get(6).getId());
		jogador6.setTitle(joga.get(6).getNome());
		jogador6.setIcon(new BitmapDrawable(getResources(), joga.get(6).getFoto()));
	}

}
