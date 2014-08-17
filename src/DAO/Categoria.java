package DAO;

public class Categoria implements EntidadeInterface {
	
	private long id_cat;
	private String descr;
	
	//Getters and Setters ID Categoria
	@Override
	public void setId(long id) {
		this.id_cat = id;
	}
	@Override
	public long getId() {
		return id_cat;
	}
	
	//Getters and Setters Descrição da Categoria
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getDescr() {
		return descr;
	}

}
