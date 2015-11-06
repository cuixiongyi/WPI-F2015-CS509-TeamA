package com.wpi.cs509.teamA.util;

import java.sql.SQLException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * 
 * Helper class to connect to the database
 * 
 * @author CS 509-Team A
 *
 */
public class JdbcConnect {

	private static DataSource ds = null;
	private static Connection conn = null;

	private JdbcConnect() {

	}

	// 在静态代码块中创建数据库连接池
	static {
		try {
			// 加载dbcp.properties配置文件
			InputStream in = JdbcConnect.class.getClassLoader().getResourceAsStream("dbcp.properties");
			Properties prop = new Properties();
			prop.load(in);
			// 创建数据源
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {

		conn = ds.getConnection();
		conn.setAutoCommit(false);

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
		if (conn != null) {
			try {
				// 将Connection连接对象还给数据库连接池
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
