package SDET;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* 1) create a connection
 * 2) create statement\query
 * 3) Execute query then store the results
 * 4) print the results
*/

public class RetreiveDataFromDB_JDBC {

	public static void main(String[] args) throws SQLException {
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root" , "root");
		
		Statement stmt=con.createStatement();
		
		String s="select *from CustomerInfo limit 1";
		
		ResultSet  rs=stmt.executeQuery(s);
		
		while(rs.next()) {
			
			String bookname=rs.getString("BookName");
			String purchasedate =rs.getString("PurchasedDate");
			int amount =rs.getInt("Amount");
			String location =rs.getString("Location");
			
			System.out.println(bookname +" "+purchasedate+" "+amount+" "+location);
			
			
		}
		
		con.close();
		

	}

}
