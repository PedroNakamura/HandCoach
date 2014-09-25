package DAO;

import Entidades.EventoAdv;
import android.content.ContentValues;
import android.content.Context;

public class EventoAdvDAO extends DAO_Base<EventoAdv> {

	public static final String NOME_TABELA = "eventoadv";
	public static final String TABELA_CATEGORIA = "categoria";
	public static final String TABELA_EQUIPEADV = "equipeadv";
	public static final String TABELA_PTDA = "partida";
	public static final String COLUNA_ID = "id_eveadv";
	public static final String COLUNA_IDCAT = "id_cat";
	public static final String COLUNA_IDEQADV = "id_eqadv";
	public static final String COLUNA_IDPTDA = "id_ptda";
	public static final String COLUNA_TEMPOIN = "tempoin";
	public static final String COLUNA_TEMPOFI = "tempofi";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ( "
            +COLUNA_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +COLUNA_IDCAT+" INTEGER NOT NULL, "
            +COLUNA_IDEQADV+" INTEGER NOT NULL, "
            +COLUNA_IDPTDA+" INTEGER NOT NULL, "
            +COLUNA_TEMPOIN+" TIME, "
            +COLUNA_TEMPOFI+" TIME, " 
            +"FOREIGN KEY("+COLUNA_IDCAT+") "+"REFERENCES "+TABELA_CATEGORIA+"("+COLUNA_IDCAT+"), "
            +"FOREIGN KEY("+COLUNA_IDEQADV+") "+"REFERENCES "+TABELA_EQUIPEADV+"("+COLUNA_IDEQADV+"), "
            +"FOREIGN KEY("+COLUNA_IDPTDA+") "+"REFERENCES "+TABELA_PTDA+"("+COLUNA_IDPTDA+")"
            +" )";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;

    private static EventoAdvDAO instancia;
    
    public EventoAdvDAO(Context context) {
		super(context);
	}
    
	public static EventoAdvDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new EventoAdvDAO(context);
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
	public ContentValues entidadeParaContentValues(EventoAdv entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_IDCAT, entidade.getId_cat());
		cv.put(COLUNA_IDEQADV, entidade.getId_eqadv());
		cv.put(COLUNA_IDPTDA, entidade.getId_ptda());
		cv.put(COLUNA_TEMPOIN, entidade.getTempoIn());
		cv.put(COLUNA_TEMPOFI, entidade.getTempoFi());
		return cv;
	}

	@Override
	public EventoAdv contentValuesParaEntidade(ContentValues contentValues) {
		EventoAdv evento = new EventoAdv();
		evento.setId(contentValues.getAsInteger(COLUNA_ID));
		evento.setId_cat(contentValues.getAsInteger(COLUNA_IDCAT));
		evento.setId_eqadv(contentValues.getAsInteger(COLUNA_IDEQADV));
		evento.setId_ptda(contentValues.getAsInteger(COLUNA_IDPTDA));
		evento.setTempoIn(contentValues.getAsDouble(COLUNA_TEMPOIN));
		evento.setTempoFi(contentValues.getAsDouble(COLUNA_TEMPOFI));
		return evento;
	}

}
