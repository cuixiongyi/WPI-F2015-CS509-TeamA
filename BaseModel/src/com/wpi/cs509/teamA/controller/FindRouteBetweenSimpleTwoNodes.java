package com.wpi.cs509.teamA.controller;

import java.util.List;

import com.wpi.cs509.teamA.entities.Node;
import com.wpi.cs509.teamA.findRoute.FindRoute;
import com.wpi.cs509.teamA.findRouteImpl.FindRouteImpl;
import com.wpi.cs509.teamA.ui.InteractWithFrontEnd;

public class FindRouteBetweenSimpleTwoNodes extends InteractWithFrontEnd{

	// should get parameters from the front end
	// do what it should do to find a path
	Node startNode = new InteractWithFrontEnd().getParameters("");
	Node endNode = new InteractWithFrontEnd().getParameters("");
	FindRoute fr = new FindRouteImpl();
	// not correctly
	List<Node> result = fr.findRouteBetweenSimpleTwoNodes(startNode, endNode);
	// draw on the map
	
}
