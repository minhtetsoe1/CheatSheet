package spring.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	static Connection con = null;
	public static Connection getConnection() {
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ojt","root","root");
			 System.out.println("con:"+con);
			 } catch (ClassNotFoundException e) {
			 System.out.println("Driver:" + e.getMessage());
			 } catch (SQLException e) {
			 System.out.println("Error:"+e.getMessage()); 
			}
		return con;
	}

}
