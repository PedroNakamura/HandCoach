package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class OnPauseFragment extends Fragment {
	
	ImageButton btOnPlayPause;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.onpausefragment, container);
		btOnPlayPause = (ImageButton) v.findViewById(R.id.btOnPlayPause);
		btOnPlayPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OnPlayingFragment onPlay = new OnPlayingFragment();
				FragmentTransaction  transaction = getFragmentManager().beginTransaction();
		        transaction.replace(R.id.fragmentContent, onPlay);
		        transaction.addToBackStack(null);
		        transaction.commit();
			}
		});
		return v;
	}

}
