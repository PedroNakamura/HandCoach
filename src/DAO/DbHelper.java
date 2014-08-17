package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
	
	private static final String NOME_BASE = "HandCoach_DB";
	private static final int VERSAO_BASE = 10;
	private static DbHelper instancia;

	private DbHelper(Context context) {
		super(context, NOME_BASE, null, VERSAO_BASE);
	}
	
	public static DbHelper getInstancia(Context context) {
		if(instancia == null) {
			instancia = new DbHelper(context);
		}
		return instancia;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(JogadorDAO.CREATE_TABLE);
		db.execSQL(CategoriaDAO.CREATE_TABLE);
		db.execSQL(EquipeDAO.CREATE_TABLE);
		db.execSQL(EventoDAO.CREATE_TABLE);
		db.execSQL(PartidaDAO.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(JogadorDAO.DROP_TABLE);
		db.execSQL(CategoriaDAO.DROP_TABLE);
		db.execSQL(EquipeDAO.DROP_TABLE);
		db.execSQL(EventoDAO.DROP_TABLE);
		db.execSQL(PartidaDAO.DROP_TABLE);
		onCreate(db);
	}

}
