package DAO;

public class Equipe implements EntidadeInterface {

	private int id_eq;
	private String nome;
	
	public Equipe() {
	}
	
	public Equipe(int id, String nome) {
		this.id_eq = id;
		this.nome = nome;
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
	
	public String toString() {
		return this.nome;
	}
	
	
	@Override
	public boolean equals(Object o) {
		return this.id_eq == ((Equipe)o).getId();
	}

}
