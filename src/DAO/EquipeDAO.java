package DAO;

import android.content.ContentValues;
import android.content.Context;

public class EquipeDAO extends DAO_Base<Equipe> {
	


	public static final String NOME_TABELA = "jogador";
	public static final String COLUNA_ID = "id";
	public static final String COLUNA_IDJOG = "id_jog";
	public static final String COLUNA_NOME = "nome";
	public static final String COLUNA_ADV = "adv";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("+COLUNA_ID+" LONG PRIMARY KEY AUTO_INCREMENT, "+
	                                                                            COLUNA_NOME+" TEXT, "+
	                                                                            COLUNA_ADV+" BOOLEAN, "+
	                                                                            COLUNA_IDJOG+" LONG )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;
	
	private static EquipeDAO instancia;
	
	public EquipeDAO(Context context) {
		super(context);
	}
	
	public static EquipeDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new EquipeDAO(context);
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
	public ContentValues entidadeParaContentValues(Equipe entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_NOME, entidade.getNome());
		cv.put(COLUNA_IDJOG, entidade.getId_jog());
		cv.put(COLUNA_ADV, entidade.isAdv());
		return cv;
	}

	@Override
	public Equipe contentValuesParaEntidade(ContentValues contentValues) {
		Equipe equipe = new Equipe();
		equipe.setId(contentValues.getAsLong(COLUNA_ID));
		equipe.setId_jog(contentValues.getAsLong(COLUNA_IDJOG));
		equipe.setNome(contentValues.getAsString(COLUNA_NOME));
		equipe.setAdv(contentValues.getAsBoolean(COLUNA_ADV));
		return equipe;
	}

}
