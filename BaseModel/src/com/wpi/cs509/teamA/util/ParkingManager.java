package com.wpi.cs509.teamA.util;

import java.util.TimerTask;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.ViewManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class ParkingManager {
	private static Timer timer = new Timer();
	static private MainModel model = null;
	static ArrayList<String> parkingName;

	public ParkingManager() {
		timer.schedule(new UpdateParkingLot(), 1000, 2000);
		parkingName = new ArrayList<String>();
		for (Node n : Database.getAllNodeListFromDatabase()) {
			if (n.getNodeType() == NodeType.PARKING) {
				parkingName.add(n.getName());
			}
		}
	}

	public static void updateParkingLot() {
		HashMap<String, Integer> parking = new HashMap<String, Integer>();
		for(String park : parkingName){
			parking.put(park, randomNumber());
		}
		model.setParkingAvilibility(parking);
	}

	public static int randomNumber() {
		double d = Math.random();
		int i = (int) (d * 30);
		return i;
	}

	@SuppressWarnings("static-access")
	public void setModel(MainModel mainModel) {
		// TODO Auto-generated method stub
		this.model = mainModel;
	}
}

class UpdateParkingLot extends TimerTask {

	public void run() {
		ParkingManager.updateParkingLot();
		System.out.println("hh");
		ViewManager.updateView();

	}
}