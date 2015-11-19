package com.wpi.cs509.teamA.bean;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;

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
	private String mapId;
	/**
	 * Map name
	 */
	private String mapName;
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

    private float displayScale;

    private List<Node> nodes;
    
    private BufferedImage image;

	/**
	 * Default constructor
	 */
	public GeneralMap() {

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

		// TODO:get data from database here, what data we need?
		System.out.println(
				"general map is getting data from database and making a matrix.. this should happen only once.. ");
		// TODO: assign value from the database to the adjacencyMatrix, make a new Matrix
		// adjacencyMatrix = makeMatrix();

	}
	
	// TODO: assign value from the database to the adjacencyMatrix, make a new Matrix
	private InputMatrix makeMatrix(){
		
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
	public String getMapId() {
		return mapId;
	}

	/**
	 * @param mapId the mapId to set
	 */
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * @return the scale
	 */
	public float getScale() {
		return measureScale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		measureScale = scale;
	}



    public String getMapImgPath() {
        return mapImgPath;
    }

    public void setMapImgPath(String mapImgPath) {

        this.mapImgPath = mapImgPath;
        try {
            image = ImageIO.read(new FileInputStream(mapImgPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	public float getDisplayScale() {
        return displayScale;
    }

    public void setDisplayScale(float displayScale) {
        this.displayScale = displayScale;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

}
