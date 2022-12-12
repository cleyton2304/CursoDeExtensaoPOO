package br.com.unibra.screens;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.Seat;
import br.com.unibra.entities.Session;
import br.com.unibra.util.Data;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class SelectSeatsScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Seat[][] seats;
	private int countSeats;
	private Session session;
	private int tickets;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectSeatsScreen frame = new SelectSeatsScreen(Data.lerSessions().get(0),2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectSeatsScreen(Session session, int tickets) {
		this.session = session;
		this.tickets = tickets;
		countSeats = tickets;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 473);
		setLocationRelativeTo(null);
		setTitle("Select Seats");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuySeats = new JButton("Buy Seats");
		btnBuySeats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Session> sessions = Data.lerSessions();
				Session sessionLocal = sessions.stream().filter(s->s.isSimilar(session)).collect(Collectors.toList()).get(0);
				//update Session
				for (int i = 0; i < sessionLocal.getSeats().length; i++) {
					for (int j = 0; j < sessionLocal.getSeats()[0].length; j++) {
						if(!seats[i][j].isAvailable())
							sessionLocal.getSeats()[i][j] = true; 
					}
				}
				Data.gravarSessions(sessions);
				JOptionPane.showMessageDialog(null, "Purchase made!!!");
				
				
				
				
				new ClientInitialScreen().setVisible(true);
				dispose();
			}
		});
		btnBuySeats.setEnabled(false);
		btnBuySeats.setBounds(206, 367, 117, 25);
		contentPane.add(btnBuySeats);
		
		JButton btnNewButton = new JButton("Cinema Screen");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 10));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(12, 343, 498, 16);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClientInitialScreen().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(216, 399, 102, 25);
		contentPane.add(btnNewButton_1);
		
		String pattern = "dd-MM-yyyy HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		JLabel lblTitle = new JLabel(session.getMovie());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitle.setBounds(12, 0, 498, 25);
		contentPane.add(lblTitle);
		
		JLabel lblDate = new JLabel(simpleDateFormat.format(new Date(session.getDate())));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(12, 26, 498, 15);
		contentPane.add(lblDate);
		
		JLabel lblTickets = new JLabel("Selecione "+tickets+" lugares!");
		lblTickets.setHorizontalAlignment(SwingConstants.CENTER);
		lblTickets.setBounds(12, 46, 498, 16);
		contentPane.add(lblTickets);
		
		seats = new Seat[session.getSeats().length][session.getSeats()[0].length];
		int x = 50, y = 70, size =25;
		for (int i = 0; i < session.getSeats().length; i++) {
			x=60;
			for (int j = 0; j < session.getSeats()[0].length; j++) {
				seats[i][j] = new Seat();
				if(!session.getSeats()[i][j]) {
					seats[i][j].setEnabled(true);
					seats[i][j].setBackground(Color.CYAN);
				}else {
					seats[i][j].setEnabled(false);
					seats[i][j].setBackground(Color.RED);
				}
				seats[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Seat seat = (Seat) e.getSource();
						if(countSeats==0 && seat.isAvailable())
							return;
						seat.setAvailable(!seat.isAvailable());
						seat.setBackground(seat.isAvailable()?Color.CYAN:Color.GREEN);
						if(seat.isAvailable()) {
							countSeats++;
							btnBuySeats.setEnabled(false);
						}else {
							countSeats--;
							if(countSeats==0)
								btnBuySeats.setEnabled(true);
						}
					}
				});
				seats[i][j].setBounds(x, y, size, size);
				contentPane.add(seats[i][j]);
				x+=size+2;
			}
			y+=size+2;
		}
	}

}
