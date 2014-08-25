package DAO;

import android.content.ContentValues;
import android.content.Context;

public class EquipeAdvDAO extends DAO_Base<EquipeAdv> {
	
	public static final String NOME_TABELA = "equipeadv";
	public static final String COLUNA_ID = "id_eqadv";
	public static final String COLUNA_NOME = "nome";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ( "
	                                                         +COLUNA_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	                                                         +COLUNA_NOME+" TEXT )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;
	
	private static EquipeAdvDAO instancia;
	
	public static EquipeAdvDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new EquipeAdvDAO(context);
		}
		return instancia;
	}

	public EquipeAdvDAO(Context context) {
		super(context);
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
	public ContentValues entidadeParaContentValues(EquipeAdv entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_NOME, entidade.getNome());
		return cv;
	}

	@Override
	public EquipeAdv contentValuesParaEntidade(ContentValues contentValues) {
		EquipeAdv equipe = new EquipeAdv();
		equipe.setId(contentValues.getAsInteger(COLUNA_ID));
		equipe.setNome(contentValues.getAsString(COLUNA_NOME));
		return equipe;
	}

}
