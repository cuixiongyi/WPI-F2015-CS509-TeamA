package com.wpi.cs509.teamA.misc;

import java.util.Map;
/**
 * Details of the entrance map
 * @author CS 509-Team A 
 * @version Oct 5th
*/

public class EntranceMap {
	
	// data structure is not decided yet
	class EntrancePair{
		
		/** ID of the first entrance */
		String entranceId1;
		/** ID of the second entrance */
		String entranceId2;
		
		/**
		 * Deafult constructor
		 */ 
		public EntrancePair() {
			// TODO Auto-generated constructor stub
		}
		
		/**
		 * Constructor
		 * @param entranceId1 ID of the first entrance
		 * @param entranceId2 ID of the second entrance
		 */ 
		public EntrancePair(String entranceId1, String entranceId2){
			this.entranceId1 = entranceId1;
			this.entranceId2 = entranceId2;
		}
		
	}
	
	/** Representation of the same point in different maps */
	private Map<EntrancePair, Integer> entranceMap;
	
	/**
	 * Sets the entrance mapping for the entrance pair
	 * @param entranceId1 ID of the first entrance
	 * @param entranceId2 ID of the second entrance
	 */ 
	public void setEntranceMap(String entranceId1, String entranceId2){
		// calculate distance between two nodes
		// put the node pair and distance into the entranceMap
	}
	
	/**
	 * Gets the entrance mapping for the entrance pair
	 * @return entranceMap
	 */
	public Map<EntrancePair, Integer> getEntranceMap(){
		return entranceMap;
		
	}

}
