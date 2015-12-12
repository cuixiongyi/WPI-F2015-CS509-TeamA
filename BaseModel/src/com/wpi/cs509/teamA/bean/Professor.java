package com.wpi.cs509.teamA.bean;

public class Professor {
	private String professorName;
	private int nodeId;
	public Professor(String professorName, int node_id){
		this.professorName = professorName;
		this.nodeId = node_id;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
}
