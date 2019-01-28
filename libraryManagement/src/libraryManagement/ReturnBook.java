package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReturnBook {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook window = new ReturnBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReturnBook() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 309);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReturnBook = new JLabel("Return Book");
		lblReturnBook.setBounds(208, 35, 78, 14);
		frame.getContentPane().add(lblReturnBook);
		
		JLabel lblBookAccNo = new JLabel("Book Acc No");
		lblBookAccNo.setBounds(104, 78, 91, 14);
		frame.getContentPane().add(lblBookAccNo);
		
		textField = new JTextField();
		textField.setBounds(223, 75, 102, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStudentRollNo = new JLabel("Student Roll No");
		lblStudentRollNo.setBounds(104, 122, 91, 14);
		frame.getContentPane().add(lblStudentRollNo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(223, 119, 102, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AccNo = textField.getText();
				int RollNo = Integer.parseInt(textField_1.getText());
				int i=ReturnBookCheck.delete(AccNo, RollNo);
				if(i>0){
					JOptionPane.showMessageDialog(null,"Book returned successfully!");
					ViewBooks.main(new String[]{});
					frame.dispose();					
				}else{
					JOptionPane.showMessageDialog(null,"Invalid details");
				}
				
			}
		});
		btnReturn.setBounds(104, 187, 89, 23);
		frame.getContentPane().add(btnReturn);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewBooks.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(238, 187, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
