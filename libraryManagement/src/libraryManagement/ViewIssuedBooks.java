package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class ViewIssuedBooks {

	private JFrame frame;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIssuedBooks window = new ViewIssuedBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshTable() {
		try {
			String query = "select * from IssueBooks";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	Connection conn = null;

	public ViewIssuedBooks() {
		initialize();
		conn = IssueConnection.Dbconnector();
		refreshTable();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 537, 248);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooks.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(200, 280, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
