package com.wpi.cs509.teamA.test.sample;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.DupEntranceMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;
import com.wpi.cs509.teamA.util.ProxyMap;

/**
 * This is a sample test class to show you how to use JUnit
 * 
 * @author CS 509-Team A 
 *
 */
public class SampleTest {

	@Test
	public void testAlgoStrategy() {
		AlgoController ac = new AlgoController();
		ac.getRoute();
		ac.getRoute();
	}

	/**
	 * A wrong way to use the proxy pattern
	 */
	@Test
	public void testProxyMapWrong() {
		// though we use proxy, we still should not new the same thing several
		// times only the instance will not be initialized again, a new
		// instance, a new initialization
		for (int i = 0; i < 3; i++) {
			AdjacencyMatrix FULLER = new ProxyMap("fuller");
			InputMatrix im1 = FULLER.getAdjacencyMatrix();
			InputMatrix im2 = FULLER.getAdjacencyMatrix();
		}
	}

	/**
	 * A right way to use the proxy pattern
	 * 
	 */

	@Test
	public void testProxyMapRight() {

		AdjacencyMatrix FULLER = new ProxyMap("fuller");
		// the same FULLER instance, will never 
		InputMatrix im1 = FULLER.getAdjacencyMatrix();
		InputMatrix im2 = FULLER.getAdjacencyMatrix();
	}
	
	@Test
	public void testInitDupEntranceMap(){
		
		DupEntranceMap dem = DupEntranceMap.initDupEntranceMap();
		DupEntranceMap dem2 = DupEntranceMap.initDupEntranceMap();
		dem.getDupEntranceMap();
		dem.getDupEntranceMap();
		
		
	}
}
