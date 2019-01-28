package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ViewBooks {
	private JFrame frame;
	private JTable table;
	private JTextField textField1 , textField2 , textField3 , textField4 , textField5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks window = new ViewBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;

	public void refreshBookTable() {
		try {
			String query = "select * from Books";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void clearAll() {
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
		textField5.setText("");
	}
	
	public ViewBooks() {
		conn = BookConnection.Dbconnector();
		initialize();
		refreshBookTable();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBooksAvailable = new JLabel("Books Available");
		lblBooksAvailable.setBounds(247, 11, 104, 14);
		frame.getContentPane().add(lblBooksAvailable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 46, 453, 317);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String num = (table.getModel().getValueAt(row, 0)).toString();
					String query = "select * from Books where AccNo='"+num+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					textField1.setText(rs.getString("AccNo"));
					textField2.setText(rs.getString("Name"));
					textField3.setText(rs.getString("Author"));
					textField4.setText(rs.getString("Quantity"));
					textField5.setText(rs.getString("Issued"));
					pst.close();
					rs.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminSuccess.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(276, 392, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblAccno = new JLabel("AccNo");
		lblAccno.setBounds(29, 47, 65, 14);
		frame.getContentPane().add(lblAccno);
		
		textField1 = new JTextField();
		textField1.setBounds(117, 44, 104, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(29, 83, 65, 14);
		frame.getContentPane().add(lblName);
		
		textField2 = new JTextField();
		textField2.setBounds(117, 80, 104, 20);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(29, 119, 65, 14);
		frame.getContentPane().add(lblAuthor);
		
		textField3 = new JTextField();
		textField3.setBounds(117, 119, 104, 20);
		frame.getContentPane().add(textField3);
		textField3.setColumns(10);
		
		JLabel lblQauntity = new JLabel("Qauntity");
		lblQauntity.setBounds(29, 155, 65, 14);
		frame.getContentPane().add(lblQauntity);
		
		JLabel lblIssued = new JLabel("Issued");
		lblIssued.setBounds(29, 193, 46, 14);
		frame.getContentPane().add(lblIssued);
		
		textField4 = new JTextField();
		textField4.setBounds(117, 152, 104, 20);
		frame.getContentPane().add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setBounds(117, 190, 104, 20);
		frame.getContentPane().add(textField5);
		textField5.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "insert into Books (AccNo,Name,Author,Quantity,Issued) values (?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField1.getText());
					pst.setString(2, textField2.getText());
					pst.setString(3, textField3.getText()); 
					pst.setString(4, textField4.getText());
					pst.setString(5, textField5.getText());
					if(textField1.getText().trim().equals("") || textField2.getText().trim().equals("") || textField3.getText().trim().equals("") || textField4.getText().trim().equals("") || textField5.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "incomplete data", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						pst.execute();
						JOptionPane.showMessageDialog(null, "data saved", "Success", JOptionPane.INFORMATION_MESSAGE);
						refreshBookTable();
						pst.close();
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnSave.setBounds(52, 240, 119, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Books set AccNo='"+textField1.getText()+"',Name='"+textField2.getText()+"',Author='"+textField3.getText()+"',Quantity='"+textField4.getText()+"',Issued='"+textField5.getText()+"' where AccNo='"+textField1.getText()+"'";
					PreparedStatement pst = conn.prepareStatement(query);	
					if(textField1.getText().trim().equals("") || textField2.getText().trim().equals("") || textField3.getText().trim().equals("") || textField4.getText().trim().equals("") || textField5.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "incomplete data", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						pst.execute();
						JOptionPane.showMessageDialog(null, "data updated", "Success", JOptionPane.INFORMATION_MESSAGE);
						refreshBookTable();
						pst.close();
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnEdit.setBounds(52, 285, 119, 23);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAll();
			}
		});
		btnClear.setBounds(52, 328, 119, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnIssuedBooks = new JButton("Issued Books");
		btnIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewIssuedBooks.main(new String[] {});
				frame.dispose();
			}
		});
		btnIssuedBooks.setBounds(52, 368, 119, 23);
		frame.getContentPane().add(btnIssuedBooks);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu mnIssueBook = new JMenu("Issue");
		mnIssueBook.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				IssueBooks.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnIssueBook);
		
		JMenu mnReturn = new JMenu("Return");
		mnReturn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				ReturnBook.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnReturn);
		
		JMenu mnLogout = new JMenu("Logout");
		mnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				AdminLogin.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnLogout);
	}
}
