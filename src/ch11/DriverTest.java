package ch11;

import java.sql.*;

public class DriverTest {
	public static void main(String[] args) {

			Connection con;
		
			try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "3854");
				System.out.println("Success");
			} 
			catch (SQLException ex) {
				System.out.println("SQLE");
			}
			catch (Exception ex) {
				System.out.println("exception");
				
			}
			
		
	}
}
