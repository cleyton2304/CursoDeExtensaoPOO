package br.com.unibra.entities;

import javax.swing.JButton;

public class Seat extends JButton {
	private static final long serialVersionUID = 1L;
	private boolean available;
	
	public Seat() {
		available = true;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
