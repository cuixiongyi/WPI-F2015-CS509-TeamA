package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.Node;

/**
 * This class is is intended for searching, avoid changing the
 * original node class. This class saves the node info and also the name that
 * should appear in the search supplement.
 * 
 * @author CS 509-Team A
 * @version Nov 19th
 */
public class NodeName {

	/** Main information of the node */
	private Node nodeInfo;

	/** Name for search supplement */
	private String nodeName;

	/**
	 * 
	 * @param info
	 *            the Node with all information
	 * @param name
	 *            the name of the node just for search
	 */
	public NodeName(Node info, String name) {
		this.nodeInfo = info;
		this.nodeName = name;
	}

	/**
	 * @param node
	 *            name just for search supplement the node name to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @param Node
	 *            with all information the Node with all information to set
	 */
	public void setNode(Node node) {
		this.nodeInfo = node;
	}

	/**
	 * @return the node name
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @return the Node with all information
	 */
	public Node getNode() {
		return nodeInfo;
	}
}
