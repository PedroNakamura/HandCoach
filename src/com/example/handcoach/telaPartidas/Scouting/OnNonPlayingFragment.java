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
	
	private ActionItem jogador0;
	private ActionItem jogador1;
	private ActionItem jogador2;
	private ActionItem jogador3;
	private ActionItem jogador4;
	private ActionItem jogador5;
	private ActionItem jogador6;
	
	private QuickAction gbQuick;
	private QuickAction ftQuick;
	private QuickAction rbdQuick;
	private QuickAction ctamarQuick;
	private QuickAction minQuick;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onnonplayingfragment, container, false);
		
		Button btGanhoBola = (Button) v.findViewById(R.id.btGanhoSB);
		Button btFalta = (Button) v.findViewById(R.id.btFaltaSB);
		Button btRoubada = (Button) v.findViewById(R.id.btRoubadaSB);
		Button btCtAmarelo = (Button) v.findViewById(R.id.btCtAmareloSB);
		Button bt2min = (Button) v.findViewById(R.id.bt2minSB);
		
		getJogadores();
		criaItensJogadores();
		criaQuickGb();
		criaQuickFt();
		criaQuickCtAmarelo();
		criaQuickRbd();
		criaQuick2min();
		
		btGanhoBola.setOnClickListener(new OnClickListener() {
			
		   @Override 
		   public void onClick(View v) {
			   gbQuick.show(v);
			   gbQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			   telaS.comBola();
	       }
	    });
		
		btFalta.setOnClickListener(new OnClickListener() {
				
			@Override 
			public void onClick(View v) {
				
		    }
		});
		
		btRoubada.setOnClickListener(new OnClickListener() {
			
		   @Override 
		   public void onClick(View v) {
			   rbdQuick.show(v);
			   rbdQuick.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			   telaS.comBola();
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
					break;
				case 1:
					telaS.jogadorComBola = jogador1.getId();
					break;
				case 2:
					telaS.jogadorComBola = jogador2.getId();
					break;
				case 3:
					telaS.jogadorComBola = jogador3.getId();
					break;
				case 4:
					telaS.jogadorComBola = jogador4.getId();
					break;
				case 5:
					telaS.jogadorComBola = jogador5.getId();
					break;
				case 6:
					telaS.jogadorComBola = jogador6.getId();
					break;
				}
			}
		});
	}
	
	private void criaQuickFt() {
		ftQuick = new QuickAction(getActivity());
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
				} else if(pos == 1) {
					Evento eventoCtAmarelo = new Evento(14, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
				} else if(pos == 2) {
					Evento eventoCtAmarelo = new Evento(14, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
				} else if(pos == 3) {
					Evento eventoCtAmarelo = new Evento(14, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
				} else if(pos == 4) {
					Evento eventoCtAmarelo = new Evento(14, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
				} else if(pos == 5) {
					Evento eventoCtAmarelo = new Evento(14, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
				} else if(pos == 6) {
					Evento eventoCtAmarelo = new Evento(14, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoCtAmarelo);
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
					telaS.jogadorComBola = jogador0.getId();
					Evento eventoRbdBola = new Evento(9, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 1) {
					telaS.jogadorComBola = jogador1.getId();
					Evento eventoRbdBola = new Evento(9, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 2) {
					telaS.jogadorComBola = jogador2.getId();
					Evento eventoRbdBola = new Evento(9, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 3) {
					telaS.jogadorComBola = jogador3.getId();
					Evento eventoRbdBola = new Evento(9, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 4) {
					telaS.jogadorComBola = jogador4.getId();
					Evento eventoRbdBola = new Evento(9, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 5) {
					telaS.jogadorComBola = jogador5.getId();
					Evento eventoRbdBola = new Evento(9, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 6) {
					telaS.jogadorComBola = jogador6.getId();
					Evento eventoRbdBola = new Evento(9, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
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
					Evento eventoRbdBola = new Evento(15, jogador0.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 1) {
					Evento eventoRbdBola = new Evento(15, jogador1.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 2) {
					Evento eventoRbdBola = new Evento(15, jogador2.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 3) {
					Evento eventoRbdBola = new Evento(15, jogador3.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 4) {
					Evento eventoRbdBola = new Evento(15, jogador4.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 5) {
					Evento eventoRbdBola = new Evento(15, jogador5.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				} else if(pos == 6) {
					Evento eventoRbdBola = new Evento(15, jogador6.getId(), id_ptda, 0, 0);
					EventoDAO.getInstancia(getActivity()).Inserir(eventoRbdBola);
				}
			}
		});
	}

}
