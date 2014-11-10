package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;

import util.JogadorTitularListaAdapter;

import com.example.handcoach.R;
import Entidades.Jogador;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class MenuPreJogo_listaTitulares extends Fragment {
	
	private View v;
	private List<Jogador> listaJogadores;
	private JogadorTitularListaAdapter jogadorAdapterT;
	private ListView listaTitulares;
	private Bundle valor;
	private Intent it;
	private int id_eq;
	
	public MenuPreJogo_listaTitulares(List<Jogador> listaJogadores){
		this.listaJogadores = listaJogadores;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.menu_prejogo_listatitulares, container, false);
		getCoisas();
	    listaTitulares = (ListView) v.findViewById(R.id.listaTitulares);
		jogadorAdapterT = new JogadorTitularListaAdapter(getActivity(), listaJogadores);
		listaTitulares.setAdapter(jogadorAdapterT);
		return v;
	}
	
	@Override
	public void onResume() { 
		super.onResume();
	}
	
	public void getCoisas() {
		it = getActivity().getIntent();
		valor = it.getExtras();
		id_eq = valor.getInt("id_equipe");
		Log.i("DEBUGG", id_eq+" ID da Equipe");
	}

}
