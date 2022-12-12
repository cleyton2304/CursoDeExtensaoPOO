package br.com.unibra.entities;

import br.com.unibra.interfaces.StringData;

public class CreditCard implements StringData{
	private String number;
	private String name;
	private String code;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getStringData() {
		return number+";"+name+";"+code;
	}
}
