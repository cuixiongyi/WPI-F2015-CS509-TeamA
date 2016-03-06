package com.wpi.cs509.teamA.persistence.dao.impl;

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

import com.wpi.cs509.teamA.exception.PwdIncorrectException;
import com.wpi.cs509.teamA.exception.UserAccountNotFoundException;
import com.wpi.cs509.teamA.persistence.bean.History;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.persistence.bean.UserAccount;
import com.wpi.cs509.teamA.persistence.dao.UserAccountDao;
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
				userinfo.setAdmin(resultSet.getBoolean("isAdmin"));
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

		return null;
	}

	@Override
	public List<History> getAllHistoryForUser(int user_id) {
		// TODO Auto-generated method stub

		ResultSet resultSet = null;
		List<History> res = new ArrayList<History>();
		try {
			String selectAllUsers = "SELECT nodeid,count,searchString FROM routefinder.history where userid=?;";
			pstmt = conn.prepareStatement(selectAllUsers);
			pstmt.setInt(1, user_id);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				res.add(new History(resultSet.getString("searchString"), resultSet.getInt("nodeid"),
						resultSet.getInt("count")));
			}
			return res;
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}
		return null;
	}

	public boolean checkUserNameInDatabase(String username) {
		ResultSet resultSet = null;
		try {
			String checkUserNameInDB = "SELECT count(id) FROM routefinder.user_account where username=?";
			pstmt = conn.prepareStatement(checkUserNameInDB);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt(1) == 0)
					return false;
			} else
				return true;
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}
		return true;
	}

	@Override
	public boolean addAccountToDatabase(UserAccount add_user) {
		try {
			if (checkUserNameInDatabase(add_user.getUsername())) {
				System.out.println("User exists");
				return false;
			}
			String insertNodeToDB = "INSERT INTO routefinder.user_account (username,password, isAdmin,email)  VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertNodeToDB);
			pstmt.setString(1, add_user.getUsername());
			pstmt.setString(2, add_user.getPassword());
			pstmt.setInt(3, add_user.isAdmin() ? 1 : 0);
			pstmt.setString(4, add_user.getEmail());
			pstmt.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}
		return false;
	}

	@Override
	public UserAccount loginAuthorization(String username, String password)
			throws UserAccountNotFoundException, PwdIncorrectException {
		ResultSet resultSet = null;
		try {
			String UserAccountsInDB = "SELECT id, username,password, isAdmin,email FROM routefinder.user_account where username=?";
			pstmt = conn.prepareStatement(UserAccountsInDB);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				UserAccount userinfo = new UserAccount();
				userinfo.setId(resultSet.getInt("id"));
				userinfo.setUsername(resultSet.getString("username"));
				userinfo.setPassword(resultSet.getString("password"));
				if (!userinfo.getPassword().equals(password)) {
					throw new PwdIncorrectException();
				}
				userinfo.setAdmin(resultSet.getBoolean("isAdmin"));
				userinfo.setEmail(resultSet.getString("email"));
				// add history for this user
				userinfo.setHistory(this.getAllHistoryForUser(userinfo.getId()));
				return userinfo;
			} else {
				// System.out.println("We cannot find this username, please sign
				// up first");
				throw new UserAccountNotFoundException();
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
			JdbcConnect.connClose();
		}
		return null;
	}

	public boolean checkHistoryInDatabase(int userid,int nodeid,String searchString){
		ResultSet resultSet = null;
		try {
			String checkUserNameInDB = "SELECT * FROM routefinder.history where userid=? and nodeid=? and searchString=?";
			pstmt = conn.prepareStatement(checkUserNameInDB);
			pstmt.setInt(1, userid);
			pstmt.setInt(2, nodeid);
			pstmt.setString(3, searchString);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
					return true;
			} else
				return false;
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}
		return false;
	}
	@Override
	public void saveSearchHistoryToDatabase(UserAccount user) {
		try {
			// delete all existing history for this user
			String deleteHistoryFromDB = "delete from routefinder.history where userid=?";
			pstmt = conn.prepareStatement(deleteHistoryFromDB);
			pstmt.setInt(1, user.getId());
			pstmt.executeUpdate();
			conn.commit();

			// save new history for this user
			for (History history : user.getHistory()) {
				if(!checkHistoryInDatabase(user.getId(),history.getNodeid(),history.getHistoryStr())){
				String insertHistoryToDB = "INSERT INTO routefinder.history (userid,count, nodeid, searchString)  VALUES (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(insertHistoryToDB);
				pstmt.setInt(1, user.getId());
				pstmt.setInt(2, history.getCount());
				pstmt.setInt(3, history.getNodeid());
				pstmt.setString(4, history.getHistoryStr());
				pstmt.executeUpdate();
				conn.commit();
				}
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}
	}

}
