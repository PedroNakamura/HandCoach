package DAO;

public class EquipeAdv implements EntidadeInterface {

	private int id_eqadv;
	private String nome;
	
	public EquipeAdv(String nome) {
		this.nome = nome;
	}
	
	public EquipeAdv(int id, String nome) {
		this.id_eqadv = id;
		this.nome = nome;
	}
	
	public EquipeAdv() {
		
	}
	
	@Override
	public void setId(int id) {
		this.id_eqadv = id;
	}

	@Override
	public int getId() {
		return id_eqadv;
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

}
