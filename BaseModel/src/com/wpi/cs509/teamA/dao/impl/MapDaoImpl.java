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
			String selectAllMaps = "SELECT id, name, image_name,scale,map_abbr FROM routefinder.map;";
			pstmt = conn.prepareStatement(selectAllMaps);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				GeneralMap maps = new GeneralMap();
				maps.setMapId(resultSet.getInt("id"));
				maps.setMapName(resultSet.getString("name"));
				maps.setImageName(resultSet.getString("image_name"));
				maps.setScale(resultSet.getFloat("scale"));
				maps.setMapAbbrName(resultSet.getString("map_abbr"));
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
			String selectAllMaps = "SELECT distinct id FROM routefinder.map;";
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

	@Override
	public void saveMap(String mapName, String mapAbbrName,String mapPathName, double mapScale) {
		// TODO Auto-generated method stub
		try {
			String insertMapToDB = "INSERT INTO routefinder.map (name, image_name, scale, map_abbr) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertMapToDB);
			pstmt.setString(1, mapName);
			pstmt.setString(2, mapPathName );
			pstmt.setDouble(3, mapScale);
			pstmt.setString(4, mapAbbrName);
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
