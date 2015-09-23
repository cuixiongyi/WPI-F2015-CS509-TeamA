package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.entities.Node;

public class InteractWithFrontEnd {
	
	private String startNode;
	private String endNode;
	
	public InteractWithFrontEnd(){
		
	}
	
	public Node getParameters(String fieldName){
		if(fieldName == "startNode"){
			
		}else if(fieldName == "endNode"){
			
		}
		
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

}
