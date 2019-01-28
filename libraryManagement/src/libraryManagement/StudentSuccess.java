package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;



public class StudentSuccess {

	private JFrame frame;
	private JTable table;
	private JLabel lblWelcome,lblRollNo,lblDivision,lblContactNo , lblWelcome_1 , lblRollNo_1 , lblDivision_1 , lblContactNo_1;
	private JButton btnLogout;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSuccess window = new StudentSuccess();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public void refreshTable() {
		try {
			String name = StudentLogin.getUsername();
			PreparedStatement ps = con.prepareStatement("select * from StudentInfo where UserName='"+name+"'");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString("Name");
			}
			lblWelcome.setText(rs.getString("Name"));
			lblRollNo.setText(rs.getString("RollNo"));
			lblDivision.setText(rs.getString("Division"));
			lblContactNo.setText(rs.getString("ContactNo"));
			String query = "select * from IssueBooks where StudentName='"+name+"' ";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs1 = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs1));			
			conn.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	Connection conn = null;
	Connection con = null;
	
	public StudentSuccess() {
		initialize();
		conn = IssueConnection.Dbconnector();
		con = DbConnection.Dbconnector();
		refreshTable();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIssuedBooks = new JLabel("Issued Books");
		lblIssuedBooks.setBounds(444, 69, 99, 21);
		frame.getContentPane().add(lblIssuedBooks);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 101, 539, 257);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		
		scrollPane.setViewportView(table);
		
		lblWelcome = new JLabel("WELCOME: ");
		lblWelcome.setBounds(357, 25, 139, 14);
		frame.getContentPane().add(lblWelcome);
		
		lblRollNo = new JLabel("Roll No : ");
		lblRollNo.setBounds(90, 80, 84, 14);
		frame.getContentPane().add(lblRollNo);
		
		lblDivision = new JLabel("Division :");
		lblDivision.setBounds(90, 102, 77, 14);
		frame.getContentPane().add(lblDivision);
		
		lblContactNo = new JLabel("Contact No : ");
		lblContactNo.setBounds(90, 127, 99, 14);
		frame.getContentPane().add(lblContactNo);
		
		lblWelcome_1 = new JLabel("WELCOME");
		lblWelcome_1.setBounds(287, 25, 60, 14);
		frame.getContentPane().add(lblWelcome_1);
		
		lblRollNo_1 = new JLabel("Roll No:");
		lblRollNo_1.setBounds(26, 80, 54, 14);
		frame.getContentPane().add(lblRollNo_1);
		
		lblDivision_1 = new JLabel("Division:");
		lblDivision_1.setBounds(26, 102, 54, 14);
		frame.getContentPane().add(lblDivision_1);
		
		lblContactNo_1 = new JLabel("Contact No:");
		lblContactNo_1.setBounds(11, 127, 69, 14);
		frame.getContentPane().add(lblContactNo_1);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentLogin.main(new String[] {});
				frame.dispose();
			}
		});
		btnLogout.setBounds(43, 288, 124, 23);
		frame.getContentPane().add(btnLogout);
	}
}
