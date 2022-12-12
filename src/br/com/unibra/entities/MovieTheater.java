package br.com.unibra.entities;

import br.com.unibra.interfaces.StringData;

public class MovieTheater implements StringData{
	private int id;
	private MovieTheaterType type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MovieTheaterType getType() {
		return type;
	}
	public void setType(MovieTheaterType type) {
		this.type = type;
	}
	public String getNameId() {
		return "Sala "+id;
	}
	
	@Override
	public String getStringData() {
		return id+";"+type.toString();
	}
}
