package util;

import java.util.List;
import com.example.handcoach.R;

import Entidades.EquipeAdv;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapterEqAdv extends BaseAdapter {

	private Activity act;
	private List<EquipeAdv> listaEquipes;
	private static LayoutInflater inflater = null;
	
	public LazyAdapterEqAdv(Activity a, List<EquipeAdv> d) {
		act = a;
		listaEquipes = d;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void remove(EquipeAdv equipe) {
		listaEquipes.remove(equipe);
	}
	
	public void add(EquipeAdv equipe) {
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
		EquipeAdv equipe = listaEquipes.get(position);
		teamName.setText(equipe.getNome());
		return vi;
	}

}
