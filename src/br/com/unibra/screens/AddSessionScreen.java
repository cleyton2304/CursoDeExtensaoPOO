package br.com.unibra.screens;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.Movie;
import br.com.unibra.entities.MovieTheater;
import br.com.unibra.entities.Session;
import br.com.unibra.util.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddSessionScreen extends JFrame {

	private JPanel contentPane;

	private JSpinner dateSession;
	private JComboBox movie;
	private JButton btnSave;
	private JButton btnBack;
	private JComboBox theater;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSessionScreen frame = new AddSessionScreen();
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
	public AddSessionScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setTitle("Add Session");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setBounds(33, 17, 70, 15);
		contentPane.add(lblMovie);
		
		
		movie = new JComboBox(Data.lerMovies().stream().map(Movie::getName).collect(Collectors.toList()).toArray());
		movie.setBounds(88, 12, 219, 24);
		contentPane.add(movie);
		
		JLabel lblDay = new JLabel("Date");
		lblDay.setBounds(33, 106, 70, 15);
		contentPane.add(lblDay);
		
		dateSession = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSession, "dd/MM/yyyy HH:mm");
		dateSession.setEditor(editor);
		dateSession.setBounds(88, 104, 219, 20);
		contentPane.add(dateSession);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = new Session();
				session.setMovie(movie.getSelectedItem().toString());
				session.setTheater(Integer.parseInt(theater.getSelectedItem().toString().split(" ")[1]));
				session.setDate(((Date)(dateSession.getModel().getValue())).getTime());
				List<Session> sessions = Data.lerSessions();
				sessions.add(session);
				Data.gravarSessions(sessions);
				JOptionPane.showMessageDialog(null, "Session Saved!");
				new ManagerInitialScreen().setVisible(true);
				dispose();
			}
		});
		btnSave.setBounds(43, 142, 117, 25);
		contentPane.add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagerInitialScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(190, 142, 117, 25);
		contentPane.add(btnBack);
		
		JLabel lblTheater = new JLabel("Theater");
		lblTheater.setBounds(30, 62, 70, 15);
		contentPane.add(lblTheater);
		
		theater = new JComboBox(Data.lerMovieTheaters().stream().map(MovieTheater::getNameId).collect(Collectors.toList()).toArray());
		theater.setBounds(88, 57, 219, 24);
		contentPane.add(theater);
		
	}
}
