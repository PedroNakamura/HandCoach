package DAO;

public class EquipeAdv implements EntidadeInterface {

	private int id_eqadv;
	private String nome;
	
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

}
