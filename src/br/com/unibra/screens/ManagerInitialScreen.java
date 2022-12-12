package br.com.unibra.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.Manager;
import br.com.unibra.util.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerInitialScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Manager loggedManager;
	private JPanel contentPane;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerInitialScreen frame = new ManagerInitialScreen();
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
	public ManagerInitialScreen() {
		this.frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 368);
		setLocationRelativeTo(null);
		setTitle("Manager Screen - "+ManagerInitialScreen.loggedManager.getLogin());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblManagement = new JLabel("Management");
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setFont(new Font("Mallanna", Font.BOLD, 50));
		lblManagement.setBounds(12, 12, 407, 60);
		contentPane.add(lblManagement);

		JButton addSalaButton = new JButton("Add Sala");
		addSalaButton.setFont(new Font("Dialog", Font.BOLD, 9));
		addSalaButton.setFocusable(false);
		addSalaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddMovieTheaterScreen(frame).setVisible(true);
				setEnabled(false);
			}
		});
		addSalaButton.setBounds(63, 84, 93, 80);
		contentPane.add(addSalaButton);

		JButton btnEditSala = new JButton("Edit Sala");
		btnEditSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditMovieTheaterScreen().setVisible(true);
				dispose();
			}
		});
		btnEditSala.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditSala.setFocusable(false);
		btnEditSala.setBounds(63, 176, 93, 80);
		contentPane.add(btnEditSala);

		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddMovieScreen().setVisible(true);
				dispose();
			}
		});
		btnAddMovie.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAddMovie.setFocusable(false);
		btnAddMovie.setBounds(175, 84, 93, 80);
		contentPane.add(btnAddMovie);

		JButton btnEditSala_1 = new JButton("Edit Movie");
		btnEditSala_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditMovieScreen().setVisible(true);
				dispose();
			}
		});
		btnEditSala_1.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditSala_1.setFocusable(false);
		btnEditSala_1.setBounds(175, 176, 93, 80);
		contentPane.add(btnEditSala_1);

		JButton btnEditSala_1_1 = new JButton("Edit Session");
		btnEditSala_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Data.lerSessions().isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are no sessions registered!");
					return;
				}
				new EditSessionScreen().setVisible(true);
				dispose();
			}
		});
		btnEditSala_1_1.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditSala_1_1.setFocusable(false);
		btnEditSala_1_1.setBounds(282, 176, 93, 80);
		contentPane.add(btnEditSala_1_1);

		JButton btnAddSession = new JButton("Add Session");
		btnAddSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Data.lerMovies().isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are no movies registered!");
					return;
				}
				if(Data.lerMovieTheaters().isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are no movies theaters registered!");
					return;
				}
				new AddSessionScreen().setVisible(true);
				dispose();
			}
		});
		btnAddSession.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAddSession.setFocusable(false);
		btnAddSession.setBounds(282, 84, 93, 80);
		contentPane.add(btnAddSession);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(167, 287, 117, 25);
		contentPane.add(btnLogout);
	}
}
