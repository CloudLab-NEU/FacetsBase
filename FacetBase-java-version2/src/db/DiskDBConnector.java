package db;

import java.sql.*;

public class DiskDBConnector {
	
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			conn.setAutoCommit(false);
			System.out.println("Opened disk database successfully");
			return conn;
		}
		catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
}
