package com;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    //A common method to connect to the DB
	    private Connection connect() 
	        { 
                Connection con = null; 
                try
	        { 
	            Class.forName("com.mysql.jdbc.Driver"); 
	    
	    //Provide the correct details: DBServer/DBName, username, password 
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db", "root", ""); 
    } 
    catch (Exception e) 
	        {e.printStackTrace();} 
    return con; 
    } 
    
    public String addpayment(String paymentID, String billID, String payDate,Double amount,
		    String cardHolderName, String cardNo, String month, String year,String ccvNo ) 
    { 
        String output = ""; 
            try
            { 
                Connection con = connect(); 
                if (con == null) 
                    {	
                        return "Error while connecting to the database for inserting.";
                    } 
                
                // create a prepared statement
                String query = "insert into payments(`paymentID`,`billID`,`payDate`,`amount`,`cardHolderName`,`cardNo`,`month`,`year`,`ccvNo`)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setInt(1, 0); 
                preparedStmt.setString(2, billID); 
                preparedStmt.setString(3, payDate);
                preparedStmt.setDouble(4, amount);
                preparedStmt.setString(5, cardHolderName); 
                preparedStmt.setString(6, cardNo); 
                preparedStmt.setString(7, month);  
                preparedStmt.setString(8, year); 
                preparedStmt.setString(9, ccvNo);  
                
              

                // execute the statement
                preparedStmt.execute(); 
                con.close(); 
                //output = "New bill Create successfully"; 
                
                String newBills = readPayment();
                output = "{\"status\":\"success\", \"data\": \"" + 
           			 newBills + "\"}"; 
                
            } 
            catch (Exception e) 
            { 
//            output = "Error while creating the bill"; 
            output = "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 
    
    public String readPayment() 
    { 
    String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
        {
        	return "Error while connecting to the database for reading."; } 
        // Prepare the html table to be displayed
        output = "<table border='1'><tr>"
        		+ "<th>Payment ID</th>"
        		+ "<th>Bill ID</th>" +
                "<th>Payment Date</th>" + 
                "<th>Amount</th>"+
                "<th>Cardholder Name</th>" + 
                "<th>Card No</th>" +
                "<th>Month</th>" + 
                "<th>Year</th>"+
                "<th>CcvNo</th> </tr>"; 
        
        /**Use join query connect tree table */
        String query = "SELECT * FROM payments" ; 
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query); 
        // iterate through the rows in the result set
        while (rs.next()) 
        { 
            String paymentID = rs.getString("paymentID"); 
            String billID = rs.getString("billID"); 
            String payDate = rs.getString("payDate"); 
            String amount  = rs.getString("amount");
            String cardHolderName = rs.getString("cardHolderName"); 
            String cardNo = rs.getString("cardNo"); 
            String month = rs.getString("month"); 
            String year = rs.getString("year"); 
            String ccvNo = rs.getString("ccvNo");
           
            // Add into the html table
            output += "<tr><td>" + paymentID + "</td>"; 
            output += "<td>" + billID + "</td>"; 
            output += "<td>" + payDate + "</td>";
            output += "<td>" + amount + "</td>"; 
            output += "<td>" + cardHolderName + "</td>"; 
            output += "<td>" + cardNo + "</td>"; 
            output += "<td>" + month + "</td>"; 
            output += "<td>" + year + "</td>";
            output += "<td>" + ccvNo + "</td>"; 
 
            		 
            // buttons
        			output += "<td><input name='btnUpdate' "
        					+ "type='button' value='Update' "
        					+ " class='btnUpdate btn btn-secondary'></td>"
        					+ "<td><input name='btnRemove' "
        					+ "type='button' value='Remove' "
        					+ "class='btnRemove btn btn-danger' "
        					+ "data-id='"
        			 + paymentID + "'>" + "</td></tr>";
        } 
        con.close(); 
        // Complete the html table
        output += "</table>"; 
    } 
	catch (Exception e) 
	{ 
		output = "Error while reading the Payments."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	} 
    
   
  
    
    public String updatepayment(String paymentID, String billID, String payDate,Double amount, String cardHolderName,
    String cardNo, String month, String year, String ccvNo)  
    
    { 
        String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
    { 
	return "Error while connecting to the database for updating."; } 
    // create a prepared statement
    String query = "UPDATE payments SET billID=?,payDate=?"
    		+ ",amount=?,cardHolderName=?,cardNo=?,month=?,"
    		+ "year=? ,ccvNo=? WHERE paymentID=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
        // binding values
    
    
    preparedStmt.setString(1, billID); 
    preparedStmt.setString(2, payDate);
    preparedStmt.setDouble(3, amount);
    preparedStmt.setString(4, cardHolderName); 
    preparedStmt.setString(5, cardNo); 
    preparedStmt.setString(6, month);  
    preparedStmt.setString(7, year); 
    preparedStmt.setString(8, ccvNo); 
    preparedStmt.setInt(9, Integer.parseInt(paymentID));
    
       

    
    // execute the statement
    preparedStmt.execute(); 
    con.close(); 
    String newAppointment = readPayment();
	output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
}
catch (Exception e)
{
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the Payment details. \"}";
	System.err.println(e.getMessage());
}

return output;
} 

    
    public String deletePayment(String paymentID) 
    { 
	    String output = ""; 
	    try
	    { 
	    	Connection con = connect(); 
		    if (con == null) 
		    	{return "Error while connecting to the database for deleting."; } 
			    // create a prepared statement
			    String query = "delete from payments where paymentID=?"; 
			    PreparedStatement preparedStmt = con.prepareStatement(query); 
			    // binding values
			    //preparedStmt.setString(1, paymentID);
			    preparedStmt.setInt(1, Integer.parseInt(paymentID));
			    // execute the statement
			    preparedStmt.execute(); 
			    con.close(); 
			 
	   
    String newAppointment = readPayment();
	output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
}
catch (Exception e)
{
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the paymente details. \"}";
	System.err.println(e.getMessage());
}

return output;
}

}

