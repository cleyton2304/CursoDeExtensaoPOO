package br.com.unibra.entities;

import br.com.unibra.interfaces.StringData;

public class User implements StringData{
	private String login;
	private String password;
	private String cpf;
	private char sex;
	private String email;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStringData() {
		return cpf+";"+login+";"+password+";"+sex+";"+email;
	}
}
