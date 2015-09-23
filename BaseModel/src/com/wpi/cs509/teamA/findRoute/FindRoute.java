package com.wpi.cs509.teamA.findRoute;

import java.util.List;
import com.wpi.cs509.teamA.entities.Node;

public interface FindRoute {
	public List<Node> findRouteSameMap(Node startNode, Node endNode);
	
	// the return type of different path find will be different?
	// three types? Or just 2 types?
	public List<List<Node>> findRouteMultipleMap(Node startNode, Node endNode);
	
}
