package libraryManagement;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdminSuccess {

	private JFrame frame;
	private JLabel lblAdmin;
	private JButton btnLogout , btnManageStudentData , btnManageBooks;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSuccess window = new AdminSuccess();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public AdminSuccess() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblAdmin = new JLabel("ADMIN");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdmin.setBounds(222, 22, 162, 22);
		frame.getContentPane().add(lblAdmin);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start.main(new String[] {});
				frame.dispose();
			}
		});
		btnLogout.setBounds(190, 226, 167, 36);
		frame.getContentPane().add(btnLogout);
		
		btnManageStudentData = new JButton("Manage Student Data");
		btnManageStudentData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudent.main(new String[] {});
				frame.dispose();
			}
		});
		btnManageStudentData.setBounds(190, 104, 167, 36);
		frame.getContentPane().add(btnManageStudentData);
		
		btnManageBooks = new JButton("Manage Books");
		btnManageBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewBooks.main(new String[] {});
				frame.dispose();
			}
		});
		btnManageBooks.setBounds(190, 167, 167, 36);
		frame.getContentPane().add(btnManageBooks);
	}
}
