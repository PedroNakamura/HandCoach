package DAO;

public class Evento implements EntidadeInterface {

	private long id_eve;
	private long id_cat;
	private long id_jog;
	private long id_eq;
	private double tempoIn;
	private double tempoFi;
	
	//Getters and Setters ID Evento
	@Override
	public void setId(long id) {
		this.id_eve = id;
	}
	@Override
	public long getId() {
		return id_eve;
	}
	
	//Getters and Setters ID Categoria
	public long getId_cat() {
		return id_cat;
	}
	public void setId_cat(long id_cat) {
		this.id_cat = id_cat;
	}
	
	//Getters and Setters ID Jogador
	public long getId_jog() {
		return id_jog;
	}
	public void setId_jog(long id_jog) {
		this.id_jog = id_jog;
	}
	
	//Getters and Setters ID Equipe
	public long getId_eq() {
		return id_eq;
	}
	public void setId_eq(long id_eq) {
		this.id_eq = id_eq;
	}
	
	//Getters and Setters Tempo Início
	public double getTempoIn() {
		return tempoIn;
	}
	public void setTempoIn(double tempoIn) {
		this.tempoIn = tempoIn;
	}
	
	//Getters and Setters Tempo Fim
	public double getTempoFi() {
		return tempoFi;
	}
	public void setTempoFi(double tempoFm) {
		this.tempoFi = tempoFm;
	}
	
	
	

}
