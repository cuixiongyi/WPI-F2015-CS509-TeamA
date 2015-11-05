package com.wpi.cs509.teamA.test.database;

import java.util.List;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.impl.DupEntranceMapDaoImpl;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.strategy.impl.Graph;

/**
 * This is General database test
 * 1. Test Connection..
 * 2. 
 * 
 * @author CS 509-Team A 
 *
 */
public class DatabaseTest {

	@Test
	public void testNodeInsert() {
		DupEntranceMapDaoImpl demp = new DupEntranceMapDaoImpl();
		demp.insertNodes("323", 4, 5, 1, "OFFICE");
	}
	
	@Test
	public void testEdgeInsert(){
		DupEntranceMapDaoImpl demp = new DupEntranceMapDaoImpl();
		demp.insertoneEdge(3, 4, 4, 5);;
	}

}
