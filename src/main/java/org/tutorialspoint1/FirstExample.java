package org.tutorialspoint1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FirstExample extends DbInfo{

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt =  conn.createStatement();
			


			// Set auto-commit to false
			conn.setAutoCommit(false);

			// Create SQL statement
			String SQL = "INSERT INTO Employees ( first, last, age) " +
			             "VALUES('Zia', 'Ali', 30)";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement
//			String 
			SQL = "INSERT INTO Employees ( first, last, age) " +
			             "VALUES('Raj', 'Kumar', 35)";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement
//			String 
			SQL = "UPDATE Employees SET age = 35 " +
			             "WHERE id = 100";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create an int[] to hold returned values
			int[] count = stmt.executeBatch();

			//Explicitly commit statements to apply changes
			conn.commit();
			
			
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main
}//end FirstExample
