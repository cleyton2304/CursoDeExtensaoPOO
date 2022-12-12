package br.com.unibra.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.Client;
import br.com.unibra.entities.Manager;
import br.com.unibra.entities.User;
import br.com.unibra.util.Data;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField loginField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Login");
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginField = new JTextField();
		loginField.setBounds(96, 25, 185, 19);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(96, 55, 185, 19);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validations
				if(loginField.getText().replace(" ", "").length()==0 || passwordField.getText().replace(" ", "").length()==0) {
					JOptionPane.showMessageDialog(null, "Fill all Login information!");
					return;
				}
				
				//Try to login
				List<User> users = Data.lerUsers().stream().
						filter(u->u.getLogin().equals(loginField.getText()) && u.getPassword().equals(passwordField.getText())).collect(Collectors.toList());
				
				if(users.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Credenciais inválidas!");
					return;
				}
				
				if(users.get(0).getClass()==Client.class) {
					ClientInitialScreen.loggedClient = (Client) users.get(0);
					new ClientInitialScreen().setVisible(true);
					dispose();
				}else {
					ManagerInitialScreen.loggedManager = (Manager) users.get(0);
					new ManagerInitialScreen().setVisible(true);
					dispose();
				}
			}
		});
		loginButton.setBounds(106, 86, 117, 25);
		contentPane.add(loginButton);
		
		JButton loginRegister = new JButton("Register");
		loginRegister.setFont(new Font("Dialog", Font.BOLD, 10));
		loginRegister.setBackground(new Color(0,0,0,0));
		loginRegister.setBorder(null);
		loginRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterScreen().setVisible(true);
				dispose();
			}
		});
		loginRegister.setBounds(120, 131, 91, 25);
		contentPane.add(loginRegister);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(15, 25, 70, 15);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(15, 55, 70, 15);
		contentPane.add(lblPassword);
	}
}
