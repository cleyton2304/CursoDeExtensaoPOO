package br.com.unibra.screens;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import br.com.unibra.util.Data;

public class EditMovieScreen extends JFrame {

	private static final long serialVersionUID = 1L;
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
					EditMovieScreen frame = new EditMovieScreen();
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
	public EditMovieScreen() {
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

		table.setModel(updateMovieTable(Data.lerMovies()));
		table.getTableHeader().setReorderingAllowed(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);

		contentPane.add(scrollPane);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Movie> movies = Data.lerMovies();
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "You must select a movie!");
					return;
				}
				String name = table.getValueAt(table.getSelectedRow(), 0).toString();
				movies = movies.stream().filter(m->!m.getName().equals(name)).collect(Collectors.toList());
				updateMovieTable(movies);
				Data.gravarMovies(movies);
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
		
		JButton btnSinopse = new JButton("Sinopse");
		btnSinopse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "You must select a movie!");
					return;
				}
				List<Movie> movies = Data.lerMovies();
				String name = table.getValueAt(table.getSelectedRow(), 0).toString();
				movies = movies.stream().filter(m->m.getName().equals(name)).collect(Collectors.toList());
				JOptionPane.showMessageDialog(null, movies.get(0).getSinopseWithLineBreak());
			}
		});
		btnSinopse.setBounds(479, 94, 117, 25);
		contentPane.add(btnSinopse);
	}

	private DefaultTableModel updateMovieTable(List<Movie> movies) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = model.getRowCount()-1; i >= 0; i--) {
			model.removeRow(i);
		}

		String[] columnNames = {"Movie",
		"Duration (min)","Price (R$)"};

		model.setColumnIdentifiers(columnNames);

		for (Movie m : movies) {
			Object[] row = new Object[3];
			row[0]= m.getName();
			row[1]= m.getDuration();
			row[2]= m.getPrice();
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
