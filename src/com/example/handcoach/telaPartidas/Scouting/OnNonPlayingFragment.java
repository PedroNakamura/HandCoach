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

public class OnNonPlayingFragment extends Fragment {
	
	private TelaScouting telaS;
	private List<Jogador> joga = new ArrayList<Jogador>();
	private int id_ptda = TelaScouting.id_ptda;
	private int eventinho;
	private View vie;
	
	private ActionItem jogador0;
	private ActionItem jogador1;
	private ActionItem jogador2;
	private ActionItem jogador3;
	private ActionItem jogador4;
	private ActionItem jogador5;
	private ActionItem jogador6;
	private ActionItem faltaTecnica;
	private ActionItem falta7m;
	private ActionItem faltaDefesa;
	
	private QuickAction gbQuick;
	private QuickAction ftQuick;
	private QuickAction rbdQuick;
	private QuickAction ctamarQuick;
	private QuickAction minQuick;
	private QuickAction insideQuick;
	private QuickAction rbtQuick;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onnonplayingfragment, container, false);
		
		Log.i("id_ptda: ", id_ptda+"");
		telaS = (TelaScouting) getActivity();
		
		Button btGanhoBola = (Button) v.findViewById(R.id.btGanhoSB);
		Button btFalta = (Button) v.findViewById(R.id.btFaltaSB);
		Button btRoubada = (Button) v.findViewById(R.id.btRoubadaSB);
		Button btCtAmarelo = (Button) v.findViewById(R.id.btCtAmareloSB);
		Button bt2min = (Button) v.findViewById(R.id.bt2minSB1);
		Button btRebote = (Button) v.findViewById(R.id.btReboteSB);
		Button btGolAdv = (Button) v.findViewById(R.id.btGolAdvSB);
		
		getJogadores();
		criaItensJogadores();
		criaQuickGb();
		criaQuickFt();
		criaQuickCtAmarelo();
		criaQuickRbd();
		criaQuick2min();
		criaQuickRebote();
		
		btGolAdv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				telaS.golEqAdv();
			}
		});
		
		btRebote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				rbtQuick.show(v);
				rbtQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
		
		btGanhoBola.setOnClickListener(new OnClickListener() {
			
		   @Override 
		   public void onClick(View v) {
			   gbQuick.show(v);
			   gbQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
	       }
	    });
		
		btFalta.setOnClickListener(new OnClickListener() {
				
			@Override 
			public void onClick(View v) {
				vie = v;
				ftQuick.show(v);
				ftQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
		    }
		});
		
		btRoubada.setOnClickListener(new OnClickListener() {
			
		   @Override 
		   public void onClick(View v) {
			   rbdQuick.show(v);
			   rbdQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
	       }
	    });
		
		btCtAmarelo.setOnClickListener(new OnClickListener() {
			
		   @Override 
		   public void onClick(View v) {
			   ctamarQuick.show(v);
			   ctamarQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
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
	
	
	//saiu do OnCreate
	private void getJogadores() {
		for(Jogador jog : TelaScouting.jogadores) {
			if(jog.isTitular()) {
				Log.i("Jogador: ", jog.getNome()+" ");
				joga.add(jog);
			}
		}
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
	
	private void criaQuickGb() {
		gbQuick = new QuickAction(getActivity());
		gbQuick.addActionItem(jogador0);
		gbQuick.addActionItem(jogador1);
		gbQuick.addActionItem(jogador2);
		gbQuick.addActionItem(jogador3);
		gbQuick.addActionItem(jogador4);
		gbQuick.addActionItem(jogador5);
		gbQuick.addActionItem(jogador6);
		
		gbQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				switch(pos) {
				case 0:
					telaS.jogadorComBola = jogador0.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador0.getId()+"");
					break;
				case 1:
					telaS.jogadorComBola = jogador1.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador1.getId()+"");
					break;
				case 2:
					telaS.jogadorComBola = jogador2.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador2.getId()+"");
					break;
				case 3:
					telaS.jogadorComBola = jogador3.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador3.getId()+"");
					break;
				case 4:
					telaS.jogadorComBola = jogador4.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador4.getId()+"");
					break;
				case 5:
					telaS.jogadorComBola = jogador5.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador5.getId()+"");
					break;
				case 6:
					telaS.jogadorComBola = jogador6.getId();
					telaS.comBolaS();
					Log.i("Recuperou a bola: ", jogador6.getId()+"");
					break;
				}
			}
		});
	}
	
	private void criaQuickFt() {
		faltaTecnica = new ActionItem();
		faltaTecnica.setTitle(getResources().getString(R.string.faltatecnica));
		faltaTecnica.setIcon(getResources().getDrawable(R.drawable.faltatecnica));
		
		faltaDefesa = new ActionItem();
		faltaDefesa.setTitle(getResources().getString(R.string.faltadefesa));
		faltaDefesa.setIcon(getResources().getDrawable(R.drawable.faltadefesa));
		
		falta7m = new ActionItem();
		falta7m.setTitle(getResources().getString(R.string.falta7m));
		falta7m.setIcon(getResources().getDrawable(R.drawable.metros7));
		
		ftQuick = new QuickAction(getActivity());
		insideQuick = new QuickAction(getActivity());
		
		ftQuick.addActionItem(faltaTecnica);
		ftQuick.addActionItem(falta7m);
		ftQuick.addActionItem(faltaDefesa);
		
		insideQuick.addActionItem(jogador0);
		insideQuick.addActionItem(jogador1);
		insideQuick.addActionItem(jogador2);
		insideQuick.addActionItem(jogador3);
		insideQuick.addActionItem(jogador4);
		insideQuick.addActionItem(jogador5);
		insideQuick.addActionItem(jogador6);
		
		insideQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento evento = new Evento(eventinho, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador0.getId()+"");
				} else if(pos == 1) {
					Evento evento = new Evento(eventinho, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador1.getId()+"");
				} else if(pos == 2) {
					Evento evento = new Evento(eventinho, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador2.getId()+"");
				} else if(pos == 3) {
					Evento evento = new Evento(eventinho, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador3.getId()+"");
				} else if(pos == 4) {
					Evento evento = new Evento(eventinho, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador4.getId()+"");
				} else if(pos == 5) {
					Evento evento = new Evento(eventinho, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador5.getId()+"");
				} else if(pos == 6) {
					Evento evento = new Evento(eventinho, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento);
					Log.i("Fez falta: "+eventinho+" -- ", jogador6.getId()+"");
				}
			}
		});
		
		ftQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				switch(pos) {
				case 0:
					eventinho = 10;
					insideQuick.show(vie);
					insideQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
					break;
				case 1:
					eventinho = 11;
					insideQuick.show(vie);
					insideQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
					break;
				case 2:
					eventinho = 13;
					insideQuick.show(vie);
					insideQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
					break;
				}
			}
		});
		
	}
	
	private void criaQuickCtAmarelo() {
		ctamarQuick = new QuickAction(getActivity());
		ctamarQuick.addActionItem(jogador0);
		ctamarQuick.addActionItem(jogador1);
		ctamarQuick.addActionItem(jogador2);
		ctamarQuick.addActionItem(jogador3);
		ctamarQuick.addActionItem(jogador4);
		ctamarQuick.addActionItem(jogador5);
		ctamarQuick.addActionItem(jogador6);
		
		ctamarQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento eventoCtAmarelo = new Evento(14, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo: ", jogador0.getId()+"");
				} else if(pos == 1) {
					Evento eventoCtAmarelo = new Evento(14, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo: ", jogador1.getId()+"");
				} else if(pos == 2) {
					Evento eventoCtAmarelo = new Evento(14, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo: ", jogador2.getId()+"");
				} else if(pos == 3) {
					Evento eventoCtAmarelo = new Evento(14, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo:", jogador3.getId()+"");
				} else if(pos == 4) {
					Evento eventoCtAmarelo = new Evento(14, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo:", jogador4.getId()+"");
				} else if(pos == 5) {
					Evento eventoCtAmarelo = new Evento(14, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo:", jogador5.getId()+"");
				} else if(pos == 6) {
					Evento eventoCtAmarelo = new Evento(14, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
					Log.i("Cartão amarelo:", jogador6.getId()+"");
				}
			}	
		});
	}
	
	private void criaQuickRbd() {
		rbdQuick = new QuickAction(getActivity());
		rbdQuick.addActionItem(jogador0);
		rbdQuick.addActionItem(jogador1);
		rbdQuick.addActionItem(jogador2);
		rbdQuick.addActionItem(jogador3);
		rbdQuick.addActionItem(jogador4);
		rbdQuick.addActionItem(jogador5);
		rbdQuick.addActionItem(jogador6);
		
		rbdQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					telaS.setJogadorComBola(jogador0.getId());
					Evento eventoRbdBola = new Evento(9, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador0.getId()+"");
					telaS.comBolaS();
				} else if(pos == 1) {
					telaS.setJogadorComBola(jogador1.getId());
					Evento eventoRbdBola = new Evento(9, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador1.getId()+"");
					telaS.comBolaS();
				} else if(pos == 2) {
					telaS.setJogadorComBola(jogador2.getId());
					Evento eventoRbdBola = new Evento(9, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador2.getId()+"");
					telaS.comBolaS();
				} else if(pos == 3) {
					telaS.setJogadorComBola(jogador3.getId());
					Evento eventoRbdBola = new Evento(9, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador3.getId()+"");
					telaS.comBolaS();
				} else if(pos == 4) {
					telaS.setJogadorComBola(jogador4.getId());
					Evento eventoRbdBola = new Evento(9, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador4.getId()+"");
					telaS.comBolaS();
				} else if(pos == 5) {
					telaS.setJogadorComBola(jogador5.getId());
					Evento eventoRbdBola = new Evento(9, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador5.getId()+"");
					telaS.comBolaS();
				} else if(pos == 6) {
					telaS.setJogadorComBola(jogador6.getId());
					Evento eventoRbdBola = new Evento(9, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
					Log.i("Roubada de bola: ", jogador6.getId()+"");
					telaS.comBolaS();
				}
			}	
		});
	}
	
	private void criaQuick2min() {
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
					Evento evento2min = new Evento(15, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador0.getId()+"");
				} else if(pos == 1) {
					Evento evento2min = new Evento(15, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador1.getId()+"");
				} else if(pos == 2) {
					Evento evento2min = new Evento(15, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador2.getId()+"");
				} else if(pos == 3) {
					Evento evento2min = new Evento(15, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador3.getId()+"");
				} else if(pos == 4) {
					Evento evento2min = new Evento(15, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador4.getId()+"");
				} else if(pos == 5) {
					Evento evento2min = new Evento(15, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador5.getId()+"");
				} else if(pos == 6) {
					Evento evento2min = new Evento(15, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(evento2min);
					Log.i("2 minutos: ", jogador6.getId()+"");
				}
			}
		});
	}
	
	private void criaQuickRebote() {
		rbtQuick = new QuickAction(getActivity());
		
		rbtQuick.addActionItem(jogador0);
		rbtQuick.addActionItem(jogador1);
		rbtQuick.addActionItem(jogador2);
		rbtQuick.addActionItem(jogador3);
		rbtQuick.addActionItem(jogador4);
		rbtQuick.addActionItem(jogador5);
		rbtQuick.addActionItem(jogador6);
		
		rbtQuick.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(int pos) {
				if(pos == 0) {
					Evento eventoRbt = new Evento(20, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador0.getId()+"");
					telaS.comBolaS();
				} else if(pos == 1) {
					Evento eventoRbt = new Evento(20, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador1.getId()+"");
					telaS.comBolaS();
				} else if(pos == 2) {
					Evento eventoRbt = new Evento(20, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador2.getId()+"");
					telaS.comBolaS();
				} else if(pos == 3) {
					Evento eventoRbt = new Evento(20, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador3.getId()+"");
					telaS.comBolaS();
				} else if(pos == 4) {
					Evento eventoRbt = new Evento(20, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador4.getId()+"");
					telaS.comBolaS();
				} else if(pos == 5) {
					Evento eventoRbt = new Evento(20, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador5.getId()+"");
					telaS.comBolaS();
				} else if(pos == 6) {
					Evento eventoRbt = new Evento(20, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbt);
					Log.i("Rebote: ", jogador6.getId()+"");
					telaS.comBolaS();
				}
			}
		});
		
	}
	
	protected void atualizarJogadores() {
		joga.clear();
		getJogadores();
		criaItensJogadores();
	}

}
