package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Entidades.Equipe;
import Entidades.EquipeAdv;
import Entidades.Evento;
import Entidades.Jogador;
import Entidades.Partida;
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
				Jogador jogador1 = new Jogador(equipe.getId(), "Chico da Silva", 1, null, 186, format.parse("15/03/1996"), 1);
				Jogador jogador2 = new Jogador(equipe.getId(), "Fabio Fahrenheidt", 1, null, 181, format.parse("21/05/1995"), 3);
				Jogador jogador3 = new Jogador(equipe.getId(), "Joao da Laje", 1, null, 184, format.parse("19/10/1996"), 4);
				Jogador jogador4 = new Jogador(equipe.getId(), "Ronaldo Reagan", 1, null, 180, format.parse("15/03/1996"), 5);
				Jogador jogador5 = new Jogador(equipe.getId(), "Levy Fidelix", 1, null, 196, format.parse("15/03/1994"), 3);
				Jogador jogador6 = new Jogador(equipe.getId(), "Luciana Genro", 0, null, 170, format.parse("15/03/1999"), 2);
				Jogador jogador7 = new Jogador(equipe.getId(), "Ferrari Júnior", 0, null, 173, format.parse("15/03/1995"), 4);
				Jogador jogador8 = new Jogador(equipe.getId(), "Jéssica Alencar", 0, null, 175, format.parse("15/03/1992"), 3);
				Jogador jogador9 = new Jogador(equipe.getId(), "Maicon Mauro", 1, null, 189, format.parse("15/03/1994"), 2);
				Jogador jogador10 = new Jogador(equipe.getId(), "Luís Felipe Scolari", 1, null, 183, format.parse("15/03/1999"), 1);
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
			
			try {
				Partida partida = new Partida(1, 2, "Puta que o pariu", format.parse("28/03/1234"), "Zeni F.C", "Remor F.C");
				partida.setGol_eq(14);
				partida.setGol_adv(12);
				
				Partida partida2 = new Partida(1, 2, "Casa do caralho", format.parse("10/05/1999"), "Zeni F.C", "Remor F.C");
				partida2.setGol_eq(15);
				partida2.setGol_adv(19);
				
				Partida partida3 = new Partida(1, 2, "Casa do caralho", format.parse("10/05/1999"), "Zeni F.C", "Remor F.C");
				partida3.setGol_eq(10);
				partida3.setGol_adv(9);
				
				PartidaDAO.getInstancia(context).Inserir(partida);
				PartidaDAO.getInstancia(context).Inserir(partida2);
				PartidaDAO.getInstancia(context).Inserir(partida3);
				
				//categoria, jogador, partida, 0, 0
				Evento eventinho = new Evento(7, 5, 2, 0, 0);
				Evento evento = new Evento(1, 3, 1, 0, 0);
				Evento evento1 = new Evento(1, 3, 1, 0, 0);
				Evento evento2 = new Evento(1, 3, 1, 0, 0);
				Evento evento3 = new Evento(1, 2, 1, 0, 0);
				Evento evento4 = new Evento(1, 3, 2, 0, 0);
				Evento evento5 = new Evento(1, 5, 2, 0, 0);
				Evento evento6 = new Evento(1, 6, 2, 0, 0);
				Evento evento7 = new Evento(1, 4, 2, 0, 0);
				Evento evento8 = new Evento(1, 2, 2, 0, 0);
				Evento evento9 = new Evento(2, 2, 2, 0, 0);
				Evento evento99 = new Evento(2, 2, 2, 0, 0);
				Evento evento999 = new Evento(2, 2, 2, 0, 0);
				Evento evento9999 = new Evento(2, 2, 2, 0, 0);
				Evento evento99999 = new Evento(3, 2, 2, 0, 0);
				Evento evento999999 = new Evento(3, 2, 2, 0, 0);
				Evento evento9999999 = new Evento(4, 2, 2, 0, 0);
				Evento evento10 = new Evento(1, 1, 3, 0, 0);
				Evento evento11 = new Evento(1, 3, 3, 0, 0);
				Evento evento12 = new Evento(1, 2, 3, 0, 0);
				Evento evento13 = new Evento(1, 7, 3, 0, 0);
				Evento evento14 = new Evento(5, 3, 1, 0, 0);
				Evento evento15 = new Evento(5, 3, 1, 0, 0);
				Evento evento16 = new Evento(5, 3, 1, 0, 0);
				Evento evento17 = new Evento(5, 3, 1, 0, 0);
				Evento evento18 = new Evento(5, 3, 1, 0, 0);
				Evento evento19 = new Evento(5, 3, 1, 0, 0);
				Evento evento20 = new Evento(5, 3, 2, 0, 0);
				Evento evento21 = new Evento(5, 3, 2, 0, 0);
				Evento evento22 = new Evento(5, 3, 2, 0, 0);
				Evento evento23 = new Evento(5, 3, 2, 0, 0);
				Evento evento24 = new Evento(5, 3, 3, 0, 0);
				Evento evento25 = new Evento(5, 3, 3, 0, 0);
				Evento evento26 = new Evento(5, 3, 3, 0, 0);
				Evento evento27 = new Evento(5, 3, 3, 0, 0);
				Evento evento28 = new Evento(5, 3, 3, 0, 0);
				Evento evento29 = new Evento(6, 3, 1, 0, 0);
				Evento evento30 = new Evento(6, 3, 1, 0, 0);
				Evento evento31 = new Evento(6, 3, 1, 0, 0);
				Evento evento32 = new Evento(6, 3, 1, 0, 0);
				Evento evento33 = new Evento(6, 3, 1, 0, 0);
				Evento evento34 = new Evento(6, 3, 2, 0, 0);
				Evento evento35 = new Evento(6, 3, 2, 0, 0);
				Evento evento36 = new Evento(6, 3, 2, 0, 0);
				Evento evento37 = new Evento(6, 3, 2, 0, 0);
				Evento evento38 = new Evento(6, 3, 2, 0, 0);
				Evento evento39 = new Evento(6, 3, 2, 0, 0);
				Evento evento40 = new Evento(6, 3, 3, 0, 0);
				Evento evento41 = new Evento(6, 3, 3, 0, 0);
				Evento evento42 = new Evento(6, 3, 3, 0, 0);
				Evento evento43 = new Evento(6, 3, 3, 0, 0);
				Evento evento44 = new Evento(14, 3, 1, 0, 0);
				Evento evento45 = new Evento(14, 3, 1, 0, 0);
				Evento evento46 = new Evento(14, 3, 1, 0, 0);
				Evento evento47 = new Evento(14, 3, 1, 0, 0);
				Evento evento48 = new Evento(15, 3, 1, 0, 0);
				Evento evento49 = new Evento(15, 3, 1, 0, 0);
				Evento evento50 = new Evento(13, 3, 1, 0, 0);
				
				EventoDAO.getInstancia(context).Inserir(eventinho);
				EventoDAO.getInstancia(context).Inserir(evento);
				EventoDAO.getInstancia(context).Inserir(evento1);
				EventoDAO.getInstancia(context).Inserir(evento2);
				EventoDAO.getInstancia(context).Inserir(evento3);
				EventoDAO.getInstancia(context).Inserir(evento4);
				EventoDAO.getInstancia(context).Inserir(evento5);
				EventoDAO.getInstancia(context).Inserir(evento6);
				EventoDAO.getInstancia(context).Inserir(evento7);
				EventoDAO.getInstancia(context).Inserir(evento8);
				EventoDAO.getInstancia(context).Inserir(evento9);
				EventoDAO.getInstancia(context).Inserir(evento10);
				EventoDAO.getInstancia(context).Inserir(evento11);
				EventoDAO.getInstancia(context).Inserir(evento12);
				EventoDAO.getInstancia(context).Inserir(evento13);
				EventoDAO.getInstancia(context).Inserir(evento14);
				EventoDAO.getInstancia(context).Inserir(evento15);
				EventoDAO.getInstancia(context).Inserir(evento16);
				EventoDAO.getInstancia(context).Inserir(evento17);
				EventoDAO.getInstancia(context).Inserir(evento18);
				EventoDAO.getInstancia(context).Inserir(evento19);
				EventoDAO.getInstancia(context).Inserir(evento20);
				EventoDAO.getInstancia(context).Inserir(evento21);
				EventoDAO.getInstancia(context).Inserir(evento22);
				EventoDAO.getInstancia(context).Inserir(evento23);
				EventoDAO.getInstancia(context).Inserir(evento24);
				EventoDAO.getInstancia(context).Inserir(evento25);
				EventoDAO.getInstancia(context).Inserir(evento26);
				EventoDAO.getInstancia(context).Inserir(evento27);
				EventoDAO.getInstancia(context).Inserir(evento28);
				EventoDAO.getInstancia(context).Inserir(evento29);
				EventoDAO.getInstancia(context).Inserir(evento30);
				EventoDAO.getInstancia(context).Inserir(evento31);
				EventoDAO.getInstancia(context).Inserir(evento32);
				EventoDAO.getInstancia(context).Inserir(evento33);
				EventoDAO.getInstancia(context).Inserir(evento34);
				EventoDAO.getInstancia(context).Inserir(evento35);
				EventoDAO.getInstancia(context).Inserir(evento36);
				EventoDAO.getInstancia(context).Inserir(evento37);
				EventoDAO.getInstancia(context).Inserir(evento38);
				EventoDAO.getInstancia(context).Inserir(evento39);
				EventoDAO.getInstancia(context).Inserir(evento40);
				EventoDAO.getInstancia(context).Inserir(evento41);
				EventoDAO.getInstancia(context).Inserir(evento42);
				EventoDAO.getInstancia(context).Inserir(evento43);
				EventoDAO.getInstancia(context).Inserir(evento44);
				EventoDAO.getInstancia(context).Inserir(evento45);
				EventoDAO.getInstancia(context).Inserir(evento46);
				EventoDAO.getInstancia(context).Inserir(evento47);
				EventoDAO.getInstancia(context).Inserir(evento48);
				EventoDAO.getInstancia(context).Inserir(evento49);
				EventoDAO.getInstancia(context).Inserir(evento50);
				EventoDAO.getInstancia(context).Inserir(evento99);
				EventoDAO.getInstancia(context).Inserir(evento999);
				EventoDAO.getInstancia(context).Inserir(evento9999);
				EventoDAO.getInstancia(context).Inserir(evento99999);
				EventoDAO.getInstancia(context).Inserir(evento999999);
				EventoDAO.getInstancia(context).Inserir(evento9999999);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}

}
