package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Entidades.Equipe;
import Entidades.Jogador;
import android.content.Context;

public class Inicializacao {

	public static void inicializa(Context context) {
	
		Equipe equipe = new Equipe("Zeni F.C");
		
		if(EquipeDAO.getInstancia(context).buscarNome(equipe.getNome()).size() == 0){
			
			EquipeAdv equipeAdv = new EquipeAdv("Remor F.C");
	
			EquipeDAO.getInstancia(context).Inserir(equipe);
			EquipeAdvDAO.getInstancia(context).Inserir(equipeAdv);
	
			List<Equipe> equipeNome = (List<Equipe>) EquipeDAO.getInstancia(context).buscarNome(equipe.getNome());
			equipe = equipeNome.get(0);
	
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
	
			try {
				Jogador jogador1 = new Jogador(equipe.getId(), "Chico da Silva", 1, null, "1m86cm", format.parse("15/03/1996"), 1);
				Jogador jogador2 = new Jogador(equipe.getId(), "Fabio Fahrenheidt", 1, null, "1m81cm", format.parse("21/05/1995"), 3);
				Jogador jogador3 = new Jogador(equipe.getId(), "Joao da Laje", 1, null, "1m84cm", format.parse("19/10/1996"), 4);
				Jogador jogador4 = new Jogador(equipe.getId(), "Ronaldo Reagan", 1, null, "1m80cm", format.parse("15/03/1996"), 5);
				Jogador jogador5 = new Jogador(equipe.getId(), "Levy Fidelix", 1, null, "1m96cm", format.parse("15/03/1994"), 3);
				Jogador jogador6 = new Jogador(equipe.getId(), "Luciana Genro", 0, null, "1m70cm", format.parse("15/03/1999"), 2);
				Jogador jogador7 = new Jogador(equipe.getId(), "Ferrari Júnior", 0, null, "1m73cm", format.parse("15/03/1995"), 4);
				Jogador jogador8 = new Jogador(equipe.getId(), "Jéssica Alencar", 0, null, "1m75cm", format.parse("15/03/1992"), 3);
				Jogador jogador9 = new Jogador(equipe.getId(), "Maicon Mauro", 1, null, "1m89cm", format.parse("15/03/1994"), 2);
				Jogador jogador10 = new Jogador(equipe.getId(), "Luís Felipe Scolari", 1, null, "1m83cm", format.parse("15/03/1999"), 1);
				JogadorDAO.getInstancia(context).Inserir(jogador1);
				JogadorDAO.getInstancia(context).Inserir(jogador2);
				JogadorDAO.getInstancia(context).Inserir(jogador3);
				JogadorDAO.getInstancia(context).Inserir(jogador4);
				JogadorDAO.getInstancia(context).Inserir(jogador5);
				JogadorDAO.getInstancia(context).Inserir(jogador6);
				JogadorDAO.getInstancia(context).Inserir(jogador7);
				JogadorDAO.getInstancia(context).Inserir(jogador8);
				JogadorDAO.getInstancia(context).Inserir(jogador9);
				JogadorDAO.getInstancia(context).Inserir(jogador10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
