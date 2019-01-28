package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start {

	private JFrame frame;
	private JLabel lblLibraryLogin;
	private JButton btnAdmin;
	private JButton btnStudent;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Start() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 498, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblLibraryLogin = new JLabel("LIBRARY LOGIN");
		lblLibraryLogin.setBounds(194, 30, 123, 24);
		frame.getContentPane().add(lblLibraryLogin);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminLogin.main(new String[] {});
				frame.dispose();	
			}
		});
		btnAdmin.setBounds(183, 138, 105, 32);
		frame.getContentPane().add(btnAdmin);
		
		btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentLogin.main(new String[] {});
				frame.dispose();
			}
		});
		btnStudent.setBounds(183, 191, 105, 32);
		frame.getContentPane().add(btnStudent);
	}
}
