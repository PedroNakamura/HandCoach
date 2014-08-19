package DAO;

import android.content.ContentValues;
import android.content.Context;

public class CategoriaDAO extends DAO_Base<Categoria> {
	
	public static final String NOME_TABELA = "categoria";
	public static final String COLUNA_ID = "id_cat";
	public static final String COLUNA_DESCR = "descr";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("
	                                                         +COLUNA_ID+" INT NOT NULL AUTOINCREMENT PRIMARY KEY, "
	                                                         +COLUNA_DESCR+" TEXT )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;
	
	private static CategoriaDAO instancia;
	
	public CategoriaDAO(Context context) {
		super(context);
	}
	
	public static CategoriaDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new CategoriaDAO(context);
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
	public ContentValues entidadeParaContentValues(Categoria entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_DESCR, entidade.getDescr());
		return cv;
	}

	@Override
	public Categoria contentValuesParaEntidade(ContentValues contentValues) {
		Categoria categoria = new Categoria();
		categoria.setId(contentValues.getAsInteger(COLUNA_ID));
		categoria.setDescr(contentValues.getAsString(COLUNA_DESCR));
		return categoria;
	}

}
