package com.wpi.cs509.teamA.misc;

public enum NodeType {
	
	OFFICE("office"),CLASSROOM("classroom"),MEETINGROOM("meetingroom"),RESTROOM("restroom"),
	PATHDOT("pathdot"),LAB("lab"),PARKING("parkinglot");
	
	private String nodeType;
	
	private NodeType(String nodeType){
		this.nodeType = nodeType;
	}
	
	// 
	public String getNode(NodeType node){
		return this.nodeType;
	}
	
}
