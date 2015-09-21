package com.wpi.cs509.teamA.findRoute;

import java.util.List;
import com.wpi.cs509.teamA.entities.Node;

public interface FindRoute {
	public List<Node> findRouteBetweenSimpleTwoNodes(Node startNode, Node endNode);
}
