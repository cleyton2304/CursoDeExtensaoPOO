package br.com.unibra.entities;

import br.com.unibra.interfaces.StringData;

public class Session implements StringData{
	
	private String movie;
	private int theater;
	private long date;
	private boolean[][] seats;
	
	public Session() {
		seats = new boolean[10][15];
	}
	
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public boolean[][] getSeats() {
		return seats;
	}
	public void setSeats(boolean[][] seats) {
		this.seats = seats;
	}

	public int getTheater() {
		return theater;
	}

	public void setTheater(int theater) {
		this.theater = theater;
	}

	public boolean isSimilar(Session session) {
		return session.getMovie().equals(this.movie) && session.getDate()==this.date && session.getTheater()==this.theater;
	}
	
	@Override
	public String getStringData() {
		String ret = movie+";"+date+";"+theater+";";
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[0].length; j++) {
				ret+=seats[i][j]+";";
			}
		}
		return ret;
	}
}
