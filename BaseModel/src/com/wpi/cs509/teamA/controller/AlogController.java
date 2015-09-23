package com.wpi.cs509.teamA.controller;

import java.util.List;

import com.wpi.cs509.teamA.entities.Node;

public class AlogController {
	
	private String startNode;
	private String endNode;
	
	public AlogController(){
		
	}
	
	public AlogController(String from, String to){
		
		this.startNode = from;
		this.endNode = to;		
	}
	
	public List<Node> getRoute(){
		Node fromNode = getNodeFromName(startNode);
		Node toNode = getNodeFromName(endNode);
		String startMapId = fromNode.getMapId();
		String endMapId = toNode.getMapId();
		// decide which algorithm to use
		
		// walking through the same map
		if(startMapId == endMapId){
			AlogController ac= new FindRouteSameMap();
			List<Node> result = ac.pathFinding();
			return result;
			
		}else if(startMapId != endMapId && (startMapId == "0" || endMapId == "0")){ // campus to building
			
		}else{ // building to building
			
		}
		
		return null;
	}
	
	public List<Node> pathFinding(){
		System.out.println("you must specify a implement class!");
		return null;
	}
	
	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}
	
	private Node getNodeFromName(String nodeName){
		
		// use node name to find the Node we need
		
		return null;
		
	}
	
}
