package DAO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import android.net.ParseException;
import android.util.Log;

public class Partida implements EntidadeInterface {

	private int id_ptda;
	private int id_eq;
	private int id_eqadv;
	private String local;
	private int gol_eq;
	private int gol_adv;
	private Date data_ptda;
	
	public Partida() { }
	
	public Partida(int id_ptda, int id_eq, int id_eqadv, String local, Date data_ptda) {
		this.id_ptda = id_ptda;
		this.id_eq = id_eq;
		this.id_eqadv = id_eqadv;
		this.local = local;
		this.gol_eq = 0;
		this.gol_adv = 0;
		this.data_ptda = data_ptda;
	}
	
	//Getters and Setters ID da Partida
	@Override
	public void setId(int id) {
		this.id_ptda = id;
	}
	@Override
	public int getId() {
		return id_ptda;
	}
	
	//Getters and Setters ID Equipes
	public long getId_eq() {
		return id_eq;
	}
	public void setId_eq(int id_eq) {
		this.id_eq = id_eq;
	}
	
	//Getters and Setters ID Eventos
	public long getId_eqadv() {
		return id_eqadv;
	}
	public void setId_eqadv(int id_eqadv) {
		this.id_eqadv = id_eqadv;
	}
	
	//Getters and Setters Local
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	//Getters and Setters Data do Jogo
	public Date getData() {
		return data_ptda;
	}
	
	public void setData(Date data) {
		this.data_ptda = data;
	}
	
	//Getters and Setters goleq
	
	public int getGol_eq() {
		return gol_eq;
	}
	public void setGol_eq(int gol_eq) {
		this.gol_eq = gol_eq;
	}
	
	//Getters and Setters goleqadv
	
	public int getGol_adv() {
		return gol_adv;
	}
	public void setGol_adv(int gol_adv) {
		this.gol_adv = gol_adv;
	}
	
	//conversão do Date para String
	public String dateToString() {
        //"05/09/2013 06:30:07"
		Date data = this.data_ptda;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String dataFormatada = format.format(data);
        Log.i("DATA String", dataFormatada);
        return dataFormatada;
    }

	//conversão do String para Date
    public Date stringToDate(String dataStr) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try {
                this.data_ptda = (Date) format.parse(dataStr);
        } catch (ParseException e) {
                e.printStackTrace();
        }
        return data_ptda;
    }
    
    public String toString() {
    	return this.local;
    }
	

}
