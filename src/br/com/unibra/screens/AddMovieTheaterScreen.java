package br.com.unibra.screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibra.entities.MovieTheater;
import br.com.unibra.entities.MovieTheaterType;
import br.com.unibra.util.Data;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class AddMovieTheaterScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int maxMovieTheater = 50;
	private JComboBox idComboBox;
	private JComboBox typeComboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieTheaterScreen frame = new AddMovieTheaterScreen(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param parentScreen 
	 */
	public AddMovieTheaterScreen(JFrame parentScreen) {
	
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	if(parentScreen!=null)
					parentScreen.setEnabled(true);
            }
        });
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setTitle("Add Movie Theater");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSalaId = new JLabel("Movie Theater ID");
		lblSalaId.setBounds(53, 26, 130, 15);
		contentPane.add(lblSalaId);
		
		idComboBox = new JComboBox(getAvailableIds().toArray());
		idComboBox.setBounds(189, 21, 70, 24);
		contentPane.add(idComboBox);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(79, 54, 70, 15);
		contentPane.add(lblType);
		
		typeComboBox = new JComboBox(MovieTheaterType.values());
		typeComboBox.setBounds(125, 53, 134, 24);
		contentPane.add(typeComboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<MovieTheater> theaters = Data.lerMovieTheaters();
				MovieTheater theater = new MovieTheater();
				theater.setId(Integer.parseInt(idComboBox.getSelectedItem().toString()));
				theater.setType(MovieTheaterType.valueOf(typeComboBox.getSelectedItem().toString()));
				theaters.add(theater);
				Data.gravarMovieTheaters(theaters);
				if(parentScreen!=null)
					parentScreen.setEnabled(true);
				dispose();
			}
		});
		btnSave.setBounds(41, 95, 117, 25);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parentScreen!=null)
					parentScreen.setEnabled(true);
				dispose();
			}
		});
		btnCancel.setBounds(170, 95, 117, 25);
		contentPane.add(btnCancel);
	}

	private List<Integer> getAvailableIds() {
		List<Integer> movieTheaterIds = Data.lerMovieTheaters().stream().map(MovieTheater::getId).collect(Collectors.toList());
		
		List<Integer> availableIds = new ArrayList<>();
		for (int i = 1; i <= maxMovieTheater; i++) {
			if(!movieTheaterIds.contains(i))
				availableIds.add(i);
		}
		return availableIds;
	}
}
