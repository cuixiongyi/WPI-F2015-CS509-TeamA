package com.wpi.cs509.teamA.dao.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.DupEntranceMapDao;

/**
 * The implementation class of DupEntranceMapDao
 * 
 * @author CS 509-Team A
 *
 */
public class DupEntranceMapDaoImpl implements DupEntranceMapDao {
	Connection con = null;

	public DupEntranceMapDaoImpl() {
		try {
			// connect to mysql Driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find the jdbc Driver");
			e.printStackTrace();
		}
		// connect to database
		String url = "jdbc:mysql://localhost:3306/RouteFinder";
		String username = "root";
		String password = "hammer";
		try {
			con = (Connection) DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
	}
	// initialize nodes at once
	Node [] initAllNodes(){
		Node [] allNodes = new Node[10000];
		return allNodes;
	}
	
	// initialize edges at once
	Edge [] initAllEdges(){
		Edge [] alledges = new Edge[10000];
		return alledges;
	}
	
}
