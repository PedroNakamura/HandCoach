package DAO;

public class Partida implements EntidadeInterface {

	private long id_ptda;
	private long id_eq;
	private long id_eve;
	private long id_adv;
	private String local;
	
	//Getters and Setters ID da Partida
	@Override
	public void setId(long id) {
		this.id_ptda = id;
	}
	@Override
	public long getId() {
		return id_ptda;
	}
	
	//Getters and Setters ID Equipes
	public long getId_eq() {
		return id_eq;
	}
	public void setId_eq(long id_eq) {
		this.id_eq = id_eq;
	}
	
	//Getters and Setters ID Eventos
	public long getId_eve() {
		return id_eve;
	}
	public void setId_eve(long id_eve) {
		this.id_eve = id_eve;
	}
	
	//Getters and Setters ID Adversários
	public long getId_adv() {
		return id_adv;
	}
	public void setId_adv(long id_adv) {
		this.id_adv = id_adv;
	}
	
	//Getters and Setters Local? 
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	

}
