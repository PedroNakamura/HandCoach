package DAO;

import android.content.ContentValues;
import android.content.Context;

public class EventoDAO extends DAO_Base<Evento> {
	
	public static final String NOME_TABELA = "evento";
	public static final String COLUNA_ID = "id_eve";
	public static final String COLUNA_IDCAT = "id_cat";
	public static final String COLUNA_IDJOG = "id_jog";
	public static final String COLUNA_IDEQ = "id_eq";
	public static final String COLUNA_TEMPOIN = "tempoin";
	public static final String COLUNA_TEMPOFI = "tempofi";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("+COLUNA_ID+" LONG PRIMARY KEY AUTO_INCREMENT, "+
	                                                                            COLUNA_IDCAT+" LONG, "+
	                                                                            COLUNA_IDJOG+" LONG, "+
	                                                                            COLUNA_IDEQ+" LONG, "+
	                                                                            COLUNA_TEMPOIN+" TIME, "+
	                                                                            COLUNA_TEMPOFI+" TIME )";
	
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
		cv.put(COLUNA_IDEQ, entidade.getId_eq());
		cv.put(COLUNA_TEMPOIN, entidade.getTempoIn());
		cv.put(COLUNA_TEMPOFI, entidade.getTempoFi());
		return cv;
	}

	@Override
	public Evento contentValuesParaEntidade(ContentValues contentValues) {
		Evento evento = new Evento();
		evento.setId(contentValues.getAsLong(COLUNA_ID));
		evento.setId_cat(contentValues.getAsLong(COLUNA_IDCAT));
		evento.setId_jog(contentValues.getAsLong(COLUNA_IDCAT));
		evento.setId_eq(contentValues.getAsLong(COLUNA_IDEQ));
		evento.setTempoIn(contentValues.getAsDouble(COLUNA_TEMPOIN));
		evento.setTempoFi(contentValues.getAsDouble(COLUNA_TEMPOFI));
		return evento;
	}
	
	

}
