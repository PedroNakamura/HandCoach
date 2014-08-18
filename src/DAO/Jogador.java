package DAO;

//import java.io.File;
import java.sql.Date;

public class Jogador implements EntidadeInterface {
	
	private long id_jog;
	private String nome;
	private boolean sexo;
	//private File foto;
	private String altura;
	private Date dt_nasc;
	
	
	
	//Getters and Setters ID do Jogador
	@Override
	public void setId(long id) {
		this.id_jog = id;
	}
	@Override
	public long getId() {
		return id_jog;
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
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	//Getters and Setters Foto 
	/*
	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	} */

	//Getters and Setters Altura
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}

	//Getters and Setters Data Nascimento
	public Date getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	

}
