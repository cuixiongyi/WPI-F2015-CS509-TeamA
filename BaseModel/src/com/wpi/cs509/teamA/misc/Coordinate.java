package com.wpi.cs509.teamA.misc;

import com.wpi.cs509.teamA.entities.Node;

/**
 * the coordinate definition
 * @author CS 509-Team A 
 * @version Oct 5th
*/

public class Coordinate {
	
	/** x coordinate in the coordinate pair */
	private int x; 
	/** y coordinate in the coordinate pair */
	private int y;

	/**
	 * Default constructor
	 */ 
	public Coordinate(){
		
	}
	
	/**
	 * Constructor assigning x and y coordinates
	 * @param x The x coordinate for the coordinate pair
	 * @param y The y coordinate for the coordinate pair
	 */ 
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x coordinate of the coordinate pair
	 * @return x coordinate
	 */ 
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of the coordinate pair
	 * @param x The new x coordinate value
	 */ 
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y coordinate of the coordinate pair
	 * @return y coordinate
	 */ 
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of the coordinate pair
	 * @param y The new y coordinate value
	 */ 
	public void setY(int y) {
		this.y = y;
	}
	
}
