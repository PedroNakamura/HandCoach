package DAO;

import java.text.ParseException;

import android.content.ContentValues;
import android.content.Context;

public class PartidaDAO extends DAO_Base<Partida> {
	
	public static final String NOME_TABELA = "partida";
	public static final String TABELA_EQUIPE = "equipe";
	public static final String TABELA_EQUIPEADV = "equipeadv";
	public static final String COLUNA_ID = "id_ptida";
	public static final String COLUNA_IDEQUIPE = "id_eq";
	public static final String COLUNA_IDEQADV = "id_eqadv";
	public static final String COLUNA_LOCAL = "local";
	public static final String COLUNA_DTPTDA = "dt_ptda";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("
	                                                         +COLUNA_ID+" INT NOT NULL AUTOINCREMENT PRIMARY KEY, "
	                                                         +COLUNA_IDEQUIPE+" INT NOT NULL, "
	                                                         +"CONSTRAINT id_ptda_eq FOREIGN KEY ("+COLUNA_IDEQUIPE+") REFERENCES "+TABELA_EQUIPE+" ("+COLUNA_IDEQUIPE+"), "
	                                                         +COLUNA_IDEQADV+" INT NOT NULL, "
	                                                         +"CONSTRAINT id_ptda_eqadv FOREIGN KEY ("+COLUNA_IDEQADV+") REFERENCES "+TABELA_EQUIPEADV+" ("+COLUNA_IDEQADV+"), "
	                                                         +COLUNA_DTPTDA+" DATE, "
	                                                         +COLUNA_LOCAL+" TEXT )";
	
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
		cv.put(COLUNA_IDEQADV, entidade.getId_eqadv());
		cv.put(COLUNA_LOCAL, entidade.getLocal());
		cv.put(COLUNA_DTPTDA, entidade.dateToString());
		return cv;
	}

	@Override
	public Partida contentValuesParaEntidade(ContentValues contentValues) {
		Partida partida = new Partida();
		partida.setId(contentValues.getAsInteger(COLUNA_ID));
		partida.setId_eq(contentValues.getAsInteger(COLUNA_IDEQUIPE));
		partida.setId_eqadv(contentValues.getAsInteger(COLUNA_IDEQADV));
		partida.setLocal(contentValues.getAsString(COLUNA_LOCAL));
		
		String data = contentValues.getAsString(COLUNA_DTPTDA);
		try {
			partida.setData(partida.stringToDate(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return partida;
	}

}
