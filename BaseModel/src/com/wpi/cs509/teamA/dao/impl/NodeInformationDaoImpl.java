package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wpi.cs509.teamA.bean.Major;
import com.wpi.cs509.teamA.bean.NodeInformation;
import com.wpi.cs509.teamA.bean.OtherFeature;
import com.wpi.cs509.teamA.bean.Professor;
import com.wpi.cs509.teamA.dao.MajorDao;
import com.wpi.cs509.teamA.dao.NodeInformationDao;
import com.wpi.cs509.teamA.dao.OtherFeatureDao;
import com.wpi.cs509.teamA.dao.ProfessorDao;
import com.wpi.cs509.teamA.util.JdbcConnect;

public class NodeInformationDaoImpl implements NodeInformationDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public NodeInformationDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveNodeInformation(NodeInformation nodeInfo) {
		// save majors
		System.out.println("Adding majors.....");
		System.out.println("majors: "+ nodeInfo.getMajor().size());
		if ((nodeInfo.getMajor() != null) && (nodeInfo.getMajor().size() != 0)) {
			MajorDao md = new MajorDaoImpl();
			for (Major major : nodeInfo.getMajor()) {
				md.saveMajors(major);
			}
			JdbcConnect.connClose();
		}
		System.out.println("Adding professors.....");
		System.out.println("Professor: "+nodeInfo.getProfessor().size());
		// save professors
		if((nodeInfo.getProfessor()!=null) &&(nodeInfo.getProfessor().size()!=0)){
			ProfessorDao pd = new ProfessorDaoImpl();
			for(Professor professor: nodeInfo.getProfessor()){
				pd.saveProfessor(professor);
			}
			JdbcConnect.connClose();
		}
		
		// save labels
		System.out.println("Adding labels.....");
		if ((nodeInfo.getLabels() != null) && (nodeInfo.getLabels().size() != 0)) {
			OtherFeatureDao ofd = new OtherFeatureDaoImpl();
			for (OtherFeature otherFeature : nodeInfo.getLabels()) {
				ofd.saveAllOtherFeatures(otherFeature);
			}
			JdbcConnect.connClose();
		}
		// save for later
		// save activities
	}

}
