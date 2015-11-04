package com.wpi.cs509.teamA.dao;

import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;

public interface NodeDao {

	/**
	 * get count of nodes in the database
	 * 
	 * @return the number of nodes in the table node
	 * 
	 */
	public int getNodeNum();

	/**
	 * get count of neighbors
	 * 
	 * @return the number of neighbor nodes
	 */
	public int getNeighborsNum();
	
	/**
	 * Get all the initialized nodes
	 * 
	 * @return A set of initialized node object
	 */
	public Set<Node> getAllNodes();
	
	/**
	 * 
	 * Get the neighbor of a node
	 * 
	 * @param nodeId the id of the node that looking for neighbor
	 * 
	 * @return The neighbor node id and the distance
	 */
	public Map<Integer, Double> getNodeNeighbor(int nodeId);
	

	/**
	 * 
	 * insert nodes to the database
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param mapId
	 * @param classification
	 */
	public void saveNode(Node node);
	
	
	
	
}
