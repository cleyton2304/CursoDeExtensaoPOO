package br.com.unibra.entities;

import java.util.Date;

public class Client extends User {

	private Date birth;
	private CreditCard card;

	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public CreditCard getCard() {
		return card;
	}
	public void setCard(CreditCard card) {
		this.card = card;
	}
	
	public String getStringData() {
		return super.getStringData()+";"+birth.getTime()+";"+card.getStringData();
	}
}
