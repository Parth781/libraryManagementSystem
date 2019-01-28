package libraryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ViewStudent {
	private JFrame frame;
	private JTable table;
	private JLabel lblStudentData , lblSearch;
	private JTextField textFieldSearch;
	private JButton btnBack;
	private JComboBox<String> comboBoxSearch;
	private JScrollPane scrollPane;
	private JPanel panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudent window = new ViewStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	
	public void refreshTable() {
		try {
			String query = "select * from StudentInfo";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public ViewStudent() {
		conn = DbConnection.Dbconnector();
		initialize();
		refreshTable();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 43, 621, 255);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSuccess.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setBounds(430, 357, 109, 23);
		frame.getContentPane().add(btnBack);
		
		lblStudentData = new JLabel("Student Data");
		lblStudentData.setBounds(306, 19, 103, 14);
		frame.getContentPane().add(lblStudentData);
		
		panel = new JPanel();
		panel.setBounds(29, 321, 260, 71);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(92, 19, 60, 14);
		panel.add(lblSearch);
		
		comboBoxSearch = new JComboBox<String>();
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[] {"Name", "RollNo", "Division", "ContactNo"}));
		comboBoxSearch.setBounds(10, 44, 88, 20);
		panel.add(comboBoxSearch);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				try {
					String selection = (String)comboBoxSearch.getSelectedItem();
					String query = "select * from StudentInfo where "+selection+"=? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		textFieldSearch.setBounds(122, 44, 125, 20);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnEditData = new JMenu("Edit Student Data");
		menuBar.add(mnEditData);
		
		JMenuItem mntmAddNewStudent = new JMenuItem("Add new Student");
		mntmAddNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddStudent.main(new String[] {});
				frame.dispose();
			}
		});
		mnEditData.add(mntmAddNewStudent);
		
		JMenuItem mntmUpdateExisting = new JMenuItem("Update Existing");
		mntmUpdateExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent.main(new String[] {});
				frame.dispose();
			}
		});
		mnEditData.add(mntmUpdateExisting);
		
		JMenu mnRemoveStudent = new JMenu("Remove Student");
		mnRemoveStudent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				RemoveStudent.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnRemoveStudent);
		
		JMenu mnLogout = new JMenu("Logout");
		mnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AdminLogin.main(new String[] {});
				frame.dispose();
			}
		});
		menuBar.add(mnLogout);
		
		JMenu mnRefresh = new JMenu("Refresh");
		mnRefresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				refreshTable();
			}
		});
		menuBar.add(mnRefresh);
	}
}
