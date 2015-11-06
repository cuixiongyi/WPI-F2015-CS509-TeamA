package com.wpi.cs509.teamA.test.database;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.NodeType;

public class NodeRelationDaoTest {
	
	@Test
	public void getAllEdgesTest(){
		Set<Edge> testSet = new HashSet<Edge>();
		
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		testSet = nrd.getAllEdges();
		System.out.println(testSet.toArray().length);
	}

	@Test
	public void insertMultipleEdgesTestReturnValue() {
		
		// create 4 new node
		Node node = new Node();
		node.getLocation().setX(30);
		node.getLocation().setY(30);
		node.setMapId(1);
		node.setName("Jie's Office");
		node.setNodeType(NodeType.OFFICE);
		NodeDao nd = new NodeDaoImpl();
		nd.saveNode(node);
		
		node.getLocation().setX(40);
		node.getLocation().setY(40);
		node.setMapId(1);
		node.setName("Matt's Office");
		node.setNodeType(NodeType.OFFICE);
		nd.saveNode(node);
		
		node.getLocation().setX(50);
		node.getLocation().setY(50);
		node.setMapId(1);
		node.setName("PK's Office");
		node.setNodeType(NodeType.OFFICE);
		nd.saveNode(node);
		
		node.getLocation().setX(60);
		node.getLocation().setY(60);
		node.setMapId(1);
		node.setName("Lin's Office");
		node.setNodeType(NodeType.OFFICE);
		nd.saveNode(node);
		
		

		Set<NodeRelation> testSet = new HashSet<NodeRelation>();
		
		//
		Coordinate firstNodeCoordinate = new Coordinate();
		firstNodeCoordinate.setX(30);
		firstNodeCoordinate.setY(30);
		Coordinate secondNodeCoordinate = new Coordinate();
		secondNodeCoordinate.setX(40);
		secondNodeCoordinate.setY(40);

		NodeRelation nr = new NodeRelation();
		nr.setFirstNodeCoordinate(firstNodeCoordinate);
		nr.setSecondNodeCoordinate(secondNodeCoordinate);		
		testSet.add(nr);
		
		//
		firstNodeCoordinate = new Coordinate();
		firstNodeCoordinate.setX(50);
		firstNodeCoordinate.setY(50);
		secondNodeCoordinate = new Coordinate();
		secondNodeCoordinate.setX(60);
		secondNodeCoordinate.setY(60);

		nr = new NodeRelation();
		nr.setFirstNodeCoordinate(firstNodeCoordinate);
		nr.setSecondNodeCoordinate(secondNodeCoordinate);
		testSet.add(nr);
		
		//
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		Set<NodeRelation> res = nrd.insertMultipleEdges(testSet);
		
		for (NodeRelation edge : res) {
			System.out.println("Exists Edge: " + edge.getFirstNodeCoordinate().getX() + " " + edge.getFirstNodeCoordinate().getY() + " "
					+ edge.getSecondNodeCoordinate().getX() + " " + edge.getSecondNodeCoordinate().getY());
		}

	}

}
