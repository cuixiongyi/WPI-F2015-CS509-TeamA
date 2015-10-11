package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;

public class ProxyMap implements AdjacencyMatrix {
	
	private GeneralMap generalMap = null;

	@Override
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		if(generalMap == null){
			System.out.println("Initializing the general map.. should initialize only once..");
			generalMap = new GeneralMap();
		}
		
		return generalMap.getAdjacencyMatrix();
		
	}
	
	// the parameter is still under discussion
	public ProxyMap(String dbTableName){
		
	}

}
