package br.com.unibra.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.Movie;
import br.com.unibra.util.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class AddMovieScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField durationField;
	private JTextField priceField;
	private JTextArea sinopse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieScreen frame = new AddMovieScreen();
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
	public AddMovieScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 348);
		setLocationRelativeTo(null);
		setTitle("Add Movie");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 12, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblDuration = new JLabel("Duration (min)");
		lblDuration.setBounds(24, 39, 104, 15);
		contentPane.add(lblDuration);
		
		JLabel lblPrice = new JLabel("Price (R$)");
		lblPrice.setBounds(24, 66, 70, 15);
		contentPane.add(lblPrice);
		
		JLabel lblSinopse = new JLabel("Sinopse");
		lblSinopse.setBounds(151, 106, 70, 15);
		contentPane.add(lblSinopse);
		
		nameField = new JTextField();
		nameField.setBounds(146, 10, 155, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		durationField = new JTextField();
		durationField.setColumns(10);
		durationField.setBounds(146, 37, 155, 19);
		contentPane.add(durationField);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(146, 64, 155, 19);
		contentPane.add(priceField);
		
		sinopse = new JTextArea();
		sinopse.setBounds(45, 133, 279, 137);
		sinopse.setLineWrap(true);
		sinopse.setWrapStyleWord(true);
		contentPane.add(sinopse);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validation
				if(nameField.getText().replace(" ", "").equals("") || priceField.getText().replace(" ", "").equals("") || durationField.getText().replace(" ", "").equals("")
						|| sinopse.getText().replace(" ", "").equals("")) {
					JOptionPane.showMessageDialog(null, "Fill all movie information!");
					return;
				}
				Movie m = new Movie();
				m.setName(nameField.getText());
				m.setDuration(Integer.parseInt(durationField.getText()));
				m.setPrice(Double.parseDouble(priceField.getText().replace(",", ".")));
				m.setSinopse(sinopse.getText());
				
				List<Movie> movies = Data.lerMovies();
				
				if(movies.stream().filter(mt->mt.getName().equals(m.getName())).collect(Collectors.toList()).isEmpty()) {
					movies.add(m);
					Data.gravarMovies(movies);
					JOptionPane.showMessageDialog(null, "Registered successfully!!!");
					new ManagerInitialScreen().setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "There are a movie with the same name!");
					return;
				}
				
			}
		});
		btnRegister.setBounds(55, 280, 117, 25);
		contentPane.add(btnRegister);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagerInitialScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(201, 280, 117, 25);
		contentPane.add(btnBack);
	}
}
