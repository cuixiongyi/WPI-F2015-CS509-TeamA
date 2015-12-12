package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Professor;
import com.wpi.cs509.teamA.dao.ProfessorDao;
import com.wpi.cs509.teamA.util.JdbcConnect;

public class ProfessorDaoImpl implements ProfessorDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ProfessorDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Professor> getAllProfessors() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<Professor> res = new ArrayList<Professor>();
		try {
			String selectAllProfessor = "SELECT professor,nodeid FROM routefinder.professor;";
			pstmt = conn.prepareStatement(selectAllProfessor);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String prof_name = resultSet.getString("professor");
				int node_id = resultSet.getInt("nodeid");
				res.add(new Professor(prof_name,node_id));
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
}
