package DAO;

import android.content.ContentValues;
import android.content.Context;

public class JogadorDAO extends DAO_Base<Jogador>{
	
	public static final String NOME_TABELA = "jogador";
	public static final String COLUNA_ID = "id_jog";
	public static final String COLUNA_NOME = "nome";
	public static final String COLUNA_SEXO = "sexo";
	public static final String COLUNA_FOTO = "foto";
	public static final String COLUNA_ALTURA = "altura";
	public static final String COLUNA_DT_NASC = "dt_nasc";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ("+COLUNA_ID+"LONG PRIMARY KEY AUTO_INCREMENT, "+
	                                                                            COLUNA_NOME+"TEXT, "+
	                                                                            COLUNA_SEXO+"BOOLEAN, "+
	                                                                            COLUNA_FOTO+"BLOB, "+
	                                                                            COLUNA_ALTURA+"TEXT, "+
	                                                                            COLUNA_DT_NASC+"DATE )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+NOME_TABELA;
	
	private static JogadorDAO instancia;

	public JogadorDAO(Context context) {
		super(context);
	}
	
	public static JogadorDAO getInstancia(Context context) {
		if(instancia == null) {
			instancia = new JogadorDAO(context);
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
	public ContentValues entidadeParaContentValues(Jogador entidade) {
		ContentValues cv = new ContentValues();
		if(entidade.getId() > 0) {
			cv.put(COLUNA_ID, entidade.getId());
		}
		cv.put(COLUNA_NOME, entidade.getNome());
		cv.put(COLUNA_SEXO, entidade.isSexo());
		// WUT? - pede para retornar string! -> cv.put(COLUNA_FOTO, entidade.getFoto());
		cv.put(COLUNA_ALTURA, entidade.getAltura());
		// pede para retornar string! -> cv.put(COLUNA_DT_NASC, entidade.getDt_nasc());
		return cv;
	}

	@Override
	public Jogador contentValuesParaEntidade(ContentValues contentValues) {
		Jogador jogador = new Jogador();
		jogador.setId(contentValues.getAsLong(COLUNA_ID));
		jogador.setNome(contentValues.getAsString(COLUNA_NOME));
		jogador.setSexo(contentValues.getAsBoolean(COLUNA_SEXO));
		// getAsBlob???? jogador.setFoto(contentValues.getAs??(COLUNA_FOTO));
		jogador.setAltura(contentValues.getAsString(COLUNA_ALTURA));
		// getAsDate???? jogador.setDt_nasc(contentValues.getAs??(COLUNA_DT_NASC));
		return jogador;
	}

}
