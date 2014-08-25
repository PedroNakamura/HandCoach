package DAO;

import android.content.ContentValues;
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
		db.execSQL(CategoriaDAO.CREATE_TABLE);
		db.execSQL(EquipeDAO.CREATE_TABLE);
		db.execSQL(EquipeAdvDAO.CREATE_TABLE);
		db.execSQL(JogadorDAO.CREATE_TABLE);
		db.execSQL(PartidaDAO.CREATE_TABLE);
		db.execSQL(EventoDAO.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(JogadorDAO.DROP_TABLE);
		db.execSQL(CategoriaDAO.DROP_TABLE);
		db.execSQL(EquipeDAO.DROP_TABLE);
		db.execSQL(EquipeAdvDAO.DROP_TABLE);
		db.execSQL(EventoDAO.DROP_TABLE);
		db.execSQL(PartidaDAO.DROP_TABLE);
		onCreate(db);
	}
	
	public void populaCategoria(SQLiteDatabase db) {
		ContentValues cv = new ContentValues();
		//Bota nos contentValues
		cv.put("descr", "AR_gol");
		cv.put("descr", "AR_defesa");
		cv.put("descr", "AR_fora");
		cv.put("descr", "AR_GK");
		cv.put("descr", "PSS_certo");
		cv.put("descr", "PSS_errado");
		cv.put("descr", "RCP_certa");
		cv.put("descr", "RCP_errada");
		cv.put("descr", "FT_tecnica");
		cv.put("descr", "FT_ataque");
		cv.put("descr", "FT_defesa");
		cv.put("descr", "FT_7m");
		cv.put("descr", "CT_amarelo");
		cv.put("descr", "2min");
		cv.put("descr", "SFT_ataque");
		cv.put("descr", "SFT_defesa");
		cv.put("descr", "PB_equipe");
		cv.put("descr", "PB_adv");
		
		//Cria no Banco

	}

}
