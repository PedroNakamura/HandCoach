package util;

import java.util.List;
import Entidades.Partida;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.handcoach.R;

public class LazyAdapterPartida extends BaseAdapter {

	private Activity act;
	private List<Partida> listaPartidas;
	private static LayoutInflater inflater = null;
	
	public LazyAdapterPartida(Activity a, List<Partida> d) {
		act = a;
		listaPartidas = d;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void remove(Partida equipe) {
		listaPartidas.remove(equipe);
	}
	
	public void add(Partida equipe) {
		listaPartidas.add(equipe);
	}
	
	@Override
	public int getCount() {
		return listaPartidas.size();
	}

	@Override
	public Object getItem(int position) {
		return this.listaPartidas.get(position);
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
		Partida equipe = listaPartidas.get(position);
		teamName.setText(equipe.toString());
		return vi;
	}

}
