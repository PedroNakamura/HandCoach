package com.example.handcoach.telaPartidas.jogadores;

import java.util.List;
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

import com.example.handcoach.R;

public class LazyAdapter extends BaseAdapter {
	
	private Activity act;
	private List<Jogador> listaJogador;
	private static LayoutInflater inflater = null;
	public Bitmap Image;
	
	public LazyAdapter(Activity a, List<Jogador> d) {
		act = a;
		listaJogador = d;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void remove(Jogador jogador) {
		listaJogador.remove(jogador);
	}
	
	@Override
	public int getCount() {
		return listaJogador.size();
	}

	@Override
	public Object getItem(int position) {
		return this.listaJogador.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView == null) {
			vi = inflater.inflate(R.layout.list_row, null);
		}
		TextView playerName = (TextView) vi.findViewById(R.id.JogadorNome_listView);
		ImageView playerSex = (ImageView) vi.findViewById(R.id.JogadorSexo_listView);
		ImageView imagem = (ImageView) vi.findViewById(R.id.Jogador_list_image);
		
		//Calendar c = new GregorianCalendar();
		//c.setTime(listaJogador.get(position).getDt_nasc());
		
		Jogador jogador = listaJogador.get(position);
		
		playerName.setText(jogador.getNome());
		
		if(jogador.isSexo()) {
			playerSex.setImageResource(R.drawable.masc);
		} else {
			playerSex.setImageResource(R.drawable.femin);
		}
		
		imagem.setImageBitmap(jogador.getFoto());
	
		return vi;
	}

}
