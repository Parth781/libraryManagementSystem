package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StudentLogin {

	private JFrame frame;
	private static JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin window = new StudentLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	public static String getUsername() {
		String name = textField.getText();
		return name;
	}
	
	public StudentLogin() {
		initialize();
		conn = DbConnection.Dbconnector();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStudentLogin = new JLabel("STUDENT LOGIN");
		lblStudentLogin.setBounds(229, 44, 93, 14);
		frame.getContentPane().add(lblStudentLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(171, 111, 66, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(171, 151, 66, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(312, 108, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(312, 148, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "Select * from StudentInfo where Username=? and Password=?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						StudentSuccess.main(new String[] {});
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "invalid");
					}
					
					rs.close();
					pst.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBounds(159, 234, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(312, 234, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
