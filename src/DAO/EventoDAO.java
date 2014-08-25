package DAO;

import android.content.ContentValues;
import android.content.Context;

public class EventoDAO extends DAO_Base<Evento> {
	
	public static final String NOME_TABELA = "evento";
	public static final String TABELA_CATEGORIA = "categoria";
	public static final String TABELA_JOGADOR = "jogador";
	public static final String TABELA_PTDA = "partida";
	public static final String COLUNA_ID = "id_eve";
	public static final String COLUNA_IDCAT = "id_cat";
	public static final String COLUNA_IDJOG = "id_jog";
	public static final String COLUNA_IDPTDA = "id_ptda";
	public static final String COLUNA_TEMPOIN = "tempoin";
	public static final String COLUNA_TEMPOFI = "tempofi";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ( "
	                                                         +COLUNA_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	                                                         +COLUNA_IDCAT+" INTEGER NOT NULL, "
	                                                         +"FOREIGN KEY("+COLUNA_IDCAT+") "+"REFERENCES "+TABELA_CATEGORIA+"("+COLUNA_IDCAT+"), "
	                                                         +COLUNA_IDJOG+" INTEGER NOT NULL, "
	                                                         +"FOREIGN KEY("+COLUNA_IDJOG+") "+"REFERENCES "+TABELA_JOGADOR+"("+COLUNA_IDJOG+"), "
	                                                         +COLUNA_IDPTDA+" INTEGER NOT NULL, "
	                                                         +"FOREIGN KEY("+COLUNA_IDPTDA+") "+"REFERENCES "+TABELA_PTDA+"("+COLUNA_IDPTDA+"), "
	                                                         +COLUNA_TEMPOIN+" TIME, "
	                                                         +COLUNA_TEMPOFI+" TIME )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;
	
	private static EventoDAO instancia;
	
	public EventoDAO(Context context) {
		super(context);
	}
	
	public static EventoDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new EventoDAO(context);
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
	public ContentValues entidadeParaContentValues(Evento entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_IDCAT, entidade.getId_cat());
		cv.put(COLUNA_IDJOG, entidade.getId_jog());
		cv.put(COLUNA_IDPTDA, entidade.getId_ptda());
		cv.put(COLUNA_TEMPOIN, entidade.getTempoIn());
		cv.put(COLUNA_TEMPOFI, entidade.getTempoFi());
		return cv;
	}

	@Override
	public Evento contentValuesParaEntidade(ContentValues contentValues) {
		Evento evento = new Evento();
		evento.setId(contentValues.getAsInteger(COLUNA_ID));
		evento.setId_cat(contentValues.getAsInteger(COLUNA_IDCAT));
		evento.setId_jog(contentValues.getAsInteger(COLUNA_IDCAT));
		evento.setId_ptda(contentValues.getAsInteger(COLUNA_IDPTDA));
		evento.setTempoIn(contentValues.getAsDouble(COLUNA_TEMPOIN));
		evento.setTempoFi(contentValues.getAsDouble(COLUNA_TEMPOFI));
		return evento;
	}
	
	

}
