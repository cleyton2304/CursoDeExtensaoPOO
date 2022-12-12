package br.com.unibra.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.unibra.entities.Client;
import br.com.unibra.entities.Session;
import br.com.unibra.util.Data;
import javafx.beans.value.ChangeListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class ClientInitialScreen extends JFrame {

	private JPanel contentPane;
	public static Client loggedClient;
	private long date;
	private long initialDate;
	private final long dayInMs = 1000*60*60*24;
	private JTable table;
	private JLabel total;
	private JSpinner spinner;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientInitialScreen frame = new ClientInitialScreen();
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
	public ClientInitialScreen() {
		date = new Date().getTime();
		initialDate = date;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 473);
		setLocationRelativeTo(null);
		if(ClientInitialScreen.loggedClient!=null)
			setTitle("Client Screen - "+ClientInitialScreen.loggedClient.getLogin());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(new Date(this.date));

		JLabel dataLabel = new JLabel(dateString);
		dataLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setBounds(210, 38, 90, 15);
		contentPane.add(dataLabel);

		JButton backDate = new JButton("<<");
		backDate.setFocusable(false);
		backDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date newDate = new Date(date-dayInMs);
				if(newDate.before(new Date(initialDate)))
					return;
				date = newDate.getTime();

				String pattern = "dd/MM/yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String dateString = simpleDateFormat.format(new Date(date));
				dataLabel.setText(dateString);

				List<Session> sessions = new ArrayList<>();
				for (Session session : Data.lerSessions()) {
					String dateSession = simpleDateFormat.format(new Date(session.getDate()));
					if(dateSession.equals(dateString))
						sessions.add(session);
				}
				updateSessionTable(sessions);
			}
		});
		backDate.setFont(new Font("Dialog", Font.BOLD, 8));
		backDate.setBounds(142, 26, 48, 43);
		contentPane.add(backDate);

		JButton forwardDate = new JButton(">>");
		forwardDate.setFocusable(false);
		forwardDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date newDate = new Date(date+dayInMs);
				if(newDate.after(new Date(new Date().getTime()+7*dayInMs)))
					return;
				date = newDate.getTime();

				String pattern = "dd/MM/yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String dateString = simpleDateFormat.format(new Date(date));
				dataLabel.setText(dateString);

				List<Session> sessions = new ArrayList<>();
				for (Session session : Data.lerSessions()) {
					String dateSession = simpleDateFormat.format(new Date(session.getDate()));
					if(dateSession.equals(dateString))
						sessions.add(session);
				}
				updateSessionTable(sessions);
			}
		});
		forwardDate.setFont(new Font("Dialog", Font.BOLD, 8));
		forwardDate.setBounds(320, 26, 48, 43);
		contentPane.add(forwardDate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 79, 400, 292);

		table = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};

		List<Session> sessions = new ArrayList<>();
		for (Session session : Data.lerSessions()) {
			String dateSession = simpleDateFormat.format(new Date(session.getDate()));
			if(dateSession.equals(dateString))
				sessions.add(session);
		}
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(table.getSelectedRow()==-1)
					return;
				updatePrice();
			}
		});
		table.setModel(updateSessionTable(sessions));
		table.getTableHeader().setReorderingAllowed(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);

		contentPane.add(scrollPane);

		SpinnerModel value = new SpinnerNumberModel(0, 0, 10, 1);
		spinner = new JSpinner(value);
		spinner.addChangeListener(new javax.swing.event.ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(table.getSelectedRow()==-1)
					return;
				updatePrice();
			}
		});
		spinner.setFont(new Font("Dialog", Font.BOLD, 18));
		spinner.setBounds(220, 390, 68, 34);
		contentPane.add(spinner);

		JButton btnNewButton = new JButton("Avançar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "You must select a session!");
					return;							
				}
				if(Integer.parseInt(spinner.getValue().toString())==0) {
					JOptionPane.showMessageDialog(null, "Nothing to buy!\nAdd at least one ticket!");
					return;	
				}

				String movie = table.getValueAt(table.getSelectedRow(), 0).toString();
				String date = table.getValueAt(table.getSelectedRow(), 1).toString();
				String theater = table.getValueAt(table.getSelectedRow(), 2).toString().split(" ")[1];

				String pattern = "dd-MM-yyyy HH:mm";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

				List<Session> sessions = Data.lerSessions();
				sessions = sessions.stream().filter(s->s.getMovie().equals(movie) && date.equals(simpleDateFormat.format(new Date(s.getDate()))) &&
						Integer.parseInt(theater)==s.getTheater()).collect(Collectors.toList());

				new SelectSeatsScreen(sessions.get(0), Integer.parseInt(spinner.getValue().toString())).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(67, 396, 117, 25);
		contentPane.add(btnNewButton);

		total = new JLabel("Total: R$ 0,00");
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setFont(new Font("Dialog", Font.BOLD, 15));
		total.setBounds(298, 401, 159, 15);
		contentPane.add(total);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientInitialScreen.loggedClient = null;
				new LoginScreen().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(420, 12, 90, 25);
		contentPane.add(btnLogout);
	}

	protected void updatePrice() {
		String movie = table.getValueAt(table.getSelectedRow(), 0).toString();
		double price = Data.lerMovies().stream().filter(m->m.getName().equals(movie)).collect(Collectors.toList()).get(0).getPrice();
		NumberFormat formatter = new DecimalFormat("#0.00");
		total.setText("Total: R$ "+(formatter.format(price*Integer.parseInt(spinner.getValue().toString()))));
	}

	private DefaultTableModel updateSessionTable(List<Session> sessions) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = model.getRowCount()-1; i >= 0; i--) {
			model.removeRow(i);
		}

		String[] columnNames = {"Movie",
				"Date","Theater"};

		model.setColumnIdentifiers(columnNames);

		for (Session s : sessions) {
			Object[] row = new Object[3];
			row[0]= s.getMovie();

			String pattern = "dd-MM-yyyy HH:mm";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date(s.getDate()));

			row[1]= date;
			row[2]= "Sala "+s.getTheater();
			model.addRow(row);
		}


		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		return model;
	}
}
