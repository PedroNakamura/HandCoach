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
		db.execSQL(JogadorDAO.CREATE_TABLE);
		db.execSQL(CategoriaDAO.CREATE_TABLE);
		db.execSQL(EquipeDAO.CREATE_TABLE);
		db.execSQL(EventoDAO.CREATE_TABLE);
		db.execSQL(PartidaDAO.CREATE_TABLE);
		
		ContentValues cv = new ContentValues();
		//Bota nos contentValues
		cv.put("Gol", "AR_gol");
		cv.put("Defesa", "AR_defesa");
		cv.put("Fora", "AR_fora");
		cv.put("GK Defesa", "AR_GK");
		cv.put("Passe certo", "PSS_certo");
		cv.put("Passe errado", "PSS_errado");
		cv.put("Recepção certa", "RCP_certa");
		cv.put("Recepção errada", "RCP_errada");
		cv.put("Falta técnica", "FT_tecnica");
		cv.put("Falta de ataque", "FT_ataque");
		cv.put("Falta de defesa", "FT_defesa");
		cv.put("7 metros", "FT_7m");
		cv.put("Cartão amarelo", "CT_amarelo");
		cv.put("2 minutos", "2min");
		cv.put("Sofrer falta no ataque", "SFT_ataque");
		cv.put("Sofrer falta na defesa", "SFT_defesa");
		cv.put("Posse de bola equipe", "PB_equipe");
		cv.put("Posse de bola adversário", "PB_adv");
		
		//Cria no Banco
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Gol"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Fora"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("GK Defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Passe certo"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Passe errado"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Recepção certa"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Recepção errada"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Falta técnica"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Falta de ataque"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Falta de defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("7 metros"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Cartão amarelo"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("2 minutos"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Sofrer falta no ataque"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Sofrer falta na defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Posse de bola equipe"));
		db.insert(CategoriaDAO.NOME_TABELA, CategoriaDAO.COLUNA_DESCR, (ContentValues) cv.get("Posse de bola adversário"));
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
