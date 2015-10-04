package com.wpi.cs509.teamA.misc;

public enum NodeType {
	
	OFFICE("office"),
	CLASSROOM("classroom"),
	MEETINGROOM("meetingroom"),
	RESTROOM("restroom"),
	PATHDOT("pathdot"),
	LAB("lab"),
	PARKING("parkinglot");
	
	/** String representation of the node's type */
	private String nodeType;
	
	/**
	 * Constructor
	 * @param nodeType The type of node cooresponding to the node instance
	 */ 
	private NodeType(String nodeType){
		this.nodeType = nodeType;
	}
	
	/**
	 * @todo: Function header comments
	 */ 
	public String getNode(NodeType node){
		return this.nodeType;
	}
	
}
