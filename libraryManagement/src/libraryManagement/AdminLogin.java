package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminLogin {

	private JFrame frame;
	private JLabel lblAdminLogin , lblUsername , lblPassword;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin , btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 482, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setBounds(214, 43, 91, 22);
		frame.getContentPane().add(lblAdminLogin);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(91, 109, 64, 14);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(91, 151, 64, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(209, 106, 111, 20);
		frame.getContentPane().add(textField);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				if(username.equals("admin") && password.equals("admin123")) {
					//JOptionPane.showMessageDialog(null, "Success");
					AdminSuccess.main(new String[]{});
					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Username or password error", "Login error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(91, 223, 100, 23);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(209, 148, 111, 20);
		frame.getContentPane().add(passwordField);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(243, 223, 91, 23);
		frame.getContentPane().add(btnBack);
	}
}
