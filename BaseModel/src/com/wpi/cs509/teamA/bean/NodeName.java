package com.wpi.cs509.teamA.bean;

/**
 * Node name. This class is mainly for search, don't want to change the original node class.
 * This class saves the node info and also the name that should appear in the search supplement. 
 * 
 * @author CS 509-Team A
 * @version Nov 19th
 */
public class NodeName {
	
	/** Main information of the node*/
	private Node nodeInfo;
	
	/***/
	private String nodeName;
	
	public void setNodeName(String nodeName){
		this.nodeName = nodeName;
	}
	public void setNode(Node node){
		this.nodeInfo = node;
	}
	public String getNodeName(){
		return nodeName;
	}
	public Node getNode(){
		return nodeInfo;
	}
}
