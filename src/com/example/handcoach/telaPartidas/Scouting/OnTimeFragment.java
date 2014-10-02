package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class OnTimeFragment extends Fragment {
	
	Button btOnTime;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ontimefragment, container);
		btOnTime = (Button) v.findViewById(R.id.btOnTime);
		btOnTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		return v;
	}

}
