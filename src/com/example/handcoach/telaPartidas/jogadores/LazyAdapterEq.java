package com.example.handcoach.telaPartidas.jogadores;

import java.util.List;
import com.example.handcoach.R;

import Entidades.Equipe;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapterEq extends BaseAdapter {

	private Activity act;
	private List<Equipe> listaEquipes;
	private static LayoutInflater inflater = null;
	
	public LazyAdapterEq(Activity a, List<Equipe> d) {
		act = a;
		listaEquipes = d;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void remove(Equipe equipe) {
		listaEquipes.remove(equipe);
	}
	
	public void add(Equipe equipe) {
		listaEquipes.add(equipe);
	}
	
	@Override
	public int getCount() {
		return listaEquipes.size();
	}

	@Override
	public Object getItem(int position) {
		return this.listaEquipes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView == null) {
			vi = inflater.inflate(R.layout.list_row_eq, null);
		}
		TextView teamName = (TextView) vi.findViewById(R.id.EquipeNome_listView);
		Equipe equipe = listaEquipes.get(position);
		teamName.setText(equipe.getNome());
		return vi;
	}

}
