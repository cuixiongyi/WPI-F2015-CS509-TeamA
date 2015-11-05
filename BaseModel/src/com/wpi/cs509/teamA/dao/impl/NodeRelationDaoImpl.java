package com.wpi.cs509.teamA.dao.impl;

import java.util.Set;

import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.strategy.impl.Edge;

public class NodeRelationDaoImpl implements NodeRelationDao{

	@Override
	public boolean insertOneEdge(NodeRelation nodeRelation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertMultipleEdges(NodeRelation[] nodeRelation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkRelationInDB(NodeRelation[] nodeRelation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Edge> getAllEdges() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
}
