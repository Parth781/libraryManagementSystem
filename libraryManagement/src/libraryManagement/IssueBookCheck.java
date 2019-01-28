package libraryManagement;

import java.sql.*;
import javax.swing.*;

public class IssueBookCheck {
	public static boolean checkBook(String accNo){
		Connection con = null;
		boolean status = false;
		try{
			con = BookConnection.Dbconnector();
			PreparedStatement ps = con.prepareStatement("select * from Books where AccNo=?");
			ps.setString(1,accNo);
		    ResultSet rs = ps.executeQuery();
			status = rs.next();
			con.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return status;
	}

public static int save(String AccNo,String BookName,String StudentName,int RollNo,String ContactNo,String Date){
	int status=0;
	try{
		Connection con = IssueConnection.Dbconnector(); 
		
		status=updatebook(AccNo);//updating quantity and issue
		
		if(status>0){
			PreparedStatement ps=con.prepareStatement("insert into IssueBooks(AccNo,BookName,studentName,RollNo,ContactNo,Date) values(?,?,?,?,?,?)");
			ps.setString(1,AccNo);
			ps.setString(2,BookName);
			ps.setString(3,StudentName);
			ps.setInt(4,RollNo);
			ps.setString(5,ContactNo);
			ps.setString(6,Date);
			status=ps.executeUpdate();
		}
		
		con.close();
	}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
	return status;
}
public static int updatebook(String AccNo){
	int status=0;
	int quantity=0,issued=0;
	try{
		Connection con=BookConnection.Dbconnector();
		
		PreparedStatement ps=con.prepareStatement("select Quantity,Issued from Books where AccNo=?");
		ps.setString(1,AccNo);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("Quantity");
			issued=rs.getInt("Issued");
		}
		
		if(quantity>0){
		PreparedStatement ps2=con.prepareStatement("update Books set Quantity=?,Issued=? where AccNo=?");
		ps2.setInt(1,quantity-1);
		ps2.setInt(2,issued+1);
		ps2.setString(3,AccNo);
		
		status=ps2.executeUpdate();
		}
		con.close();
	}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
	return status;
}
}