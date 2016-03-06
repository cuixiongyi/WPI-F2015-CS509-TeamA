package com.wpi.cs509.teamA.persistence.dao;

import com.wpi.cs509.teamA.persistence.bean.NodeInformation;

public interface NodeInformationDao {
	/**
	 * get all user accounts in the database
	 * 
	 * @return a list of user accounts
	 * 
	 */
	public void saveNodeInformation(NodeInformation nodeInfo);
}
