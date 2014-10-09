package com.example.handcoach.telaPartidas.Scouting;

import com.example.handcoach.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentInitScout extends Fragment {
	
	private Button comBola;
	private Button semBola;
	private TelaScouting telaS;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_init_scout, container, false);
		Toast.makeText(getActivity(), R.string.initScouting, Toast.LENGTH_LONG).show();
		
		telaS = (TelaScouting) getActivity();
		comBola = (Button) v.findViewById(R.id.bt_comBola);
		semBola = (Button) v.findViewById(R.id.bt_semBola);
		comBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				telaS.comBola();
				telaS.cronometroJogo.resume();
			}
		});
		
		semBola.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				telaS.semBola();
				telaS.cronometroJogo.resume();
			}
		});
		
		return v;
	}

}
