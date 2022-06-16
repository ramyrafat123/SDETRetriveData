package SDET;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

/* 1) create a connection
 * 2) create statement\query
 * 3) Execute query then store the results
 * 4) print the results
*/

public class ConvertDBResults_javaObjects_jsonFiles {

	public static void main(String[] args) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root" , "root");
		
		Statement stmt=con.createStatement();
		
		String s="select *from CustomerInfo ";
		
		ResultSet  rs=stmt.executeQuery(s);
		
		ArrayList<CustomerDetails> ar= new ArrayList<CustomerDetails>();
		
		while(rs.next()) {
			
			String bookname=rs.getString("BookName");
			String purchasedate =rs.getString("PurchasedDate");
			int amount =rs.getInt("Amount");
			String location =rs.getString("Location");
			CustomerDetails cd=new CustomerDetails();
//			System.out.println(bookname +" "+purchasedate+" "+amount+" "+location);
			cd.setBookname(bookname);
			cd.setAmount(amount);
			cd.setLocation(location);
			cd.setPurchasedate(purchasedate);
			
			ar.add(cd);
			
		}
		
//		System.out.println(cd.getAmount());
//		System.out.println(cd.getBookname());
//		System.out.println(cd.getLocation());
//		System.out.println(cd.getPurchasedate());
		
//		Using Jackson to convert java object into json file
//		maven dependencies: jackson core , jackson databind , jackson annotations 
		
		for(int i=0 ; i<ar.size();i++) {
			
			
			java.io.File jsonfile= new java.io.File("E:\\Projects\\SDETRetriveData\\custinfo"+i+".json");
			ObjectMapper om=new ObjectMapper();
			om.writeValue(jsonfile,ar.get(i));
		}
		
		
		
		System.out.println("Done!!");
		
		
		
		con.close();
		

	}

}
