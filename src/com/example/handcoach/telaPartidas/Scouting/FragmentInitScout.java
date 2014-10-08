package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentInitScout extends Fragment {
	
	Button comBola;
	Button semBola;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_init_scout, container);
		Toast.makeText(getActivity(), R.string.initScouting, Toast.LENGTH_LONG).show();
		
		comBola = (Button) v.findViewById(R.id.bt_comBola);
		semBola = (Button) v.findViewById(R.id.bt_semBola);
		comBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Fragment fragment = new OnPlayingFragment();
				FragmentTransaction  transaction = getFragmentManager().beginTransaction();
		        transaction.replace(R.id.fragmentContent, fragment);
		        transaction.addToBackStack(null);
		        transaction.commit();
			}
		});
		
		semBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment fragment = new OnNonPlayingFragment();
				FragmentTransaction  transaction = getFragmentManager().beginTransaction();
		        transaction.replace(R.id.fragmentContent, fragment);
		        transaction.addToBackStack(null);
		        transaction.commit();
			}
		});
		
		return v;
	}

}
