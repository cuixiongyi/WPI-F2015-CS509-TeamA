package com.wpi.cs509.teamA.bean;

public class Major {
	private String majorName;
	private String majorAbbr;
	private int nodeId;
	public Major(String majorName, String majorAbbr, int nodeId){
		this.majorAbbr = majorAbbr;
		this.majorName = majorName;
		this.nodeId = nodeId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getMajorAbbr() {
		return majorAbbr;
	}
	public void setMajorAbbr(String majorAbbr) {
		this.majorAbbr = majorAbbr;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
}
