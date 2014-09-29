package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;
import com.example.handcoach.R;
import DAO.JogadorDAO;
import Entidades.Jogador;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MenuPreJogo_listaReservas extends MenuPreJogo_listaTitulares {
	
	View v;
	List<Jogador> listaJogadores;
	JogadorListaAdapter jogadorAdapter;
	ListView listaReservas;
	Bundle valor;
	Intent it;
	int id_eq;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.menu_prejogo_listareservas, container, false);
		getCoisas();
	    listaReservas = (ListView) v.findViewById(R.id.listaReservas);
	    listaJogadores = JogadorDAO.getInstancia(getActivity()).buscarDaEquipe(id_eq);
		jogadorAdapter = new JogadorListaAdapter(getActivity(), listaJogadores);
		listaReservas.setAdapter(jogadorAdapter);
		return v;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public List<Integer> getReservas() {
		return jogadorAdapter.getSelecionados();
	}
	
	@Override
	public void getCoisas() {
		it = getActivity().getIntent();
		valor = it.getExtras();
		id_eq = valor.getInt("id_equipe");
		Log.i("DEBUGG", id_eq+" ID da Equipe");
	}

}
