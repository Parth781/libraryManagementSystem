package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class RemoveStudent {

	private JFrame frame;
	private JTextField textField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveStudent window = new RemoveStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	public RemoveStudent() {
		conn = DbConnection.Dbconnector();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 582, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRemoveStudent = new JLabel("Remove Student");
		lblRemoveStudent.setBounds(249, 33, 110, 21);
		frame.getContentPane().add(lblRemoveStudent);
		
		JLabel lblEnterRollNo = new JLabel("Enter Roll No");
		lblEnterRollNo.setBounds(119, 87, 89, 14);
		frame.getContentPane().add(lblEnterRollNo);
		
		textField = new JTextField();
		textField.setBounds(245, 84, 114, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().trim().equals("")) {
					int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this", "Delete", JOptionPane.YES_NO_OPTION);
					if(action==0) {
						try {
							String query = "delete from StudentInfo where RollNo='"+textField.getText()+"' ";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.execute();
							pst.close();					
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Enter data to delete", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(134, 157, 119, 28);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewStudent.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(346, 157, 95, 28);
		frame.getContentPane().add(btnBack);
	}
}
