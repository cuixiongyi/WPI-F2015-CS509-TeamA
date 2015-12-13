package com.wpi.cs509.teamA.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * Helper class to connect to the database
 * 
 * @author CS 509-Team A
 *
 */
public class JdbcConnect {

	// TODO: Make this in the file
	private static String url = "jdbc:mysql://localhost:3306/routefinder";
	private static String password = "hammer";

	//private static String url = "jdbc:mysql://130.215.28.216:3306/routefinder";
	//private static String password = "hammer";

	private static String user = "root";
	private static Connection conn = null;

	private JdbcConnect() {

	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {

		if (conn == null || conn.isClosed()) {

			conn = DriverManager.getConnection(url, user, password);
			// should commit
			conn.setAutoCommit(false);
		}

		return conn;

	}

	// ResultSet and Statement closed together
	public static void resultClose(ResultSet rs, Statement st) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void connClose() {
		try {
			if (conn != null || !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
