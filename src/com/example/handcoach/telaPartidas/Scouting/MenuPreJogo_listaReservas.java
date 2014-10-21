package com.example.handcoach.telaPartidas.Scouting;

import java.util.List;

import util.JogadorReservaAdapter;

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

import com.example.handcoach.R;

@SuppressLint("ValidFragment")
public class MenuPreJogo_listaReservas extends Fragment {
	
	private View v;
	private JogadorReservaAdapter jogadorAdapter;
	private List<Jogador> listaJogadores;
	private ListView listaReservas;
	private Bundle valor;
	private Intent it;
	private int id_eq;
	
	public MenuPreJogo_listaReservas(List<Jogador> listaJogadores){
		this.listaJogadores = listaJogadores;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.menu_prejogo_listareservas, container, false);
		getCoisas();
	    listaReservas = (ListView) v.findViewById(R.id.listaReservas);
		jogadorAdapter = new JogadorReservaAdapter(getActivity(), listaJogadores);
		listaReservas.setAdapter(jogadorAdapter);
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
