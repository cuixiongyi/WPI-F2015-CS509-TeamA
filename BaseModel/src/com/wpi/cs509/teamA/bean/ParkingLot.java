package com.wpi.cs509.teamA.bean;

/**
 * Created by cuixi on 12/10/2015.
 */
public class ParkingLot {
    private Node node = null;


    private int avaiable = 0;
    private int capacity = 0;


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
