package util;

import java.util.ArrayList;

//http://www.vogella.com/tutorials/AndroidListView/article.html
//http://www.guj.com.br/4372-listview-multiselecionavel-com-checkbox-selecionar-varios-itens-de-uma-listview

import java.util.List;
import Entidades.Jogador;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.handcoach.R;

public class JogadorListaAdapter extends BaseAdapter {
	
	private Activity act;
	private List<Jogador> listaJogador;
	private List<Integer> idSelecionados = new ArrayList<Integer>();
	private static LayoutInflater inflater = null;
	public Bitmap Image;
	
	public JogadorListaAdapter(Activity a, List<Jogador> d) {
		act = a;
		listaJogador = d;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void remove(Jogador jogador) {
		listaJogador.remove(jogador);
	}
	
	public List<Integer> getSelecionados() {
		return idSelecionados;
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

	@SuppressLint("UseValueOf")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView == null) {
			vi = inflater.inflate(R.layout.jogadorlistaadapter, null);
		}
		TextView playerName = (TextView) vi.findViewById(R.id.JogadorNome_listView_adp);
		ImageView playerSex = (ImageView) vi.findViewById(R.id.JogadorSexo_listView_adp);
		ImageView imagem = (ImageView) vi.findViewById(R.id.Jogador_list_image_adp);
		CheckBox jogSelected = (CheckBox) vi.findViewById(R.id.chec_selecionaJogador);
		
		Jogador jogador = listaJogador.get(position);
		
		playerName.setText(jogador.getNome());
		
		if(jogador.isSexo()) {
			playerSex.setImageResource(R.drawable.masc);
		} else {
			playerSex.setImageResource(R.drawable.femin);
		}
		
		jogSelected.setChecked(jogador.isMarcado());
		jogSelected.setTag(jogador);
		
		imagem.setImageBitmap(jogador.getFoto());
		
		jogSelected.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View v) {
				CheckBox check = (CheckBox) v;
				Jogador j = (Jogador) v.getTag();
				j.setMarcado(check.isChecked());
				if(check.isChecked()) {
					if(!idSelecionados.contains(new Integer(j.getId()))) {
						Log.i("DEBUG!!!", "======== "+"I:"+j.getId()+"T:"+idSelecionados.size()+" ========");
						idSelecionados.add(new Integer(j.getId()));
					}
				} else {				
					if(idSelecionados.contains(new Integer(j.getId()))) {
						Log.i("DEBUG!!!", "======== "+"I:"+j.getId()+"T:"+idSelecionados.size()+" ========");
						idSelecionados.remove(new Integer(j.getId()));
					}
				}				
			}
		});
	
		return vi;
	}

}
