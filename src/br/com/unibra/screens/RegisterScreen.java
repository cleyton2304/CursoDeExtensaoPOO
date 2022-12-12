package br.com.unibra.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.Client;
import br.com.unibra.entities.CreditCard;
import br.com.unibra.entities.Manager;
import br.com.unibra.entities.SexTypes;
import br.com.unibra.entities.User;
import br.com.unibra.entities.UserTypes;
import br.com.unibra.util.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class RegisterScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField cpfField;
	private JTextField emailField;
	private JTextField passwordField;
	private JTextField numberField;
	private JTextField nameField;
	private JTextField codeField;
	private JComboBox sexComboBox;
	private JComboBox comboBox;
	private JLabel lblBirthDate;
	private JSpinner spinner;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
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
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setTitle("Register");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewUser = new JLabel("New User");
		lblNewUser.setFont(new Font("FreeSans", Font.BOLD, 20));
		lblNewUser.setBounds(204, 12, 148, 36);
		contentPane.add(lblNewUser);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(59, 75, 164, 15);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(163, 75, 203, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(59, 110, 164, 15);
		contentPane.add(lblCpf);
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(163, 110, 203, 19);
		contentPane.add(cpfField);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(59, 145, 164, 15);
		contentPane.add(lblSex);
		
//		sexField = new JTextField();
//		sexField.setColumns(10);
//		sexField.setBounds(163, 145, 203, 19);
//		contentPane.add(sexField);
		
		sexComboBox = new JComboBox(SexTypes.values());
		sexComboBox.setSelectedItem(null);
		sexComboBox.setBounds(163, 145, 203, 19);
		contentPane.add(sexComboBox);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(59, 180, 164, 15);
		contentPane.add(lblEmail);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(163, 180, 203, 19);
		contentPane.add(emailField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(59, 215, 164, 15);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(163, 215, 203, 19);
		contentPane.add(passwordField);
		
		comboBox = new JComboBox(UserTypes.values());
		comboBox.setSelectedItem(null);
		comboBox.setBounds(190, 250, 126, 24);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem()!=null && comboBox.getSelectedIndex()==0) {
					nameField.setEnabled(true);
					numberField.setEnabled(true);
					codeField.setEnabled(true);
					spinner.setVisible(true);
					lblBirthDate.setVisible(true);
				}else {
					nameField.setEnabled(false);
					numberField.setEnabled(false);
					codeField.setEnabled(false);
					spinner.setVisible(false);
					lblBirthDate.setVisible(false);
					nameField.setText("");
					numberField.setText("");
					codeField.setText("");
				}
			}
		});
		contentPane.add(comboBox);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setBounds(105, 255, 164, 15);
		contentPane.add(lblUserType);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(27, 332, 457, 15);
		contentPane.add(separator);
		
		JLabel lblCreditCardInformation = new JLabel("Credit Card Information");
		lblCreditCardInformation.setFont(new Font("FreeSans", Font.BOLD, 20));
		lblCreditCardInformation.setBounds(125, 359, 275, 36);
		contentPane.add(lblCreditCardInformation);
		
		JLabel lblcardNumber = new JLabel("Number");
		lblcardNumber.setBounds(59, 409, 164, 15);
		contentPane.add(lblcardNumber);
		
		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setEnabled(false);
		numberField.setBounds(163, 409, 203, 19);
		contentPane.add(numberField);
		
		JLabel lblcardName = new JLabel("Name");
		lblcardName.setBounds(59, 444, 164, 15);
		contentPane.add(lblcardName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setEnabled(false);
		nameField.setBounds(163, 444, 203, 19);
		contentPane.add(nameField);
		
		JLabel lblcardCode = new JLabel("Code");
		lblcardCode.setBounds(59, 479, 100, 15);
		contentPane.add(lblcardCode);
		
		codeField = new JTextField();
		codeField.setEnabled(false);
		codeField.setColumns(10);
		codeField.setBounds(163, 479, 61, 19);
		contentPane.add(codeField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSpinnerDate(spinner);
				//Validations
				if(verifyUserFields()) {
					JOptionPane.showMessageDialog(null, "Fill all user information!");
					return;
				}
				if(comboBox.getSelectedItem()!=null && comboBox.getSelectedIndex()==0 && verifyCreditCardFields()) {
					JOptionPane.showMessageDialog(null, "Fill all credit card information!");
					return;
				}
				

				List<User> users = Data.lerUsers();
				//Save User
				if(UserTypes.valueOf(comboBox.getSelectedItem().toString())==UserTypes.Client) {
					Client c = new Client();
					c.setBirth(new Date(getSpinnerDate(spinner)));
					c.setCpf(cpfField.getText());
					c.setEmail(emailField.getText());
					c.setLogin(usernameField.getText());
					c.setPassword(passwordField.getText());
					c.setSex(sexComboBox.getSelectedItem().toString().charAt(0));
					CreditCard cc = new CreditCard();
					cc.setCode(codeField.getText());
					cc.setName(nameField.getText());
					cc.setNumber(numberField.getText());
					c.setCard(cc);
					users.add(c);
				}else {
					Manager m = new Manager();
					m.setCpf(cpfField.getText());
					m.setEmail(emailField.getText());
					m.setLogin(usernameField.getText());
					m.setPassword(passwordField.getText());
					m.setSex(sexComboBox.getSelectedItem().toString().charAt(0));
					users.add(m);
				}
				
				Data.gravarUsers(users);
				JOptionPane.showMessageDialog(null, "Registered successfully!!");
				new LoginScreen().setVisible(true);
				dispose();
			}
		});
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setBounds(211, 506, 117, 25);
		contentPane.add(btnRegister);
		
		lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(59, 301, 164, 15);
		contentPane.add(lblBirthDate);
		lblBirthDate.setVisible(false);
		
		spinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
		spinner.setEditor(editor);
		spinner.setBounds(163, 300, 133, 20);
		spinner.setVisible(false);
		contentPane.add(spinner);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Dialog", Font.BOLD, 10));
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(433, 519, 61, 25);
		contentPane.add(btnBack);
	}

	protected boolean verifyCreditCardFields() {
		return nameField.getText().replace(" ","").equals("") || numberField.getText().replace(" ","").equals("") || codeField.getText().replace(" ","").equals("");
	}

	protected boolean verifyUserFields() {
		return usernameField.getText().replace(" ","").equals("") || cpfField.getText().replace(" ","").equals("") || emailField.getText().replace(" ","").equals("") || passwordField.getText().replace(" ","").equals("") || sexComboBox.getSelectedItem()==null || comboBox.getSelectedItem()==null;
	}
	
	protected long getSpinnerDate(JSpinner spinner) {
		return ((Date)(spinner.getModel().getValue())).getTime();
	}
}
