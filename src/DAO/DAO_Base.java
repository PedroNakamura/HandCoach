package DAO;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

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
	public void Editar(T entidade) {
		String[] valores = {String.valueOf(entidade.getId())};
		ContentValues cv = entidadeParaContentValues(entidade);
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
	
	//
	public T buscarPorID(long id) {
		String query = "SELECT * FROM "+getNomeTabela()+" WHERE "+getNomeColunaPrimaryKey()+" = "+id;
		List<T> resultado = getQuery(query);
		if(resultado.size() > 0) {
			return resultado.get(0);
		} else {
			return null;
		}
	}

}
