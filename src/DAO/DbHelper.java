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
		//adicionar outros DAOs
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(JogadorDAO.DROP_TABLE);
		//adicionar outros DAOs
		onCreate(db);
	}

}
