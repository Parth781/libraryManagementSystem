package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class IssueBooks {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1 , textField_2 , textField_3 , textField_4;
	private static JTextField textField_5;
	private JLabel lblIssueBook , lblAccno , lblBookName , lblStudentName , lblRollNo , lblContactNo , lblDate;
	private JButton btnIssueBook , btnBack;

	static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBooks window = new IssueBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static int setDate() {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		textField_5.setText(+day+"/"+month+"/"+year);
		return day;
	}
	
	public IssueBooks() {
		initialize();
		setDate();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 506, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblIssueBook = new JLabel("Issue Book");
		lblIssueBook.setBounds(194, 29, 81, 14);
		frame.getContentPane().add(lblIssueBook);
		
		lblAccno = new JLabel("AccNo");
		lblAccno.setBounds(73, 82, 67, 14);
		frame.getContentPane().add(lblAccno);
		
		lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(73, 117, 67, 14);
		frame.getContentPane().add(lblBookName);
		
		lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(73, 153, 86, 14);
		frame.getContentPane().add(lblStudentName);
		
		lblRollNo = new JLabel("Roll No");
		lblRollNo.setBounds(73, 190, 56, 14);
		frame.getContentPane().add(lblRollNo);
		
		lblContactNo = new JLabel("Contact No");
		lblContactNo.setBounds(73, 228, 67, 14);
		frame.getContentPane().add(lblContactNo);
		
		textField = new JTextField();
		textField.setBounds(224, 79, 103, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(224, 114, 103, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(224, 150, 103, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(224, 187, 103, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(224, 225, 103, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String AccNo = textField.getText();
					String BookName = textField_1.getText();
					String StudentName = textField_2.getText();
					int RollNo = Integer.parseInt(textField_3.getText());
					String ContactNo = textField_4.getText();
					String Date = textField_5.getText();
					if(IssueBookCheck.checkBook(AccNo)) {
						int i=IssueBookCheck.save(AccNo, BookName, StudentName, RollNo , ContactNo , Date);
						if(i>0){
							JOptionPane.showMessageDialog(null,"Book issued successfully!");
							ViewBooks.main(new String[]{});
							frame.dispose();							
						}else{
							JOptionPane.showMessageDialog(null,"Sorry, unable to issue!");
						}
						}else{
							JOptionPane.showMessageDialog(null,"Sorry, AccNo doesn't exist!");
						}					
						
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnIssueBook.setBounds(102, 319, 103, 23);
		frame.getContentPane().add(btnIssueBook);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewBooks.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(259, 319, 89, 23);
		frame.getContentPane().add(btnBack);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(73, 262, 67, 14);
		frame.getContentPane().add(lblDate);
		
		textField_5 = new JTextField();
		textField_5.setBounds(224, 259, 103, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
	}
}
