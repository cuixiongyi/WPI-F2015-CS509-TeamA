package com.wpi.cs509.teamA.test.database;

import org.junit.Test;

import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;

public class NodeDaoTest {

	@Test
	public void getNodeNumTest() {
		NodeDao nd = new NodeDaoImpl();
		int result = nd.getNodeNum();
		System.out.println(result);
	}

	@Test
	public void getNeighborsNumTest() {

	}

	@Test
	public void getAllNodesTest() {

	}

	@Test
	public void getNodeNeighborTest() {

	}

	@Test
	public void saveNodesTest() {

	}

}
