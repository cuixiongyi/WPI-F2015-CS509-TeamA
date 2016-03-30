package com.wpi.cs509.teamA.strategy.datastructure;

public class endNodeOrder {

	private double dist;
	private int id;
	private endNodeOrder previous;

	public endNodeOrder() {

	}

	public endNodeOrder(int id) {
		this.id = id;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public endNodeOrder getNext() {
		return previous;
	}

	public void setNext(endNodeOrder previous) {
		this.previous = previous;
	}

	public int getId() {
		return id;
	}

}
