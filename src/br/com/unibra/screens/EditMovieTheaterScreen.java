package br.com.unibra.screens;

import java.awt.EventQueue;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.unibra.entities.MovieTheater;
import br.com.unibra.util.Data;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMovieTheaterScreen extends JFrame {

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
					EditMovieTheaterScreen frame = new EditMovieTheaterScreen();
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
	public EditMovieTheaterScreen() {
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
	    
		table.setModel(updateMovieTheaterTable(Data.lerMovieTheaters()));
		table.getTableHeader().setReorderingAllowed(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);
		
		contentPane.add(scrollPane);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<MovieTheater> theaters = Data.lerMovieTheaters();
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "You must select a movie theater!");
					return;
				}
				int value = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				theaters = theaters.stream().filter(t->t.getId()!=value).collect(Collectors.toList());
				updateMovieTheaterTable(theaters);
				Data.gravarMovieTheaters(theaters);
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

	private DefaultTableModel updateMovieTheaterTable(List<MovieTheater> theaters) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = model.getRowCount()-1; i >= 0; i--) {
			model.removeRow(i);
		}
		
		String[] columnNames = {"Movie Theater",
                "Type"};
		
		model.setColumnIdentifiers(columnNames);
		
		for (MovieTheater mt : theaters) {
			Object[] row = new Object[2];
			row[0]= mt.getId()+"";
			row[1]= mt.getType().toString();
			model.addRow(row);
		}
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		return model;
	}
}
