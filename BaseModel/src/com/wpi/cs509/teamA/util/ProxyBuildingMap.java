package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.BuildingMap;
import com.wpi.cs509.teamA.bean.Node;

public class ProxyBuildingMap implements AdjacencyMatrix {
	
	// this instance name is in honor of blade master -- bm
	private BuildingMap bm = null;

	@Override
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		if(bm == null){
			bm = new BuildingMap();
		}
		
		return bm.getAdjacencyMatrix();
		
	}
	
	// the parameter is still under discussion
	public ProxyBuildingMap(String dbTableName){
		
	}

	
	// this is for test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final AdjacencyMatrix FULLER = new ProxyBuildingMap("fuller");
		Node testMatrix [][] = new Node[10][10];
		InputMatrix im1 = FULLER.getAdjacencyMatrix();
		InputMatrix im2 = FULLER.getAdjacencyMatrix();
	}
}
