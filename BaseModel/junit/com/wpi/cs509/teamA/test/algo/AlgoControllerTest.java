//package com.wpi.cs509.teamA.test.algo;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//
//import com.wpi.cs509.teamA.bean.Node;
//import com.wpi.cs509.teamA.controller.AlgoController;
//
//public class AlgoControllerTest {
//
//	@Test
//	public void testFindPath() {
//
//		AlgoController ac = new AlgoController("start", "end");
//		Map<Integer, List<Node>> result = ac.getRoute();
//		for (Map.Entry<Integer, List<Node>> entry : result.entrySet()) {
//			List<Node> temp = entry.getValue();
//			for (int i = 0; i < temp.size(); i++) {
//				System.out.println("id = " + temp.get(i).getId());
//			}
//		}
//
//	}
//
//}
