package DAO;

public class Equipe implements EntidadeInterface {

	private long id_eq;
	private long id_jog;
	private String nome;
	private boolean adv;
	
	
	//Getters and Setters ID_Equipe
	@Override
	public void setId(long id) {
		this.id_eq = id;
	}
	@Override
	public long getId() {
		return id_eq;
	}
	
	//Getters and Setters ID_Jogadores
	public long getId_jog() {
		return id_jog;
	}
	public void setId_jog(long id_jog) {
		this.id_jog = id_jog;
	}
	
	//Getters and Setters Nome da equipe
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Getters and Setters é adversário?
	public boolean isAdv() {
		return adv;
	}
	public void setAdv(boolean adv) {
		this.adv = adv;
	}
	
	

}
