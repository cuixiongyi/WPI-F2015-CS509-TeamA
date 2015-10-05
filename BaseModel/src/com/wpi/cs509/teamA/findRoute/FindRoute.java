package com.wpi.cs509.teamA.findRoute;
import java.util.List;
import com.wpi.cs509.teamA.entities.Node;
/**find the route on the same map
 * @author CS 509-Team A 
 * @version Oct 5th
 */
public interface FindRoute {
	/**find route on the same map
	 * @param startNode  the start node
	 * @param endNode the destination node
	 * return List<Node>  the route defined by a list of nodes*/
	public List<Node> findRouteSameMap(Node startNode, Node endNode);
	
	// the return type of different path find will be different?
	// three types? Or just 2 types?
	/**find route on different map
	 * @param startNode  the start node
	 * @param endNode the destination node
	 * return List<List<Node>>  the list of the route, each route defined by a node list
	 */
	public List<List<Node>> findRouteMultipleMap(Node startNode, Node endNode);
	
}
