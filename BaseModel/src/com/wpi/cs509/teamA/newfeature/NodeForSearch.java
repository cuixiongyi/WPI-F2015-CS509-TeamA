package com.wpi.cs509.teamA.newfeature;

import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.util.AutoSuggestUtil.SuggestorPainter.SuggestorEnum;

public class NodeForSearch {
	
	private Node node;
	private String stringForSearch;
	private String stringForDisplay;
	private int priority;
	private SuggestorEnum node_label;

	public NodeForSearch(Node node, String stringForS, String stringForD, SuggestorEnum nodeTypeS) {
		this.node = node;
		this.stringForDisplay = stringForD;
		this.stringForSearch = stringForS;
		this.node_label = nodeTypeS;
		this.priority = node_label.getValue();
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getStringForSearch() {
		return stringForSearch;
	}

	public void setStringForSearch(String stringForSearch) {
		this.stringForSearch = stringForSearch;
	}

	public String getStringForDisplay() {
		return stringForDisplay;
	}

	public void setStringForDisplay(String stringForDisplay) {
		this.stringForDisplay = stringForDisplay;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public SuggestorEnum getNode_label() {
		return node_label;
	}

	public void setNode_label(SuggestorEnum node_label) {
		this.node_label = node_label;
	}

	public static void main(String[] args) {
		// NodeForSearch ns = new NodeForSearch();
	}
}
