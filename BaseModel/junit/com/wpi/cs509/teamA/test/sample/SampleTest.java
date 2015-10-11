package com.wpi.cs509.teamA.test.sample;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlogController;
import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;
import com.wpi.cs509.teamA.util.ProxyMap;

public class SampleTest {
	
	@Test
	public void testAlgoStrategy(){
		AlogController ac = new AlogController();
		ac.getRoute();	
	}
	
	@Test
	public void testProxyMap() {
		final AdjacencyMatrix FULLER = new ProxyMap("fuller");
		// Node testMatrix [][] = new Node[10][10];
		InputMatrix im1 = FULLER.getAdjacencyMatrix();
		InputMatrix im2 = FULLER.getAdjacencyMatrix();
	}

}
