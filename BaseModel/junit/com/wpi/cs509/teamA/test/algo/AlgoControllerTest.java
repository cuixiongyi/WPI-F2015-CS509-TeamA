package com.wpi.cs509.teamA.test.algo;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;

public class AlgoControllerTest {

	/*
	 * # insert test algo data to database.. INSERT INTO routefinder.node (name,
	 * x, y, map_id, classification) VALUES ("A", 1, 1, 1, "undefined"); INSERT
	 * INTO routefinder.node (name, x, y, map_id, classification) VALUES ("B",
	 * 2, 2, 1, "undefined"); INSERT INTO routefinder.node (name, x, y, map_id,
	 * classification) VALUES ("C", 3, 3, 1, "undefined");
	 * 
	 * 
	 * INSERT INTO routefinder.relations (node_from, node_to, distance) VALUES
	 * (38, 39, 1); INSERT INTO routefinder.relations (node_from, node_to,
	 * distance) VALUES (39, 40, 1);
	 */
	@Test
	public void testFindPath() {

		AlgoController ac = new AlgoController("start", "end");
		Map<Integer, List<Node>> result = ac.getRoute();
		for (Map.Entry<Integer, List<Node>> entry : result.entrySet()) {
			List<Node> temp = entry.getValue();
			for (int i = 0; i < temp.size(); i++) {
				System.out.println("id = " + temp.get(i).getId());
			}
		}

	}

}
