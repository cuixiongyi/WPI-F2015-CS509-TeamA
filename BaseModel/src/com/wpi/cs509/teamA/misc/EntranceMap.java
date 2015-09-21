package com.wpi.cs509.teamA.misc;

import java.util.Map;

public class EntranceMap {
	
	// data structure is not decided yet
	class EntrancePair{
		
		String entranceId1;
		String entranceId2;
		
		public EntrancePair() {
			// TODO Auto-generated constructor stub
		}
		
		public EntrancePair(String entranceId1, String entranceId2){
			this.entranceId1 = entranceId1;
			this.entranceId2 = entranceId2;
		}
		
	}
	
	// same point in different map
	private Map<EntrancePair, Integer> entranceMap;
	
	// 
	public void setEntranceMap(String entranceId1, String entranceId2){
		// calculate distance between two nodes
		// put the node pair and distance into the entranceMap
	}
	
	public Map<EntrancePair, Integer> getEntranceMap(){
		return entranceMap;
		
	}

}
