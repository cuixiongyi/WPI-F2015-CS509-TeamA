package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.UserAccountDao;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.JdbcConnect;
import com.wpi.cs509.teamA.util.NodeType;
import com.wpi.cs509.teamA.util.UIDataBuffer;

public class UserAccountDaoImpl implements UserAccountDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public UserAccountDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<UserAccount> getAllUserAccounts() {
					/*
		TODO this is a hack
		ResultSet resultSet = null;
		List<UserAccount> res = new ArrayList<UserAccount>();
		
		try {
			String selectAllUsers = "SELECT id, username,password, isAdmin,email FROM routefinder.user_account;";
			pstmt = conn.prepareStatement(selectAllUsers);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				UserAccount userinfo = new UserAccount();
				userinfo.setId(resultSet.getInt("id"));
				userinfo.setUsername(resultSet.getString("username"));
				userinfo.setPassword(resultSet.getString("password"));
				userinfo.setAdmin(resultSet.getInt("isAdmin"));
				userinfo.setEmail(resultSet.getString("email"));
				// add history for this user
				userinfo.setHistory(this.getAllHistoryForUser(userinfo.getId()));
				res.add(userinfo);
			}
			return res;

		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
			JdbcConnect.connClose();
		}
		*/
			return null;

		}

	@Override
	public HashMap<String, Integer> getAllHistoryForUser(int user_id) {
		// TODO Auto-generated method stub

		ResultSet resultSet = null;
		Map<String,Integer> res = new HashMap<String,Integer>();
		try {
			String selectAllUsers = "SELECT historystring,count FROM routefinder.history where userid=?;";
			pstmt = conn.prepareStatement(selectAllUsers);
			pstmt.setInt(1, user_id);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				res.put(resultSet.getString("historystring"), resultSet.getInt("count"));
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}
		return null;
	}

	@Override
	public void addAccountToDatabase(UserAccount add_user) {
		try {
			String insertNodeToDB = "INSERT INTO routefinder.user_account (username,password, isAdmin,email)  VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertNodeToDB);
			pstmt.setString(1, add_user.getUsername());
			pstmt.setString(2, add_user.getPassword());
			pstmt.setInt(3, add_user.isAdmin() ? 1 : 0);
			pstmt.setString(4, add_user.getEmail());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}
	}

}