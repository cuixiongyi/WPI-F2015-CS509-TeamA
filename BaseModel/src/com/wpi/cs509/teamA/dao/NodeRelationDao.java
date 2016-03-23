package com.wpi.cs509.teamA.dao;

import java.util.List;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.strategy.datastructure.Edge;

public interface NodeRelationDao {

	/**
	 * 
	 * @return the number of NodeRelation in the relation table
	 */
	public int getNodeRelationNum();

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
	// public List<Edge> insertMultipleEdges(Set<Edge> nodeRelation);

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
	// public int checkNodeInDBByCoordinate(Coordinate coordinate);

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
	public List<Edge> getAllNodeRelationsForCurrentMap(int map_id);

	/**
	 * 
	 * This method is for algorithm, the Edge structure is different from node
	 * relation.
	 * 
	 * @return A set of Edge instances
	 */
	public List<Edge> getAllEdges();

	/**
	 * 
	 * This method is for algorithm, the Edge structure is different from node
	 * relation.
	 * 
	 * @return A set of Edge instances
	 */
	public List<Edge> getAllMapEdges();

	public boolean deleteOrAddEdge(Node n1, Node n2);
	
	public List<Integer> getMapRelationsNodeForOneMap(int map_id);
}
