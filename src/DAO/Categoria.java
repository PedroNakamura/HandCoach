package DAO;

public class Categoria implements EntidadeInterface {
	
	private long id_cat;
	private String desc;
	
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
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}

}
