package com.wpi.cs509.teamA.util;

/**
 * type of node definition
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */

public enum NodeType {

	UNDEFINED("UNDEFINED"), PATHNODE("PATHNODE"), OFFICE("OFFICE"), CLASSROOM("CLASSROOM"), MEETINGROOM("MEETINGROOM"), RESTROOM(
			"RESTROOM"), LAB("LAB"), PARKING("PARKING");

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
	@ Override
	public String toString() {
		return this.nodeType;
	}

}
