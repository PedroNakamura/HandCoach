package DAO;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

public abstract class DAO_Base <T extends EntidadeInterface>{
	
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
	
	//
	public List<T> buscarTodos() {
		String query = "SELECT * FROM "+getNomeTabela();
		return getQuery(query);
	}
	
	public List<T> buscarNome() {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE nome = %";
		return getQuery(query);
	}
	
	public List<T> buscarDaEquipe(int id_eq) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE id_eq = "+id_eq;
		return getQuery(query);
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
