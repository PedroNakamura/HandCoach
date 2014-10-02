package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class onPauseFragment extends Fragment {
	
	Button btOnPlayPause;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onpausefragment, container);
		btOnPlayPause = (Button) v.findViewById(R.id.btOnPlayPause);
		btOnPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		return v;
	}

}
