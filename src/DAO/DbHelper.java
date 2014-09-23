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
		populaCategoria(db);
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
		//Bota nos contentValues
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("AR_gol"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("AR_defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("AR_fora"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("AR_GK"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("PSS_certo"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("PSS_errado"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("RCP_certa"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("RCP_errada"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("RCP_rbdabola"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("FT_tecnica"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("FT_defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("FT_ataque"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("FT_7m"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("CT_amarelo"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("2min"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("SFT_ataque"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("SFT_defesa"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("PB_equipe"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("PB_adv"));
		db.insert(CategoriaDAO.NOME_TABELA, null, popCat("RBT_rebote"));
	}

	private ContentValues popCat(String s) {
		ContentValues c = new ContentValues();
		c.put("descr", s);
		return c;
		
	}
	
}
