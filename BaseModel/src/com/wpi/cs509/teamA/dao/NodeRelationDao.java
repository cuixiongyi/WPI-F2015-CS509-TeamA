package com.wpi.cs509.teamA.dao;

import java.util.Set;

import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.strategy.impl.Edge;

public interface NodeRelationDao {

	/**
	 * 
	 * Insert one edge to DB
	 * 
	 * @param nodeRelation
	 * @return
	 */
	public boolean insertOneEdge(NodeRelation nodeRelation);

	/**
	 * 
	 * Insert multiple edges to DB
	 * 
	 * @param nodeRelation
	 * @return
	 */
	public boolean insertMultipleEdges(NodeRelation[] nodeRelation);

	/**
	 * Check if an relation has already exists in DB
	 * 
	 * @param nodeRelation
	 *            an array of node relations
	 * 
	 * @return 0 no relation in exists in DB n the first relation that detects
	 *         exists in DB
	 */
	public int checkRelationInDB(NodeRelation[] nodeRelation);

	/**
	 * 
	 * @return A set of Edge instances
	 */
	public Set<Edge> getAllEdges();

}
