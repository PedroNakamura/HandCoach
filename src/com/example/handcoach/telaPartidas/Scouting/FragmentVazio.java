package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentVazio extends MenuPreJogo_listaReservas {
	
	View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_vazio, container, false);	
		return v;
	}

}