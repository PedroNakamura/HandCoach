package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;

import DAO.PartidaDAO;
import Entidades.Partida;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class OnPauseFragment extends Fragment {
	
	private ImageButton btOnPlayPause;
	private Button btTerminaTudo;
	private TelaScouting telaS;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onpausefragment, container, false);
		telaS = (TelaScouting) getActivity();
		btTerminaTudo = (Button) v.findViewById(R.id.btTerminaTudo);
		
		btTerminaTudo.setActivated(false);
		btTerminaTudo.setVisibility(View.INVISIBLE);
		
		if(telaS.terminarPartida == true) {
			btTerminaTudo.setActivated(true);
			btTerminaTudo.setVisibility(View.VISIBLE);
		}
		
		btOnPlayPause = (ImageButton) v.findViewById(R.id.btOnPlayPause);
		btOnPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(telaS.posseBola) {
					telaS.comBola();
				} else {
					telaS.semBola();
				}
			}
		});
		
		btTerminaTudo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Partida partida = telaS.partida;
				partida.setGol_eq(telaS.placarEqCont);
				partida.setGol_adv(telaS.placarEqAdvCont);
				PartidaDAO.getInstancia(getActivity()).Editar(telaS.partida, partida);
				telaS.finish();
			}
		});
		
		//instancia quick actions
		
		return v;
	}

}
