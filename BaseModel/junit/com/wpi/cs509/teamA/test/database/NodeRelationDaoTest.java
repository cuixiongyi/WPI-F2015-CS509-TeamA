package com.wpi.cs509.teamA.test.database;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.util.Coordinate;

public class NodeRelationDaoTest {

	@Test
	public void insertMultipleEdgesTestReturnValue() {

		Set<NodeRelation> testSet = new HashSet<NodeRelation>();
		
		//
		Coordinate firstNodeCoordinate = new Coordinate();
		firstNodeCoordinate.setX(202);
		firstNodeCoordinate.setY(100);
		Coordinate secondNodeCoordinate = new Coordinate();
		secondNodeCoordinate.setX(4);
		secondNodeCoordinate.setY(5);

		NodeRelation nr = new NodeRelation();
		nr.setFirstNodeCoordinate(firstNodeCoordinate);
		nr.setSecondNodeCoordinate(secondNodeCoordinate);		
		testSet.add(nr);
		
		//
		firstNodeCoordinate = new Coordinate();
		firstNodeCoordinate.setX(170);
		firstNodeCoordinate.setY(127);
		secondNodeCoordinate = new Coordinate();
		secondNodeCoordinate.setX(242);
		secondNodeCoordinate.setY(150);

		nr = new NodeRelation();
		nr.setFirstNodeCoordinate(firstNodeCoordinate);
		nr.setSecondNodeCoordinate(secondNodeCoordinate);
		testSet.add(nr);
		
		//
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		Set<NodeRelation> res = nrd.insertMultipleEdges(testSet);
		for (NodeRelation edge : res) {
			System.out.println(edge.getFirstNodeCoordinate().getX() + " " + edge.getFirstNodeCoordinate().getY() + " "
					+ edge.getSecondNodeCoordinate().getX() + " " + edge.getSecondNodeCoordinate().getY());
		}

	}

}
