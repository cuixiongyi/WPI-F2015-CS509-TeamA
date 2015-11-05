package com.wpi.cs509.teamA.dao;

import java.util.Set;

import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.util.Coordinate;

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
	 * TODO: Decide the error handle..
	 * 
	 * @param nodeRelation
	 * @return
	 */
	public Set<NodeRelation> insertMultipleEdges(Set<NodeRelation> nodeRelation);

	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return true if an node relation exist, false not exists
	 */
	public boolean checkNodeRelationInDBById(int id1, int id2);
	
	/**
	 * 
	 * @param coordinate
	 * @return
	 */
	public int checkNodeInDBByCoordinate(Coordinate coordinate);

	/**
	 * Check if an relation has already exists in DB
	 * 
	 * @param nodeRelation
	 *            an array of node relations
	 * 
	 * @return 0 no relation in exists in DB n the first relation that detects
	 *         exists in DB
	 */
	public int checkNodeRelationInDBByNodeRelation(NodeRelation[] nodeRelation);

	/**
	 * 
	 * Get all the node relations
	 * 
	 * @return a set of NodeRelation instance querying from relation table
	 */
	public Set<NodeRelation> getAllNodeRelations();

	/**
	 * 
	 * This method is for algorithm, the Edge structure is different from node
	 * relation.
	 * 
	 * @return A set of Edge instances
	 */
	public Set<Edge> getAllEdges();

}
