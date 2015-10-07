package com.wpi.cs509.teamA.findRoute;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.entities.Node;

/**This is the interface for different path finding algorithm
 * @author CS 509-Team A 
 * @version Oct 5th
 */
public interface FindRoute {
	/**find route on the same map
	 * @param startNode  the start node
	 * @param endNode the destination node
	 * return List<Node>  the route defined by a list of nodes*/
	public Map<Integer, List<Node>> findRouteSameMap(Node startNode, Node endNode);
	
	// the return type of different path find will be different?
	// three types? Or just 2 types?
	/**find route on different map
	 * @param startNode  the start node
	 * @param endNode the destination node
	 * return Map<Integer, List<Node>>, key is the map id,
	 * and the value is the route on that map, which is defined by a list of nodes
	 */
	public Map<Integer, List<Node>> findRouteMultipleMap(Node startNode, Node endNode);
	
}
