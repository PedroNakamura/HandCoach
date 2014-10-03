package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OnTimeFragment extends Fragment {
	
	TextView onTime;
	ScoutingCountdown contTempo;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ontimefragment, container);
		onTime = (TextView) v.findViewById(R.id.tv_onTime);
		contTempo = new ScoutingCountdown(20000, 1000, false, 1, getActivity());
		contTempo.setText(onTime);
		contTempo.create();
		return v;
	}

}
