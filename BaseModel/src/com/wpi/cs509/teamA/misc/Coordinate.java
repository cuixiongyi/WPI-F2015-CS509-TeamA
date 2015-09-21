package com.wpi.cs509.teamA.misc;

import com.wpi.cs509.teamA.entities.Node;

public class Coordinate {
	
	private int x;
	private int y;

	public Coordinate(){
		
	}
	
	// calculate the distance between this node and the anotherNode
	public void getDistance(Node anotherNode){
		
		// x = this.x - anotherNode.getLocation().getX();
		// same to y
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
