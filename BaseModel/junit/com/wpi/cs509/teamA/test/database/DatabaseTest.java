package com.wpi.cs509.teamA.test.database;

import java.util.List;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.DupEntranceMapDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.strategy.impl.Graph;

/**
 * This is General database test 1. Test Connection.. 2.
 * 
 * @author CS 509-Team A
 *
 */
public class DatabaseTest {

	/**
	 * 
	 * Test the connection will not repeat connect if the connection has already
	 * been built
	 * 
	 */
	@Test
	public void testConnection() {
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		NodeRelationDao nrd1 = new NodeRelationDaoImpl();
		NodeRelationDao nrd2 = new NodeRelationDaoImpl();
	}

}
