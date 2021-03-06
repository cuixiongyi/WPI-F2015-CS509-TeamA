package com.wpi.cs509.teamA.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;

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
	 * Get all the initialized nodes(current map)
	 * 
	 * @return A set of initialized node object
	 */
	public Set<Node> getAllNodesForCurrentMap();
	
	/**
	 * Get all the initialized nodes(for all maps)
	 * 
	 * @return A set of initialized node object
	 */
	public List<Node> getAllNodes();	
	
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
	 * Insert a node into database
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param mapId
	 * @param classification
	 */
	public void saveNode(Node node);
	
	/**
	 * Check if a node exists in the database..
	 * 
	 * @return
	 */
	public boolean checkNodeInDB(Node node);
	
	/**
	 * 
	 * @param nodeId
	 * @return the coordinate of that node
	 */
	public Coordinate getNodeCoordinateFromId(int nodeId);
	
	/**
	 * 
	 * @param nodeId
	 * @return
	 */
	public Node getNodeFromId(int nodeId);
	
	/**
	 * 
	 * @return
	 */
	public List<Node> getNodeFromIds(List<Integer> nodeIds);
	
	public boolean editNode(Node node_edit);
	
	public boolean deleteNode(Node node_del);
	
}
