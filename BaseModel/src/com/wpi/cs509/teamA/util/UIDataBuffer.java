package com.wpi.cs509.teamA.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;

public class UIDataBuffer {
	
	private Map<Integer, List<Node>> allNodesInDB;
	
	static Map<Integer, List<Node>> getAllNodes(){
		// get node from db
		// allNodesInDB = new NodeDaoImpl().getAllNodes();
		
		// insert data
		
		return null;
	}
	
	static Set<NodeRelation> getAllEdges(){
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		return nrd.getAllNodeRelations();
	}

}
