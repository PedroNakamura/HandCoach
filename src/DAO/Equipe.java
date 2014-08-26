package DAO;

public class Equipe implements EntidadeInterface {

	private int id_eq;
	private String nome;
	
	public Equipe() {
	}
	
	public Equipe(String nome) {
		this.nome = nome;
	}
	
	//Getters and Setters ID_Equipe
	@Override
	public void setId(int id) {
		this.id_eq = id;
	}
	@Override
	public int getId() {
		return id_eq;
	}
	
	//Getters and Setters Nome da equipe
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
