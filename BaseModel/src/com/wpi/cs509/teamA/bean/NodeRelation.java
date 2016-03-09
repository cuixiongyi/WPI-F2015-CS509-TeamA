package com.wpi.cs509.teamA.bean;

import com.wpi.cs509.teamA.util.Coordinate;

/**
 * 
 * This class is different from the class edge in the strategy package. This
 * class is for persistence to record the relationship of two node. Logically,
 * it is the edge.
 * 
 * 
 * @author Team-A
 *
 */
public class NodeRelation {

	// must initialize the Coordinate here..
	private Coordinate firstNodeCoordinate = new Coordinate();
	private Coordinate secondNodeCoordinate = new Coordinate();
	/**
	 * @return the firstNodeCoordinate
	 */
	public Coordinate getFirstNodeCoordinate() {
		return firstNodeCoordinate;
	}
	/**
	 * @param firstNodeCoordinate the firstNodeCoordinate to set
	 */
	public void setFirstNodeCoordinate(Coordinate firstNodeCoordinate) {
		this.firstNodeCoordinate = firstNodeCoordinate;
	}
	/**
	 * @return the secondNodeCoordinate
	 */
	public Coordinate getSecondNodeCoordinate() {
		return secondNodeCoordinate;
	}
	/**
	 * @param secondNodeCoordinate the secondNodeCoordinate to set
	 */
	public void setSecondNodeCoordinate(Coordinate secondNodeCoordinate) {
		this.secondNodeCoordinate = secondNodeCoordinate;
	}

}
