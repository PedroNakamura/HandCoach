package com.example.handcoach.telaPartidas.Scouting;

import java.util.ArrayList;
import java.util.List;

import com.example.handcoach.R;
import com.example.handcoach.telaPartidas.Scouting.quickAction.ActionItem;
import com.example.handcoach.telaPartidas.Scouting.quickAction.QuickAction;
import com.example.handcoach.telaPartidas.Scouting.quickAction.QuickAction.OnActionItemClickListener;

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
import android.widget.Toast;

public class FragmentInitScout extends Fragment {
	
	private Button comBola;
	private Button semBola;
	private List<Jogador> joga = new ArrayList<Jogador>();
	private QuickAction choosePlayerQuick;
	private ActionItem jogador0;
	private ActionItem jogador1;
	private ActionItem jogador2;
	private ActionItem jogador3;
	private ActionItem jogador4;
	private ActionItem jogador5;
	private ActionItem jogador6;
	private TelaScouting telaS;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_init_scout, container, false);
		Toast.makeText(getActivity(), R.string.initScouting, Toast.LENGTH_LONG).show();
		
		geraJogadores();
		geraChoosePlayerQuick();
		
		telaS = (TelaScouting) getActivity();
		comBola = (Button) v.findViewById(R.id.bt_comBola);
		semBola = (Button) v.findViewById(R.id.bt_semBola);
		comBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				choosePlayerQuick.show(v);
				choosePlayerQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		semBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				telaS.habilitaPlayPause(true);
				telaS.semBola();
				telaS.cronometroJogo.resume();
			}
		});
		
		return v;
	}
	
	private void geraJogadores() {
		
		for(Jogador jog : TelaScouting.jogadores) {
			if(jog.isTitular()) {
				Log.i("Jogador: ", jog.getNome()+" ");
				joga.add(jog);
			}
		}
		
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
	
	private void geraChoosePlayerQuick() {
		choosePlayerQuick = new QuickAction(getActivity());
		
		choosePlayerQuick.addActionItem(jogador0);
		choosePlayerQuick.addActionItem(jogador1);
		choosePlayerQuick.addActionItem(jogador2);
		choosePlayerQuick.addActionItem(jogador3);
		choosePlayerQuick.addActionItem(jogador4);
		choosePlayerQuick.addActionItem(jogador5);
		choosePlayerQuick.addActionItem(jogador6);
		
		choosePlayerQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					telaS.jogadorComBola = jogador0.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				} else if(pos == 1) {
					telaS.jogadorComBola = jogador1.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				} else if(pos == 2) {
					telaS.jogadorComBola = jogador2.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				} else if(pos == 3) {
					telaS.jogadorComBola = jogador3.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				} else if(pos == 4) {
					telaS.jogadorComBola = jogador4.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				} else if(pos == 5) {
					telaS.jogadorComBola = jogador5.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				} else if(pos == 6) {
					telaS.jogadorComBola = jogador6.getId();
					telaS.habilitaPlayPause(true);
					telaS.comBolaS();
				}
			}
		});
		
	}

}
