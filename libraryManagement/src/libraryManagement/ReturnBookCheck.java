package libraryManagement;

import java.sql.*;
import javax.swing.*;

public class ReturnBookCheck {
	public static int delete(String AccNo,int RollNo){
		int status=0;
		try{
			Connection con = IssueConnection.Dbconnector();
			
			status=updatebook(AccNo);//updating quantity and issue
			
			if(status>0){
				PreparedStatement ps=con.prepareStatement("delete from IssueBooks where AccNo=? and RollNo=?");
				ps.setString(1,AccNo);
				ps.setInt(2,RollNo);
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
			Connection con = BookConnection.Dbconnector();
			
			PreparedStatement ps=con.prepareStatement("select Quantity,Issued from Books where AccNo=?");
			ps.setString(1,AccNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				quantity=rs.getInt("quantity");
				issued=rs.getInt("issued");
			}
			
			if(issued>0){
				PreparedStatement ps2=con.prepareStatement("update books set quantity=?,issued=? where AccNo=?");
				ps2.setInt(1,quantity+1);
				ps2.setInt(2,issued-1);
				ps2.setString(3,AccNo);
			
				status=ps2.executeUpdate();
			}
			con.close();
		}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
		return status;
	}
}
