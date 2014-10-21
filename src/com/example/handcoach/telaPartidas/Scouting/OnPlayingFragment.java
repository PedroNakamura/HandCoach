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
import android.widget.TextView;

public class OnPlayingFragment extends Fragment {
	
	private TelaScouting telaS;
	private List<Jogador> joga = new ArrayList<Jogador>();
	private int id_ptda;
	private Jogador jogadorComBola;
	private Jogador ultimoComBola;
	private List<Jogador> jogadorSemBola = new ArrayList<Jogador>();
	private View viewzinha;
	private int tipo_evento;
	
	private QuickAction arrQuick;
	private QuickAction ftQuick;
	private QuickAction jogFtQuick;
	private QuickAction amarQuick;
	private QuickAction minQuick;
	private QuickAction pbQuick;
	
	private ActionItem jogador0;
	private ActionItem jogador1;
	private ActionItem jogador2;
	private ActionItem jogador3;
	private ActionItem jogador4;
	private ActionItem jogador5;
	private ActionItem jogador6;
	
	ImageButton btJogadorCB;
	ImageButton btJogadorSB1;
	private Jogador SB1;
	ImageButton btJogadorSB2;
	private Jogador SB2;
	ImageButton btJogadorSB3;
	private Jogador SB3;
	ImageButton btJogadorSB4;
	private Jogador SB4;
	ImageButton btJogadorSB5;
	private Jogador SB5;
	ImageButton btJogadorSB6;
	private Jogador SB6;
	TextView tvJogadorCB;
	TextView tvJogadorSB1;
	TextView tvJogadorSB2;
	TextView tvJogadorSB3;
	TextView tvJogadorSB4;
	TextView tvJogadorSB5;
	TextView tvJogadorSB6;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onplayingfragment, container, false);
		
		telaS = (TelaScouting) getActivity();
		id_ptda = telaS.id_ptda;
		
		Button btArremesso = (Button) v.findViewById(R.id.btArremessoCB);
		Button btFalta = (Button) v.findViewById(R.id.btFaltaCB);
		Button btPerdaBola = (Button) v.findViewById(R.id.btPerdaBolaCB);
		Button btCtAmarelo = (Button) v.findViewById(R.id.btCtAmareloCB);
		Button bt2min = (Button) v.findViewById(R.id.bt2minutosCB);
		btJogadorCB = (ImageButton) v.findViewById(R.id.jogadorCB);
		btJogadorSB1 = (ImageButton) v.findViewById(R.id.jogadorSB1);
		btJogadorSB2 = (ImageButton) v.findViewById(R.id.jogadorSB2);
		btJogadorSB3 = (ImageButton) v.findViewById(R.id.jogadorSB3);
		btJogadorSB4 = (ImageButton) v.findViewById(R.id.jogadorSB4);
		btJogadorSB5 = (ImageButton) v.findViewById(R.id.jogadorSB5);
		btJogadorSB6 = (ImageButton) v.findViewById(R.id.jogadorSB6);
		tvJogadorCB = (TextView) v.findViewById(R.id.tv_jogadorCB);
		tvJogadorSB1 = (TextView) v.findViewById(R.id.tv_jogadorSB1);
		tvJogadorSB2 = (TextView) v.findViewById(R.id.tv_jogadorSB2);
		tvJogadorSB3 = (TextView) v.findViewById(R.id.tv_jogadorSB3);
		tvJogadorSB4 = (TextView) v.findViewById(R.id.tv_jogadorSB4);
		tvJogadorSB5 = (TextView) v.findViewById(R.id.tv_jogadorSB5);
		tvJogadorSB6 = (TextView) v.findViewById(R.id.tv_jogadorSB6);
		
		getJogadores();
		getJogadorComBola();
		criaItensJogadores();
		criaQuickAr();
		criaQuickFt();	
		criaAmarQuick();
		criaMinQuick();
		criaPbQuick();
		setButtonJogadores();
		
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
				viewzinha = v;
			}
		});
		
		btPerdaBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pbQuick.show(v);
				pbQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
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
		
		btJogadorSB1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ultimoComBola = jogadorComBola;
				jogadorComBola = SB1;
				SB1 = ultimoComBola;
				telaS.jogadorComBola = jogadorComBola.getId();
				setButtonJogadores();
				Evento eventoPasse = new Evento(5, ultimoComBola.getId(), id_ptda, 0, 0);
				Evento eventoRcp = new Evento(7, jogadorComBola.getId(), id_ptda, 0, 0);
				Log.i("PASSE E RECEPÇÃO: ", "De "+ultimoComBola.getNome()+" para "+jogadorComBola.getNome());
				EventoDAO.getInstancia(getActivity()).Inserir(eventoPasse);
				EventoDAO.getInstancia(getActivity()).Inserir(eventoRcp);
			}
		});
		
		btJogadorSB2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ultimoComBola = jogadorComBola;
				jogadorComBola = SB2;
				SB2 = ultimoComBola;
				telaS.jogadorComBola = jogadorComBola.getId();
				setButtonJogadores();
			}
		});
		
		btJogadorSB3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ultimoComBola = jogadorComBola;
				jogadorComBola = SB3;
				SB3 = ultimoComBola;
				telaS.jogadorComBola = jogadorComBola.getId();
				setButtonJogadores();
			}
		});
		
		btJogadorSB4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ultimoComBola = jogadorComBola;
				jogadorComBola = SB4;
				SB4 = ultimoComBola;
				telaS.jogadorComBola = jogadorComBola.getId();
				setButtonJogadores();
			}
		});
		
		btJogadorSB5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ultimoComBola = jogadorComBola;
				jogadorComBola = SB5;
				SB5 = ultimoComBola;
				telaS.jogadorComBola = jogadorComBola.getId();
				setButtonJogadores();
			}
		});
		
		btJogadorSB6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ultimoComBola = jogadorComBola;
				jogadorComBola = SB6;
				SB6 = ultimoComBola;
				telaS.jogadorComBola = jogadorComBola.getId();
				setButtonJogadores();
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
	
	private void getJogadorComBola() {
		for(Jogador jog : joga) {
			if(jog.getId() == telaS.jogadorComBola) {
				jogadorComBola = jog;
			} else {
				jogadorSemBola.add(jog);
			}
		}
		SB1 = jogadorSemBola.get(0);
		SB2 = jogadorSemBola.get(1);
		SB3 = jogadorSemBola.get(2);
		SB4 = jogadorSemBola.get(3);
		SB5 = jogadorSemBola.get(4);
		SB6 = jogadorSemBola.get(5);
	}
	
	private void setButtonJogadores() {
		btJogadorCB.setImageBitmap(jogadorComBola.getFoto());
		tvJogadorCB.setText(jogadorComBola.getNome());
		
		btJogadorSB1.setImageBitmap(SB1.getFoto());
		tvJogadorSB1.setText(SB1.getNome());
		
		btJogadorSB2.setImageBitmap(SB2.getFoto());
		tvJogadorSB2.setText(SB2.getNome());
		
		btJogadorSB3.setImageBitmap(SB3.getFoto());
		tvJogadorSB3.setText(SB3.getNome());
		
		btJogadorSB4.setImageBitmap(SB4.getFoto());
		tvJogadorSB4.setText(SB4.getNome());
		
		btJogadorSB5.setImageBitmap(SB5.getFoto());
		tvJogadorSB5.setText(SB5.getNome());
		
		btJogadorSB6.setImageBitmap(SB6.getFoto());
		tvJogadorSB6.setText(SB6.getNome());		
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
					Log.i("ARREMESSO: ", jogadorComBola.getId()+"");
					telaS.golEq();
				} else if(pos == 1) {
					Evento evento = new Evento(4, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("ARREMESSO: ", jogadorComBola.getId()+"");
					telaS.semBolaS();
				} else if(pos == 2) {
					Evento evento = new Evento(3, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("ARREMESSO: ", jogadorComBola.getId()+"");
					telaS.semBolaS();
				} else if(pos == 3) {
					Evento evento = new Evento(2, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("ARREMESSO: ", jogadorComBola.getId()+"");
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
		
		jogFtQuick.addActionItem(jogador0);
		jogFtQuick.addActionItem(jogador1);
		jogFtQuick.addActionItem(jogador2);
		jogFtQuick.addActionItem(jogador3);
		jogFtQuick.addActionItem(jogador4);
		jogFtQuick.addActionItem(jogador5);
		jogFtQuick.addActionItem(jogador6);
		
		ftQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					tipo_evento = 12;
					jogFtQuick.show(viewzinha);
					jogFtQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
				} else if(pos == 1) {
					tipo_evento = 10;
					jogFtQuick.show(viewzinha);
					jogFtQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
				}
			}
		});
		
		jogFtQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento evento = new Evento(tipo_evento, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador0.getId()+"");
					telaS.semBolaS();
				} else if(pos == 1) {
					Evento evento = new Evento(tipo_evento, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador1.getId()+"");
					telaS.semBolaS();
				} else if(pos == 2) {
					Evento evento = new Evento(tipo_evento, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador2.getId()+"");
					telaS.semBolaS();
				} else if(pos == 3) {
					Evento evento = new Evento(tipo_evento, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador3.getId()+"");
					telaS.semBolaS();
				} else if(pos == 4) {
					Evento evento = new Evento(tipo_evento, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador4.getId()+"");
					telaS.semBolaS();
				} else if(pos == 5) {
					Evento evento = new Evento(tipo_evento, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador5.getId()+"");
					telaS.semBolaS();
				} else if(pos == 6) {
					Evento evento = new Evento(tipo_evento, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador Falta: ", jogador6.getId()+"");
					telaS.semBolaS();
				}
			}
		});
	}
	
	private void criaAmarQuick() {
		amarQuick = new QuickAction(getActivity());
		
		amarQuick.addActionItem(jogador0);
		amarQuick.addActionItem(jogador1);
		amarQuick.addActionItem(jogador2);
		amarQuick.addActionItem(jogador3);
		amarQuick.addActionItem(jogador4);
		amarQuick.addActionItem(jogador5);
		amarQuick.addActionItem(jogador6);
		
	    amarQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento evento = new Evento(14, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				} else if(pos == 1) {
					Evento evento = new Evento(14, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				} else if(pos == 2) {
					Evento evento = new Evento(14, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				} else if(pos == 3) {
					Evento evento = new Evento(14, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				} else if(pos == 4) {
					Evento evento = new Evento(14, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				} else if(pos == 5) {
					Evento evento = new Evento(14, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				} else if(pos == 6) {
					Evento evento = new Evento(14, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Jogador CT Amarelo: ", ""+jogador0.getId());
				}
			}
		});
		
	}
	
	private void criaMinQuick() {
		minQuick = new QuickAction(getActivity());
		
		minQuick.addActionItem(jogador0);
		minQuick.addActionItem(jogador1);
		minQuick.addActionItem(jogador2);
		minQuick.addActionItem(jogador3);
		minQuick.addActionItem(jogador4);
		minQuick.addActionItem(jogador5);
		minQuick.addActionItem(jogador6);
		
		minQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento evento = new Evento(14, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				} else if(pos == 1) {
					Evento evento = new Evento(14, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				} else if(pos == 2) {
					Evento evento = new Evento(14, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				} else if(pos == 3) {
					Evento evento = new Evento(14, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				} else if(pos == 4) {
					Evento evento = new Evento(14, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				} else if(pos == 5) {
					Evento evento = new Evento(14, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				} else if(pos == 6) {
					Evento evento = new Evento(14, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Cartão Amarelo: ", jogador0.getId()+"");
				}
			}
		});
		
	}
	
	private void criaPbQuick() {
		pbQuick = new QuickAction(getActivity());
		
		ActionItem passeErrado = new ActionItem();
		passeErrado.setTitle(getResources().getString(R.string.passerrado));
		
		ActionItem rcpErrada = new ActionItem();
		rcpErrada.setTitle(getResources().getString(R.string.rcperrada));
		
		pbQuick.addActionItem(passeErrado);
		pbQuick.addActionItem(rcpErrada);
		
		pbQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento evento = new Evento(6, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Passe errado: ", ""+jogadorComBola.getNome());
					telaS.semBolaS();
				} else if(pos == 1) {
					EventoDAO.getInstancia(getActivity()).deletarUltimaCat(7);
					Evento evento = new Evento(8, jogadorComBola.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Recepção errada", jogadorComBola.getNome()+"");
					telaS.semBolaS();
				}
			}
		});
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
