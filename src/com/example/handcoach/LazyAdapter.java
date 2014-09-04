package com.example.handcoach;

import java.util.ArrayList;
import DAO.Jogador;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
	
	private Activity act;
	private ArrayList<Jogador> data;
	private static LayoutInflater inflater = null;
	public Bitmap Image;
	
	public LazyAdapter(Activity a, ArrayList<Jogador> d) {
		act = a;
		data = d;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView == null) {
			vi = inflater.inflate(R.layout.list_row, null);
		}
		TextView playerName = (TextView) vi.findViewById(R.id.JogadorNome_listView);
		TextView playerTeam = (TextView) vi.findViewById(R.id.JogadorEquipe_listView);
		TextView playerBorn = (TextView) vi.findViewById(R.id.JogadorDataNasc_listView);
		ImageView imagem = (ImageView) vi.findViewById(R.id.Jogador_list_image);
		
		ArrayList<Jogador> jogador = new ArrayList<Jogador>();
		jogador.get(position);
		
		playerName.setText(jogador.get(position).getNome());
		playerTeam.setText(jogador.get(position).getIdEq());
		playerBorn.setText(jogador.get(position).getDt_nasc().getDay()+"/"+jogador.get(position).getDt_nasc().getMonth()+"/"+jogador.get(position).getDt_nasc().getYear());
		imagem.setImageBitmap(jogador.get(position).getFoto());
		
		return vi;
	}
	

}
