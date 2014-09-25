package DAO;

import Entidades.Equipe;
import android.content.ContentValues;
import android.content.Context;

public class EquipeDAO extends DAO_Base<Equipe> {

	public static final String NOME_TABELA = "equipe";
	public static final String COLUNA_ID = "id_eq";
	public static final String COLUNA_NOME = "nome";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("
	                                                         +COLUNA_ID+"  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	                                                         +COLUNA_NOME+" TEXT )";
	
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
		return cv;
	}

	@Override
	public Equipe contentValuesParaEntidade(ContentValues contentValues) {
		Equipe equipe = new Equipe();
		equipe.setId(contentValues.getAsInteger(COLUNA_ID));
		equipe.setNome(contentValues.getAsString(COLUNA_NOME));
		return equipe;
	}

}
