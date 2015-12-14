package com.wpi.cs509.teamA.dao;

import com.wpi.cs509.teamA.bean.NodeInformation;

public interface NodeInformationDao {
	/**
	 * get all user accounts in the database
	 * 
	 * @return a list of user accounts
	 * 
	 */
	public void saveNodeInformation(NodeInformation nodeInfo);
}
