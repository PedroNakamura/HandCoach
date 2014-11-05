package DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Entidades.EntidadeInterface;
import Entidades.Partida;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

public abstract class DAO_Base <T extends EntidadeInterface> {
	
	protected SQLiteDatabase db;
	
	public DAO_Base(Context context) {
		DbHelper DBHelp = DbHelper.getInstancia(context);
		db = DBHelp.getWritableDatabase();
	}
	
	public abstract String getNomeColunaPrimaryKey();
	public abstract String getNomeTabela();
	public abstract ContentValues entidadeParaContentValues(T entidade);
	public abstract T contentValuesParaEntidade(ContentValues contentValues);
	
	//
	public void Inserir(T entidade) {
		ContentValues cv = entidadeParaContentValues(entidade);
		db.insert(getNomeTabela(), null, cv);
	}
	
	//
	public void Deletar(T entidade) {
		String[] valores = {String.valueOf(entidade.getId())};
		db.delete(getNomeTabela(), getNomeColunaPrimaryKey() + " = ?", valores);
	}
	
	public void deletarUltimaCat() {
		Deletar(buscarMaiorID());
	}
	
	//
	public void Editar(T entidade, T entidadeAtualizada) {
		String[] valores = {String.valueOf(entidade.getId())};
		ContentValues cv = entidadeParaContentValues(entidadeAtualizada);
		db.update(getNomeTabela(), cv, getNomeColunaPrimaryKey() + " = ?", valores);
	}
	
	//
	public List<T> getQuery(String query) {
		Cursor c = db.rawQuery(query, null);
		List<T> listaRetorno = new ArrayList<T>();
		if(c.moveToFirst()) {
			do {
				ContentValues cv = new ContentValues();
				DatabaseUtils.cursorRowToContentValues(c, cv);
				listaRetorno.add(contentValuesParaEntidade(cv));
			} while(c.moveToNext());
		}
		return listaRetorno;
	}
	
	public int retornaContadorQuery(String query) {
		List<T> lista = getQuery(query);
		int contador = 0;
		if(lista != null) {
			for(int x = 0; x < lista.size(); x++) {
				contador++;
			}
		}
		return contador;
	}
	
	public int retornaJogMaiorIncidencia(int id) {
		int valor = 0;
		Cursor c = db.rawQuery("SELECT id_jog FROM evento WHERE id_cat = "+id+" group by id_jog order by COUNT(id_eve) DESC LIMIT 1;", null);
		if(c.moveToFirst()) {
			valor = c.getInt(c.getColumnIndex("id_jog"));
		}
		return valor;
	}
	
	public int simpleGetQuery(String query) {
		int valor = 1;
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()) {
			valor = c.getInt(c.getColumnIndex("id_ptida"));
		}
		return valor;
	}
	
	public int retornaGolsEq(int id_eq) {
		Cursor c = db.rawQuery("SELECT SUM(gol_eq) AS gols FROM "+getNomeTabela()+" WHERE id_eq="+id_eq, null);
		int contador = 0;
		if(c.moveToFirst()) {
			contador = c.getInt(c.getColumnIndex("gols"));
		}
		return contador;
	}
	
	public int retornaGolsEqAdv(int id_eq) {
		Cursor c = db.rawQuery("SELECT SUM(gol_adv) AS gols FROM "+getNomeTabela()+" WHERE id_eq="+id_eq, null);
		int contador = 0;
		if(c.moveToFirst()) {
			contador = c.getInt(c.getColumnIndex("gols"));
		}
		return contador;
	}
	
	public List<Integer> retornaQuatroUltimas(int id_eq) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE id_eq="+id_eq+" ORDER BY "+getNomeColunaPrimaryKey()+" DESC LIMIT 4";
		@SuppressWarnings("unchecked")
		List<Partida> listaObj = ((List<Partida>) getQuery(query));
		List<Integer> lista = new ArrayList<Integer>();
		for(int x = 0; x < listaObj.size(); x++) {
			lista.add(listaObj.get(x).getId());
		}
		return lista;
	}
	
	//
	public List<T> buscarTodos() {
		String query = "SELECT * FROM "+getNomeTabela();
		return getQuery(query);
	}
	
	public List<T> buscarUltimosTres(int id_eq) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE id_eq="+id_eq+" ORDER BY "+getNomeColunaPrimaryKey()+" DESC LIMIT 3";
		return getQuery(query);
	}
	
	public List<T> buscarNome(String nome) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE nome = '"+nome+"'";
		return getQuery(query);
	}
	
	public List<T> buscarDaEquipe(int id_eq) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE id_eq = "+id_eq;
		return getQuery(query);
	}
	
	public T buscarMaiorID() {
		String query = "SELECT * FROM "+getNomeTabela()+" ORDER BY "+getNomeColunaPrimaryKey()+" DESC LIMIT 1";
		return getQuery(query).get(0);
	}
	
	//
	public T buscarPorID(int id) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE "+getNomeColunaPrimaryKey()+" = "+id;
		List<T> resultado = getQuery(query);
		if(resultado.size() > 0) {
			return resultado.get(0);
		} else {
			return null;
		}
	}
	
	//conversão do String para Date
    public Date stringToDate(String dataStr) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date dtnasc = null;
        try {
                dtnasc = (Date) format.parse(dataStr);
        } catch (ParseException e) {
                e.printStackTrace();
        }
        return dtnasc;
    }

}
