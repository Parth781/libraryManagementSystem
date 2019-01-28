package libraryManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class AddStudent {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JComboBox<String> comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent window = new AddStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JPasswordField passwordField;
	
	public void fillComboBox() {
		try {
			String query = "select * from StudentInfo";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			 
			while(rs.next()) {
				comboBox.addItem(rs.getString("Name"));
			}
			rs.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void clearAll() {
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
		passwordField.setText("");
		textField4.setText("");
		textField5.setText("");
	}
	
	public AddStudent() {
		conn = DbConnection.Dbconnector();
		initialize();
		fillComboBox();
		clearAll();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 689, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(142, 75, 68, 14);
		frame.getContentPane().add(lblName);
		
		textField1 = new JTextField();
		textField1.setBounds(279, 72, 98, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel lblRollNo = new JLabel("Roll No");
		lblRollNo.setBounds(142, 113, 68, 14);
		frame.getContentPane().add(lblRollNo);
		
		textField2 = new JTextField();
		textField2.setBounds(279, 110, 98, 20);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(142, 155, 68, 14);
		frame.getContentPane().add(lblUsername);
		
		textField3 = new JTextField();
		textField3.setBounds(279, 152, 98, 20);
		frame.getContentPane().add(textField3);
		textField3.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(142, 198, 68, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblDivision = new JLabel("Division");
		lblDivision.setBounds(142, 239, 68, 14);
		frame.getContentPane().add(lblDivision);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setBounds(142, 274, 68, 14);
		frame.getContentPane().add(lblContactNo);
		
		textField4 = new JTextField();
		textField4.setBounds(279, 236, 98, 20);
		frame.getContentPane().add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setBounds(279, 274, 98, 20);
		frame.getContentPane().add(textField5);
		textField5.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into StudentInfo (Name,RollNo,UserName,Password,Division,ContactNo) values (?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField1.getText());
					pst.setString(2, textField2.getText());
					pst.setString(3, textField3.getText()); 
					pst.setString(4, passwordField.getText());
					pst.setString(5, textField4.getText());
					pst.setString(6, textField5.getText());
					if(textField1.getText().trim().equals("") || textField2.getText().trim().equals("") || textField3.getText().trim().equals("") || passwordField.getText().trim().equals("") || textField4.getText().trim().equals("") || textField5.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "incomplete data", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						pst.execute();
						JOptionPane.showMessageDialog(null, "data saved", "Success", JOptionPane.INFORMATION_MESSAGE);
						ViewStudent.main(new String[] {});
						pst.close();
						frame.dispose();
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnSave.setBounds(115, 339, 110, 30);
		frame.getContentPane().add(btnSave);
		
		JLabel lblAddStudent = new JLabel("Add or Update Student Data");
		lblAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddStudent.setBounds(197, 11, 213, 34);
		frame.getContentPane().add(lblAddStudent);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(279, 195, 98, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update StudentInfo set Name='"+textField1.getText()+"',RollNo='"+textField2.getText()+"',UserName='"+textField3.getText()+"',Password='"+passwordField.getText()+"',Division='"+textField4.getText()+"',ContactNo='"+textField5.getText()+"' where RollNo='"+textField2.getText()+"'";
					PreparedStatement pst = conn.prepareStatement(query);	
					if(textField1.getText().trim().equals("") || textField2.getText().trim().equals("") || textField3.getText().trim().equals("") || passwordField.getText().trim().equals("") || textField4.getText().trim().equals("") || textField5.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "incomplete data", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						pst.execute();
						JOptionPane.showMessageDialog(null, "data updated", "Success", JOptionPane.INFORMATION_MESSAGE);
						ViewStudent.main(new String[] {});
						pst.close();
						frame.dispose();
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnUpdate.setBounds(334, 339, 113, 30);
		frame.getContentPane().add(btnUpdate);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from StudentInfo where name=?";
					PreparedStatement pst = conn.prepareStatement(query);	
					pst.setString(1, (String)comboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					textField1.setText(rs.getString("Name"));
					textField2.setText(rs.getString("RollNo"));
					textField3.setText(rs.getString("UserName"));
					passwordField.setText(rs.getString("Password"));
					textField4.setText(rs.getString("Division"));
					textField5.setText(rs.getString("ContactNo"));
					pst.close();
					rs.close();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		comboBox.setBounds(486, 107, 103, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblQuickSelect = new JLabel("Quick select");
		lblQuickSelect.setBounds(486, 68, 103, 28);
		frame.getContentPane().add(lblQuickSelect);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnBackToMainmenu = new JMenu("Back");
		mnBackToMainmenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AdminSuccess.main(new String[] {});
				frame.dispose();
			}
		});
		
		JMenu mnDisplayData = new JMenu("Display Data");
		mnDisplayData.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ViewStudent.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnDisplayData);
		menuBar.add(mnBackToMainmenu);
		
		JMenu mnLogout = new JMenu("Logout");
		mnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AdminLogin.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnLogout);
	}
}