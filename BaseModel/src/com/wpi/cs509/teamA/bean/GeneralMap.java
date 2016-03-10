package com.wpi.cs509.teamA.bean;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.ImageHelper;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;

/**
 * This is a class that defines all the map we want.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */

public class GeneralMap {

	/**
	 * the map id
	 */
	private int mapId;
	/**
	 * Map name
	 */
	private String mapName;
	private String mapAbbrName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	private String imageName;
	/**
	 * the map scale
	 */
	private float measureScale;

	// Refactor
	private String mapImgPath;

	private float displayScale = 1;

	private BufferedImage image;

	private List<Edge> BoundaryEdges = new ArrayList<>();

	/**
	 * Default constructor
	 */
	public GeneralMap() {

	}

	//// for algo testing
	public GeneralMap(int id, int measureScale) {
		this.mapId = id;
		this.measureScale = measureScale;
		this.imageName = "";
	}

	/**
	 * Constructor. The ProxyMap will use this constructor to create a new
	 * instance of the map, also use this instance's getAdjacencyMatrix() method
	 * to get the adjacency matrix. The constructor should be responsible for
	 * getting data from the database and then generate a adjacency matrix.
	 * 
	 * @param mapName
	 *            the name of the map in the database
	 */
	public GeneralMap(String mapName) {

		this.measureScale = 1.0f;

	}

	public void readImage() {

		// display the image. Note that " /" only works on UNIX
		this.mapImgPath = PaintHelperBasics.getUserDir() + this.imageName;
		try {
			image = ImageHelper.readImage(this.imageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the mapId
	 */
	public int getMapId() {
		return mapId;
	}

	/**
	 * @param mapId
	 *            the mapId to set
	 */
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName
	 *            the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapAbbrName() {
		return mapAbbrName;
	}

	public void setMapAbbrName(String mapAbbrName) {
		this.mapAbbrName = mapAbbrName;
	}

	/**
	 * @return the scale
	 */
	public float getScale() {
		return measureScale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(float scale) {
		measureScale = scale;
	}

	public String getMapImgPath() {
		return mapImgPath;
	}

	public BufferedImage getImage() {
		return image;
	}

	public float getDisplayScale() {
		return displayScale;
	}

	public void setDisplayScale(float displayScale) {
		this.displayScale = displayScale;
	}

	public List<Node> getNodes() {
		return Database.getAllNodesForCurrentMap(this.getMapId());
	}

	public List<Edge> getEdges() {
		return Database.getAllEdgesForCurrentMap(this.getMapId());
	}

	public List<Edge> getBoundaryEdges() {
		return BoundaryEdges;
	}

	public void setBoundaryEdges(List<Edge> boundryEdges) {
		BoundaryEdges = boundryEdges;
	}

}
