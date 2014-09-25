package Entidades;

public class Evento implements EntidadeInterface {

	private int id_eve;
	private int id_cat;
	private int id_jog;
	private int id_ptda;
	private double tempoIn;
	private double tempoFi;
	
	//construtores
	public Evento() { }
	
	public Evento(int id_cat, int id_jog, int id_ptda, double tempoIn, double tempoFi) {
		this.id_cat = id_cat;
		this.id_jog = id_jog;
		this.id_ptda = id_ptda;
		this.tempoIn = tempoIn;
		this.tempoFi = tempoFi;
	}
	
	//Getters and Setters ID Evento
	@Override
	public void setId(int id) {
		this.id_eve = id;
	}
	@Override
	public int getId() {
		return id_eve;
	}
	
	//Getters and Setters ID Categoria
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	
	//Getters and Setters ID Jogador
	public int getId_jog() {
		return id_jog;
	}
	public void setId_jog(int id_jog) {
		this.id_jog = id_jog;
	}
	
	//Getters and Setters ID Equipe
	public int getId_ptda() {
		return id_ptda;
	}
	public void setId_ptda(int id_ptda) {
		this.id_ptda = id_ptda;
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
