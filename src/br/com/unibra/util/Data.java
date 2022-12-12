package br.com.unibra.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.unibra.entities.Client;
import br.com.unibra.entities.CreditCard;
import br.com.unibra.entities.Manager;
import br.com.unibra.entities.Movie;
import br.com.unibra.entities.MovieTheater;
import br.com.unibra.entities.MovieTheaterType;
import br.com.unibra.entities.Session;
import br.com.unibra.entities.User;

public class Data {
	
	//Grava um texto no arquivo
	public static void gravarDados(String caminho,String str){		//str: Conteudo a ser gravado
		FileWriter arq;
		try {
			arq = new FileWriter(caminho);
			PrintWriter gravarArq = new PrintWriter(arq); 
			gravarArq.printf(str); 
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro na gravação do arquivo ...");
		} 
	}

	//Retorna um array com o conteudo do arquivo separado por linhas
	
	public static String[] lerDados(String caminho){		//caminho: caminho do arquivo em disco
		ArrayList<String> list = new ArrayList<String>();
		String[] retorno = null;
		BufferedReader in;
		File f = new File(caminho);
		try {
			if(!f.exists()){
				f.createNewFile();
				gravarDados(caminho, "");				//se não existir arquivo, ele cria e grava a string "0"
			}
			
			in = new BufferedReader(new FileReader(caminho));
			while (in.ready()) {
				String line = in.readLine();
				list.add(line);
			}
			in.close();
			retorno = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				retorno[i] = list.get(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static List<User> lerUsers(){
		List<User> users = new ArrayList<>();
		String[] lines = lerDados("users.txt");
		for (String line : lines) {
			String[] itens = line.split(";");
			if(itens.length==9) {
				Client c = new Client();
				c.setCpf(itens[0]);
				c.setLogin(itens[1]);
				c.setPassword(itens[2]);
				c.setSex(itens[3].charAt(0));
				c.setEmail(itens[4]);
				c.setBirth(new Date(Long.parseLong(itens[5])));
				CreditCard cc = new CreditCard();
				cc.setNumber(itens[6]);
				cc.setName(itens[7]);
				cc.setCode(itens[8]);
				c.setCard(cc);
				users.add(c);
			}else {
				Manager m = new Manager();
				m.setCpf(itens[0]);
				m.setLogin(itens[1]);
				m.setPassword(itens[2]);
				m.setSex(itens[3].charAt(0));
				m.setEmail(itens[4]);
				users.add(m);
			}
		}
		return users;
	}
	
	public static void gravarUsers(List<User> users){
		StringBuffer string = new StringBuffer("");
		for (User user : users) {
			if(user.getClass()==Client.class) {
				Client c = (Client)user;
				string.append(c.getStringData()+"\n");
			}else {
				Manager m = (Manager)user;
				string.append(m.getStringData()+"\n");
			}
		}
		Data.gravarDados("users.txt", string.toString());
	}
	
	public static List<MovieTheater> lerMovieTheaters(){
		List<MovieTheater> movieTheaters = new ArrayList<>();
		String[] lines = lerDados("theaters.txt");
		for (String line : lines) {
			String[] itens = line.split(";");
			if(itens.length==2) {
				MovieTheater mt = new MovieTheater();
				mt.setId(Integer.parseInt(itens[0]));
				mt.setType(MovieTheaterType.valueOf(itens[1]));
				movieTheaters.add(mt);
			}
		}
		return movieTheaters;
	}
	public static void gravarMovieTheaters(List<MovieTheater> theaters){
		StringBuffer string = new StringBuffer("");
		for (MovieTheater theater : theaters) {
			string.append(theater.getStringData()+"\n");
		}
		Data.gravarDados("theaters.txt", string.toString());
	}
	
	public static List<Movie> lerMovies(){
		List<Movie> movies = new ArrayList<>();
		String[] lines = lerDados("movies.txt");
		for (String line : lines) {
			String[] itens = line.split(";");
			if(itens.length==4) {
				Movie m = new Movie();
				m.setName(itens[0]);
				m.setDuration(Integer.parseInt(itens[1]));
				m.setPrice(Double.parseDouble(itens[2]));
				m.setSinopse(itens[3]);
				movies.add(m);
			}
		}
		return movies;
	}
	public static void gravarMovies(List<Movie> movies){
		StringBuffer string = new StringBuffer("");
		for (Movie movie : movies) {
			string.append(movie.getStringData()+"\n");
		}
		Data.gravarDados("movies.txt", string.toString());
	}
	
	public static List<Session> lerSessions(){
		List<Session> sessions = new ArrayList<>();
		String[] lines = lerDados("sessions.txt");
		for (String line : lines) {
			String[] itens = line.split(";");
			Session s = new Session();
			s.setMovie(itens[0]);
			s.setDate(Long.parseLong(itens[1]));
			s.setTheater(Integer.parseInt(itens[2]));
			int cont = 3;
			for (int i = 0; i < s.getSeats().length; i++) {
				for (int j = 0; j < s.getSeats()[0].length; j++) {
					s.getSeats()[i][j] = Boolean.parseBoolean(itens[cont++]);
				}
			}
			sessions.add(s);
		}
		return sessions;
	}
	public static void gravarSessions(List<Session> sessions){
		StringBuffer string = new StringBuffer("");
		for (Session session : sessions) {
			string.append(session.getStringData()+"\n");
		}
		Data.gravarDados("sessions.txt", string.toString());
	}
}