package com.wpi.cs509.teamA.bean;

import com.wpi.cs509.teamA.util.Coordinate;

/**
 * 
 * This class is different from the class edge in the strategy package. This
 * class is for persistence to record the relationship of two node. Logically,
 * it is the edge.
 * 
 * @author JLou
 *
 */
public class NodeRelation {

	// must initialize the Coordinate here..
	private Coordinate firstNode = new Coordinate();
	private Coordinate secondNode = new Coordinate();

	/**
	 * @return the firstNode
	 */
	public Coordinate getFirstNode() {
		return firstNode;
	}

	/**
	 * @param firstNode
	 *            the firstNode to set
	 */
	public void setFirstNode(Coordinate firstNode) {
		this.firstNode = firstNode;
	}

	/**
	 * @return the secondNode
	 */
	public Coordinate getSecondNode() {
		return secondNode;
	}

	/**
	 * @param secondNode
	 *            the secondNode to set
	 */
	public void setSecondNode(Coordinate secondNode) {
		this.secondNode = secondNode;
	}

}
