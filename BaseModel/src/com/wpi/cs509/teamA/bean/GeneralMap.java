package com.wpi.cs509.teamA.bean;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.InputMatrix;
import com.wpi.cs509.teamA.util.PaintHelper;

/**
 * This is a class that defines all the map we want. It is also the class that
 * really get the matrix from the data from database. In the other words, it
 * provides the matrix to the ProxyMap class.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */

public class GeneralMap implements AdjacencyMatrix {

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
	/**
	 * the adjacency matrix of this map
	 */
	private InputMatrix adjacencyMatrix;

	// Refactor
	private String mapImgPath;

	private float displayScale = 1;

	private List<Node> nodes;

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

	// TODO: assign value from the database to the adjacencyMatrix, make a new
	// Matrix
	private InputMatrix makeMatrix() {

		// test..
		return new InputMatrix();

	}

	@Override
	/**
	 * the implementation of getting an adjacency matrix, this method will
	 * return a Adjacency Matrix that created in the initialization time.
	 */
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		return adjacencyMatrix;
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

	public void readImage() {

		// display the image. Note that " /" only works on UNIX
		this.mapImgPath = PaintHelper.getUserDir() + this.imageName;
		try {
			image = ImageIO.read(new FileInputStream(mapImgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getBoundaryEdges() {
		return BoundaryEdges;
	}

	public void setBoundaryEdges(List<Edge> boundryEdges) {
		BoundaryEdges = boundryEdges;
	}

}
