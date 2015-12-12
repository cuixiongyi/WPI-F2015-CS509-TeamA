package com.wpi.cs509.teamA.bean;

public class OtherFeature {
	private String featureLabel;
	private int nodeId;
	public OtherFeature(String featureLabel, int nodeId){
		this.featureLabel = featureLabel;
		this.nodeId = nodeId;
	}
	public String getFeatureLabel() {
		return featureLabel;
	}
	public void setFeatureLabel(String featureLabel) {
		this.featureLabel = featureLabel;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
}
