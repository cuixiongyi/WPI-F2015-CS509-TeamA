package com.wpi.cs509.teamA.test.database;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.util.NodeType;

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
	public void saveNodeTest() {

		Node node = new Node();
		node.getLocation().setX(10);
		node.getLocation().setY(20);
		node.setMapId(1);
		node.setName("Jie's Office");
		node.setNodeType(NodeType.OFFICE);
		NodeDao nd = new NodeDaoImpl();
		nd.saveNode(node);

	}
	
	@Test
	public void getNodeFromIdTest(){
		
		NodeDao nd = new NodeDaoImpl();
		System.out.println(nd.getNodeFromId(40));
		
	}
	
	@Test
	public void getNodeFromIdsTest(){
		
		NodeDao nd = new NodeDaoImpl();
		// nd.getNodeFromIds(nodeIds)
		
	}

}
