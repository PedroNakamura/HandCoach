package DAO;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class JogadorDAO extends DAO_Base<Jogador> {
	
	public static final String NOME_TABELA = "jogador";
	public static final String FOREIGN_TABELA = "equipe";
	public static final String COLUNA_ID = "id_jog";
	public static final String COLUNA_IDEQ = "id_eq";
	public static final String COLUNA_NOME = "nome";
	public static final String COLUNA_SEXO = "sexo";
	public static final String COLUNA_FOTO = "foto";
	public static final String COLUNA_POS = "pos";
	public static final String COLUNA_ALTURA = "altura";
	public static final String COLUNA_DT_NASC = "dt_nasc";
	
	public static final String CREATE_TABLE = "CREATE TABLE "+NOME_TABELA+" ( "
	                                                         +COLUNA_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	                                                         +COLUNA_IDEQ+" INTEGER NOT NULL, "
	                                                         +COLUNA_NOME+" TEXT, "
	                                                         +COLUNA_SEXO+" BOOLEAN, "
	                                                         +COLUNA_FOTO+" BLOB, "
	                                                         +COLUNA_ALTURA+" TEXT, "
	                                                         +COLUNA_POS+" TEXT, "
	                                                         +COLUNA_DT_NASC+" DATE," 
	                                                         +"FOREIGN KEY("+COLUNA_IDEQ+") "+"REFERENCES "+FOREIGN_TABELA+"("+COLUNA_IDEQ+")"
	                                                         +" )";
	// +"CONSTRAINT id_jog_eq FOREIGN KEY ("+COLUNA_IDEQ+") REFERENCES "+FOREIGN_TABELA+" ("+COLUNA_IDEQ+"), "
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
		cv.put(COLUNA_ALTURA, entidade.getAltura());
		cv.put(COLUNA_IDEQ, entidade.getIdEq());
		
		if(entidade.getFoto() != null) {
		    // Bitmap -> byte[]
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			entidade.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] byteArray = stream.toByteArray();
			cv.put(COLUNA_FOTO, byteArray);
		} else {
			cv.putNull(COLUNA_FOTO);
		}

		cv.put(COLUNA_DT_NASC, entidade.dateToString());
		cv.put(COLUNA_POS, entidade.getPos());
		
		return cv;
	}

	@Override
	public Jogador contentValuesParaEntidade(ContentValues contentValues) {
		Jogador jogador = new Jogador();
		jogador.setId(contentValues.getAsInteger(COLUNA_ID));
		jogador.setNome(contentValues.getAsString(COLUNA_NOME));
		jogador.setSexo(contentValues.getAsInteger(COLUNA_SEXO));
		
		Log.i("DEBUG: ", jogador.isSexo()+"");
		
		//byte[] -> Bitmap
		if(contentValues.getAsByteArray(COLUNA_FOTO) != null) {
			byte[] byteArray = contentValues.getAsByteArray(COLUNA_FOTO);
			Bitmap foto = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
			jogador.setFoto(foto);
		} else {
			jogador.setFoto(null);
		}
        //
		
		jogador.setAltura(contentValues.getAsString(COLUNA_ALTURA));
		jogador.setIdEq(contentValues.getAsInteger(COLUNA_IDEQ));
		jogador.setPos(contentValues.getAsString(COLUNA_POS));
		
		String data = contentValues.getAsString(COLUNA_DT_NASC);
		try {
			jogador.setDt_nasc(jogador.stringToDate(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return jogador;
	}

}
