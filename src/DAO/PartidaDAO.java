package DAO;

import android.content.ContentValues;
import android.content.Context;

public class PartidaDAO extends DAO_Base<Partida> {
	
	public static final String NOME_TABELA = "partida";
	public static final String COLUNA_ID = "id_ptida";
	public static final String COLUNA_IDEQUIPE = "id_eq";
	public static final String COLUNA_IDEVENTO = "id_eve";
	public static final String COLUNA_IDADV = "id_adv";
	public static final String COLUNA_LOCAL = "local";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("+COLUNA_ID+" LONG PRIMARY KEY AUTO_INCREMENT, "+
	                                                                            COLUNA_IDEQUIPE+" LONG, "+
	                                                                            COLUNA_IDEVENTO+" LONG, "+
	                                                                            COLUNA_IDADV+" LONG, "+
	                                                                            COLUNA_LOCAL+" TEXT )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;
	
	private static PartidaDAO instancia;
	
	public PartidaDAO(Context context) {
		super(context);
	}
	
	public static PartidaDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new PartidaDAO(context);
		}
		return instancia;
	}

	@Override
	public String getNomeColunaPrimaryKey() {
		return COLUNA_ID;
	}

	@Override
	public String getNomeTabela() {
		return NOME_TABELA;
	}

	@Override
	public ContentValues entidadeParaContentValues(Partida entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_IDEQUIPE, entidade.getId_eq());
		cv.put(COLUNA_IDEVENTO, entidade.getId_eve());
		cv.put(COLUNA_IDADV, entidade.getId_adv());
		cv.put(COLUNA_LOCAL, entidade.getLocal());
		return cv;
	}

	@Override
	public Partida contentValuesParaEntidade(ContentValues contentValues) {
		Partida partida = new Partida();
		partida.setId(contentValues.getAsLong(COLUNA_ID));
		partida.setId_eq(contentValues.getAsLong(COLUNA_IDEQUIPE));
		partida.setId_eve(contentValues.getAsLong(COLUNA_IDEVENTO));
		partida.setId_adv(contentValues.getAsLong(COLUNA_IDADV));
		partida.setLocal(contentValues.getAsString(COLUNA_LOCAL));
		return partida;
	}

}
