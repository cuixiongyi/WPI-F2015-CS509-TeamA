package com.wpi.cs509.teamA.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will handle all the points those are in the same position but
 * indifferent map
 * 
 * The class will get data from database via using method initDupEntranceMap(),
 * then use method getDupEntranceMap() you will be able to get all the point
 * pairs in a map.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class DupEntranceMap {

	/**
	 * a DupEntranceMap instance
	 */
	private static DupEntranceMap dupEntranceMap;
	/**
	 * a map that contains all the data from database on the duplicate points in
	 * different map
	 */
	private Map<Integer, DupEntranceMap.SamePoint> resultMap = new HashMap<Integer, DupEntranceMap.SamePoint>();

	/**
	 * constructor, will access the database get all the information related to
	 * the duplicate points in different map
	 */
	private DupEntranceMap() {

	}

	/**
	 * Using singleton here, so the database will be only accessed once. Save
	 * I/O operation time.
	 * 
	 * @return a DupEntranceMap instance
	 */
	public static DupEntranceMap initDupEntranceMap() {

		if (dupEntranceMap == null) {
			dupEntranceMap = new DupEntranceMap();
		}

		return dupEntranceMap;

	}

	/**
	 * 
	 * @return a map whose key is the id of the node, and the value is a
	 *         DupEntranceMap.SamePoint class which contains all the information
	 *         from database
	 */
	public Map<Integer, DupEntranceMap.SamePoint> getDupEntranceMap() {
		return resultMap;
	}

	/**
	 * This class represents a record from database that two same point in
	 * different map and their distance
	 *
	 */
	class SamePoint {

		private int firstNodeId;
		private int firstNodeMapId;
		private int secondNodeId;
		private int secondNodeMapId;
		private int distance;

		public SamePoint(int firstNodeId, int secondNodeId, int distance) {
			this.firstNodeId = firstNodeId;
			this.secondNodeId = secondNodeId;
			this.distance = distance;
		}

		/**
		 * @return the firstNodeId
		 */
		public int getFirstNodeId() {
			return firstNodeId;
		}

		/**
		 * @param firstNodeId
		 *            the firstNodeId to set
		 */
		public void setFirstNodeId(int firstNodeId) {
			this.firstNodeId = firstNodeId;
		}

		/**
		 * @return the firstNodeMapId
		 */
		public int getFirstNodeMapId() {
			return firstNodeMapId;
		}

		/**
		 * @param firstNodeMapId
		 *            the firstNodeMapId to set
		 */
		public void setFirstNodeMapId(int firstNodeMapId) {
			this.firstNodeMapId = firstNodeMapId;
		}

		/**
		 * @return the secondNodeId
		 */
		public int getSecondNodeId() {
			return secondNodeId;
		}

		/**
		 * @param secondNodeId
		 *            the secondNodeId to set
		 */
		public void setSecondNodeId(int secondNodeId) {
			this.secondNodeId = secondNodeId;
		}

		/**
		 * @return the secondNodeMapId
		 */
		public int getSecondNodeMapId() {
			return secondNodeMapId;
		}

		/**
		 * @param secondNodeMapId
		 *            the secondNodeMapId to set
		 */
		public void setSecondNodeMapId(int secondNodeMapId) {
			this.secondNodeMapId = secondNodeMapId;
		}

		/**
		 * @return the distance
		 */
		public int getDistance() {
			return distance;
		}

		/**
		 * @param distance
		 *            the distance to set
		 */
		public void setDistance(int distance) {
			this.distance = distance;
		}

	}

}
