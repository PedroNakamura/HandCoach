package Entidades;

import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.util.Log;

public class Jogador implements EntidadeInterface, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id_jog;
	private int id_eq;
	private String nome;
	private int sexo;
	private int pos;
	private Bitmap foto;
	private byte[] imagem;
	private double altura;
	private Date dt_nasc;
	private boolean marcado;
	
	private boolean titular;
	private boolean reserva;
	private boolean in2min;

	public Jogador() {
		this.dt_nasc = new Date();
	}
	
	public Jogador(int id_eq, String nome, int sexo, Bitmap foto, double altura, Date dt_nasc, int pos) {
		this.id_eq = id_eq;
		this.nome = nome;
		this.sexo = sexo;
		this.foto = foto;
		this.altura = altura;
		this.dt_nasc = dt_nasc;
		this.pos = pos;
		this.imagem = null;
		this.in2min = false;
	}
	
	//Getters and Setters ID do Jogador
	@Override
	public void setId(int id) {
		this.id_jog = id;
	}
	@Override
	public int getId() {
		return id_jog;
	}
	
	
	public boolean isTitular() {
		return titular;
	}
	public void setTitular(boolean titular) {
		this.titular = titular;
		if(titular){
			reserva = false;
		}	
	}
	
	public boolean is2min() {
		return in2min;
	}
	public void set2min(boolean tf) {
		this.in2min = tf;
	}

	public boolean isReserva() {
		return reserva;
	}
	public void setReserva(boolean reserva) {
		this.reserva = reserva;
	}

	//Getters and Setters id equipe
	public int getIdEq() {
		return id_eq;
	}
	public void setIdEq(int id) {
		this.id_eq = id;
	}

	//Getters and Setters Nome do jogador
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	//Getters and Setters Sexo
	public boolean isSexo() {
		if(this.sexo == 1) {
			return true;
		} else {
			return false;
		}
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	//Getters and Setters pos
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}

	//Getters and Setters Foto 
	public Bitmap getFoto() {
		if(this.foto != null) {
			return this.foto;
		} else {
			return null;
		}
	}
	public void setFoto(Bitmap foto) {
		this.foto = foto;
	} 

	//Getters and Setters Altura
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}

	//Getters and Setters Data Nascimento
	public Date getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	
	//getters and setters marcado
	public boolean isMarcado() {
		return marcado;
	}
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	//locuragens do byte array
	public Bitmap getOutput() {
		if(this.imagem != null) {
			Bitmap b = BitmapFactory.decodeByteArray(this.imagem, 0, this.imagem.length);  
			return b;
		} else {
			return null;
		}
	}
	public void setOutput(Bitmap img) {
		if(img != null) {
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			img.compress(Bitmap.CompressFormat.PNG, 100, bs);
			this.imagem = bs.toByteArray();
		}
	}
	
	//convers�o do Date para String
	public String dateToString() {
        //"05/09/2013 06:30:07"
		Date data = this.dt_nasc;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String dataFormatada = format.format(data);
        Log.i("DATA String", dataFormatada);
        return dataFormatada;
    }
    
	//convers�o do String para Date
    public Date stringToDate(String dataStr) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try {
                this.dt_nasc = (Date) format.parse(dataStr);
        } catch (ParseException e) {
                e.printStackTrace();
        }
        return dt_nasc;
    }
    
	public String toString() {
		return this.nome;
	}
	
	public boolean equals(Object o) {
		return this.id_jog == ((Jogador)o).getId();
	}
	
}
