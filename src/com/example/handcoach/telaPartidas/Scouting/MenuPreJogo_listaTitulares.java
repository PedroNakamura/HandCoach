package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;
import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MenuPreJogo_listaTitulares extends Fragment {
	
	View v;
	List<Jogador> listaJogadores;
	JogadorListaAdapter jogadorAdapterT;
	ListView listaTitulares;
	Bundle valor;
	Intent it;
	int id_eq;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.menu_prejogo_listatitulares, container, false);
		getCoisas();
	    listaTitulares = (ListView) v.findViewById(R.id.listaTitulares);
	    listaJogadores = JogadorDAO.getInstancia(getActivity()).buscarDaEquipe(id_eq);
		jogadorAdapterT = new JogadorListaAdapter(getActivity(), listaJogadores);
		listaTitulares.setAdapter(jogadorAdapterT);
		return v;
	}
	
	@Override
	public void onResume() { 
		super.onResume();
	}
	
	public List<Integer> getTitulares() {
		return jogadorAdapterT.getSelecionados();
	}
	
	public void getCoisas() {
		it = getActivity().getIntent();
		valor = it.getExtras();
		id_eq = valor.getInt("id_equipe");
		Log.i("DEBUGG", id_eq+" ID da Equipe");
	}

}