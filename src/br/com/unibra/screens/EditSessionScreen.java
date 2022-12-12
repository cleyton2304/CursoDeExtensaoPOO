package br.com.unibra.screens;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.unibra.entities.Movie;
import br.com.unibra.entities.Session;
import br.com.unibra.util.Data;

public class EditSessionScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnDelete;
	private JButton btnBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSessionScreen frame = new EditSessionScreen();
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
	public EditSessionScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 421);
		setTitle("Edit Movie Theater Screen");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 12, 400, 329);

		table = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};

		table.setModel(updateSessionTable(Data.lerSessions()));
		table.getTableHeader().setReorderingAllowed(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);

		contentPane.add(scrollPane);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "You must select a session!");
					return;
				}
				int index = table.getSelectedRow();
				List<Session> sessions = Data.lerSessions();
				sessions.remove(index);
				updateSessionTable(sessions);
				Data.gravarSessions(sessions);
			}
		});
		btnDelete.setBounds(479, 57, 117, 25);
		contentPane.add(btnDelete);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagerInitialScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(490, 326, 117, 25);
		contentPane.add(btnBack);
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
			row[2]= s.getTheater();
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
