package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String code, String name, String Address, String PhoNo)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into hospitals(`HospitalID`,`HospitalCode`,`HospitalName`,`HospitalAddress`,`HospitalPhoNo`)" + " values (?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, code);    
			preparedStmt.setString(3, name);    
			preparedStmt.setString(4, Address);    
			preparedStmt.setString(5, PhoNo);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospital = readHospital(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
					newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readHospital()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Hospital Code</th><th>Hospital Name</th><th>Hospital Address</th><th>Hospital Phone No</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospitals";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String HospitalID = Integer.toString(rs.getInt("HospitalID"));
				 String HospitalCode = rs.getString("HospitalCode");
				 String HospitalName = rs.getString("HospitalName");
				 String HospitalAddress = rs.getString("HospitalAddress");
				 String HospitalPhoNo = Integer.toString(rs.getInt("HospitalPhoNo"));
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidHospitalIDUpdate\' name=\'hidHospitalIDUpdate\' type=\'hidden\' value=\'" + HospitalID + "'>" 
							+ HospitalCode + "</td>";      
				output += "<td>" + HospitalName + "</td>";     
				output += "<td>" + HospitalAddress + "</td>";     
				output += "<td>" + HospitalPhoNo + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='" + HospitalID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the items.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateHospital(String ID, String Code, String Name, String Address, String PhoNo)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE hospitals SET HospitalCode=?, HospitalName=?,HospitalAddress=?,HospitalPhoNo=? WHERE HospitalID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, Code);
			 preparedStmt.setString(2, Name);
			 preparedStmt.setString(3, Address);
			 preparedStmt.setString(4, PhoNo);
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospital = readHospital();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					              newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteHospital(String HospitalID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from hospitals where HospitalID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(HospitalID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospital = readHospital();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					       newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the item.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
} 


