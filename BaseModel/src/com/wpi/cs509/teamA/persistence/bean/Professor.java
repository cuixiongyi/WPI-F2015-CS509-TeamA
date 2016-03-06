package com.wpi.cs509.teamA.persistence.bean;

/**
 * 
 * 
 * 
 * @author Team-A
 *
 */
public class Professor {
	private String professorName;
	private int nodeId;

	public Professor(String professorName, int nodeId) {
		this.professorName = professorName;
		this.nodeId = nodeId;
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
