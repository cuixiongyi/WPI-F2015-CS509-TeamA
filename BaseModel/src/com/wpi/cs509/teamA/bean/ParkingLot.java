package com.wpi.cs509.teamA.bean;

/**
 * 
 * @author Team-A
 *
 */
public class ParkingLot {

	private Node node;

	private int avaiable;
	private int capacity;

	public ParkingLot(Node pnode) {
		node = pnode;
	}

	public synchronized int getAvaiable() {
		return avaiable;
	}

	public synchronized void setAvaiable(int avaiable) {
		this.avaiable = avaiable;
	}

	public synchronized int getCapacity() {
		return capacity;
	}

	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
