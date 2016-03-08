package com.wpi.cs509.teamA.strategy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.*;

public class allEdges {
	private Map<Integer, List<Edge>> edgesOnMap;
	private Stack<GeneralMap> mapsOnPath;
	private Stack<Integer> mapsId;
	private List<Edge> edges;
	private List<Edge> mapEdges;
	private Node startNode;
	private Node endNode;// when there is one certain destination
	private Node[] end;
	private HashMap<Integer, mapVertex> graphMaps;
	private boolean isMulEndNodes;

	public allEdges(List<Edge> edges, List<Edge> mapEdges, Node startNode, Node end) {
		this.edges = edges;
		this.mapEdges = mapEdges;
		this.startNode = startNode;
		this.endNode = end;
		this.isMulEndNodes=false;
	}

	public allEdges(List<Edge> edges, List<Edge> mapEdges, Node startNode, Node[] end) {
		this.edges = edges;
		this.mapEdges = mapEdges;
		this.startNode = startNode;
		this.end = end;
		this.isMulEndNodes=true;

	}

	public void init() {
		this.graphMaps = new HashMap<>(mapEdges.size());
		this.setMapsOnPath();
		System.out.println("initializing edges..");
		System.out.println("maps on paths are: " + this.mapsOnPath.size());
		this.edgesOnMap = new HashMap<Integer, List<Edge>>();
	}

	public boolean isNormal() {
		if (this.isMulEndNodes) {
			return false;
		}
		return true;
	}

	public boolean isMultipleMap() {
		if (startNode.getMap() == endNode.getMap())
			return false;
		else
			return true;
	}

	public List<Edge> getAllEdges() {
		for (Edge e : mapEdges) {
			edges.add(e);
		}
		return edges;

	}

	/**
	 * return boundary nodes on map1
	 */
	public List<Node> getBoundaryNodes(mapVertex map1, mapVertex map2) {
		if (!this.mapsId.contains(map1.getMapId())) {
			System.out.println("Requested Map not on path...");
			return null;
		}
		List<Node> n = new ArrayList<Node>();
		// System.out.println(map1.getBoundaryEdges().size());
		for (Edge e : map1.getBoundaryEdges()) {
			// System.out.println();
			if (e.getNode1().getMap().getMapId() == map1.getMapId()) {
				if (e.getNode2().getMap().getMapId() == map2.getMapId())
					n.add(e.getNode1());
			} else {
				if (e.getNode1().getMap().getMapId() == map2.getMapId())
					n.add(e.getNode2());
			}
		}
		return n;
	}

	public Node getOtherNode(Node n) {
		for (Edge e : mapEdges) {
			if (n.getId() == e.getNode1().getId() || n.getId() == e.getNode2().getId()) {
				return e.getTheOtherNode(n);
			}
		}
		return null;
	}

	public List<Edge> getEdgesOnMap(GeneralMap map) {
		// System.out.println(mapsId.size());
		if (!this.mapsId.contains((Integer) map.getMapId())) {
			// System.out.println(this.mapsOnPath.size());
			System.out.println("Requested Map not on path....");
			return null;
		}
		for (Integer m : mapsId) {
			List<Edge> value = new ArrayList<Edge>();
			this.edgesOnMap.put(m, value);
		}
		// System.out.println(edgesOnMap.size());
		this.splitEdges();
		return edgesOnMap.get(map.getMapId());

	}

	private void splitEdges() {

		for (Edge e : edges) {
			GeneralMap m = e.getNode1().getMap();
			// System.out.println(m);
			// System.out.println("+++"+mapsOnPath.peek());
			if (this.mapsId.contains(e.getNode1().getMap().getMapId())) {
				// System.out.println("++++++");
				edgesOnMap.get(m.getMapId()).add(e);
				// System.out.println(edgesOnMap.get(m.getMapId()).size());
			}
		}
	}

	/*
	 * getters and setters
	 * 
	 */

	public Map<Integer, List<Edge>> getEdgesOnMap() {
		return edgesOnMap;
	}

	public Node[] getEnd() {
		return this.end;
	}

	public void setEnd(Node[] end) {
		this.end = end;
	}

	public void setEdgesOnMap(Map<Integer, List<Edge>> edgesOnMap) {
		this.edgesOnMap = edgesOnMap;
	}

	public Stack<GeneralMap> getMapsOnPath() {
		return mapsOnPath;
	}

	private void setMapsOnPath() {
		this.mapsOnPath = this.getMaps();
		this.mapsId = new Stack<Integer>();
		for (GeneralMap v : mapsOnPath) {
			// System.out.println(v.getMapId());
			this.mapsId.push(v.getMapId());
		}
		// System.out.println(this.mapsOnPath.size());
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	/*
	 * for the calculation of multiple maps astar what maps are on the path from
	 * the start node to end node
	 */
	public Stack<GeneralMap> getMaps() {
		Stack<GeneralMap> result = new Stack<GeneralMap>();
		if (this.startNode.getMap().getMapId()==this.endNode.getMap().getMapId()) {
		//if (mapEdges.isEmpty()) {
			result.push(startNode.getMap());
			return result;
		}

		for (Edge e : mapEdges) {
			if (!graphMaps.containsKey(e.getNode1().getMap().getMapId())) {
				graphMaps.put(e.getNode1().getMap().getMapId(), new mapVertex(e.getNode1().getMap()));
			}
			if (!graphMaps.containsKey(e.getNode2().getMap().getMapId())) {
				graphMaps.put(e.getNode2().getMap().getMapId(), new mapVertex(e.getNode2().getMap()));
			}
		}
		// System.out.println(graphMaps.get(0).getMapId());
		// System.out.println(graphMaps.get(1).getMapId());
		for (Edge e : mapEdges) {
			// System.out.println(e.getNode1().getMap().getMapId());
			// System.out.println(graphMaps.get(0).getDist());
			graphMaps.get(e.getNode1().getMap().getMapId()).getNeighborM()
					.add(graphMaps.get(e.getNode2().getMap().getMapId()));
			graphMaps.get(e.getNode2().getMap().getMapId()).getNeighborM()
					.add(graphMaps.get(e.getNode1().getMap().getMapId()));
			e.getNode1().getMap().getBoundaryEdges().add(e);
			e.getNode2().getMap().getBoundaryEdges().add(e);
		}

		if (!graphMaps.containsKey(startNode.getMap().getMapId())) {
			System.err.printf("Graph doesn't contain start map \"%d\"\n", startNode.getMap().getMapId());
			// return 0;
		}

		mapVertex source = new mapVertex();
		source = graphMaps.get(startNode.getMap().getMapId());
		mapVertex destination = new mapVertex();
		destination = graphMaps.get(endNode.getMap().getMapId());
		// System.out.println(destination.getMapId());
		// System.out.println(source.getMapId());

		PriorityQueue<mapVertex> q = new PriorityQueue<mapVertex>();

		// set-up vertices
		for (mapVertex v : graphMaps.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Integer.MAX_VALUE);
			// System.out.println("++++++++++++++");
			q.add(v);
		}
		// System.out.println(q.size());
		dijkstraMap(q);

		mapVertex d = new mapVertex();
		d = destination;
		// System.out.println(d.getDist());
		do {
			result.push(d);
			d = d.getPrevious();
		} while (d != source);
		result.push(source);
		// System.out.println(destination.getMapId());
		return result;

	}

	private void dijkstraMap(final PriorityQueue<mapVertex> q) {
		mapVertex u;
		while (!q.isEmpty()) {

			u = q.poll(); // vertex with shortest distance (first iteration
			// System.out.println(u.getDist());
			if (u.getDist() == Integer.MAX_VALUE) {
				// System.out.println("++++++++++++++");
				break;
			} // we can ignore u (and any other remaining vertices)
				// since they are unreachable

			// look at distances to each neighbor
			for (mapVertex v : u.getNeighborM()) {
				// System.out.println("++++++++++++++");
				int alternateDist = u.getDist() + 1;
				if (alternateDist < v.getDist()) { // shorter path to neighbor
													// found
					// System.out.println("+++++");
					q.remove(v);
					v.setDist(alternateDist);
					// System.out.println(v.getId());
					// System.out.println(v.getDist());
					v.setPrevious(u);
					q.add(v);
					// System.out.println(q.size());
				}
			}
		}
	}
}
