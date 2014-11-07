package com.example.handcoach.telaPartidas.Scouting;

import util.ScoutingCountdown;

import com.example.handcoach.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OnTimeFragment extends Fragment {
	
	private TextView onTime;
	private ScoutingCountdown contTempo;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ontimefragment, container, false);
		onTime = (TextView) v.findViewById(R.id.tv_onTime);
		contTempo = new ScoutingCountdown(60000, 1000, false, 3, (TelaScouting) getActivity());
		contTempo.setText(onTime);
		contTempo.create();
		contTempo.resume();
		return v;
	}

}
