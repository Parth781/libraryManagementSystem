package libraryManagement;

import java.sql.*;
import javax.swing.*;

public class BookConnection {
	Connection conn = null;
	public static Connection Dbconnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\libraryManagement\\TotalBooks.sqlite");
			//JOptionPane.showMessageDialog(null, "connection successful");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
