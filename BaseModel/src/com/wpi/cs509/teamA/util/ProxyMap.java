package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;

public class ProxyMap implements AdjacencyMatrix {
	
	private GeneralMap generalMap = null;

	@Override
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		if(generalMap == null){
			generalMap = new GeneralMap();
		}
		
		return generalMap.getAdjacencyMatrix();
		
	}
	
	// the parameter is still under discussion
	public ProxyMap(String dbTableName){
		
	}

	
	// this is for test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final AdjacencyMatrix FULLER = new ProxyMap("fuller");
		Node testMatrix [][] = new Node[10][10];
		InputMatrix im1 = FULLER.getAdjacencyMatrix();
		InputMatrix im2 = FULLER.getAdjacencyMatrix();
	}
}
