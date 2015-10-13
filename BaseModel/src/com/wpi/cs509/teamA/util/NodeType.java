package com.wpi.cs509.teamA.util;

/**
 * type of node definition
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */

public enum NodeType {

	UNDEFINED("undefined"), PATHDOT("pathdot"), OFFICE("office"), CLASSROOM("classroom"), MEETINGROOM("meetingroom"), RESTROOM(
			"restroom"), LAB("lab"), PARKING("parkinglot");

	/** String representation of the node's type */
	private String nodeType;

	/**
	 * Constructor
	 * 
	 * @param nodeType
	 *            The type of node corresponding to the node instance
	 */
	private NodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * get the the String field of the Node instance
	 * @param node Node instance
	 * @return the String field of the Node instance
	 */
	public String getNode(NodeType node) {
		return this.nodeType;
	}

}
