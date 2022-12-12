package br.com.unibra.entities;

import br.com.unibra.interfaces.StringData;

public class Movie implements StringData{
	private String name;
	private int duration;
	private double price;
	private String sinopse;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSinopse() {
		return sinopse;
	}
	public String getSinopseWithLineBreak() {
		String newSinopse = "";
		int cont = 0;
		for (int i = 0; i < sinopse.length(); i++) {
			newSinopse+=sinopse.charAt(i);
			if(cont>=50 && sinopse.charAt(i)==' ') {
				newSinopse+="\n";
				cont=0;
			}
			cont++;
		}
		return newSinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	@Override
	public String getStringData() {
		return name+";"+duration+";"+price+";"+sinopse;
	}
	
}
