package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.MapDao;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.JdbcConnect;
import com.wpi.cs509.teamA.util.NodeType;
import com.wpi.cs509.teamA.util.UIDataBuffer;

public class MapDaoImpl implements MapDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public MapDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<GeneralMap> getAllMaps() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<GeneralMap> res = new ArrayList<GeneralMap>();
		try {
			String selectAllMaps = "SELECT id, name, image_name,scale FROM RouteFinder.map;";
			pstmt = conn.prepareStatement(selectAllMaps);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				GeneralMap maps = new GeneralMap();
				maps.setMapId(resultSet.getInt("id"));
				maps.setMapName(resultSet.getString("name"));
				//maps.setMapImgPath(resultSet.getString("image_name"));
				maps.setDisplayScale(resultSet.getFloat("scale"));
				res.add(maps);
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

	public List<Integer> getAllMapIds() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<Integer> res = new ArrayList<Integer>();
		try {
			String selectAllMaps = "SELECT distinct id FROM RouteFinder.map;";
			pstmt = conn.prepareStatement(selectAllMaps);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				res.add(resultSet.getInt("id"));
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