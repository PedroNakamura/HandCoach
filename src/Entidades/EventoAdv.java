package Entidades;


public class EventoAdv implements EntidadeInterface {
	
	private int id_eve_adv;
	private int id_eqadv;
	private int id_cat;
	private int id_ptda;
	private double tempoIn;
	private double tempoFi;
	
    //Getters and Setters ID
	@Override
	public void setId(int id) {
		this.id_eve_adv = id;
	}
	@Override
	public int getId() {
		return this.id_eve_adv;
	}
	
	//Getters and Setters ID Eq adv
	public int getId_eqadv() {
		return id_eqadv;
	}
	public void setId_eqadv(int id_eqadv) {
		this.id_eqadv = id_eqadv;
	}
	
	//Getters and Setters ID categoria
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	
	//Getters and Setters ID partida
	public int getId_ptda() {
		return id_ptda;
	}
	public void setId_ptda(int id_ptda) {
		this.id_ptda = id_ptda;
	}
	
	//Getters and Setters tempoIn
	public double getTempoIn() {
		return tempoIn;
	}
	public void setTempoIn(double tempoIn) {
		this.tempoIn = tempoIn;
	}
	
	//Getters and Setters tempoFi
	public double getTempoFi() {
		return tempoFi;
	}
	public void setTempoFi(double tempoFi) {
		this.tempoFi = tempoFi;
	}

}
