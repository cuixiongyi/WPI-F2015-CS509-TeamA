package com.wpi.cs509.teamA.bean;

public class History {
	private int nodeid;
	private String historyStr;
	private int count;
	
	public History(String historyStr, int nodeid, int count){
		this.historyStr = historyStr;
		this.nodeid = nodeid;
		this.count = count;
	}
	public int getNodeid() {
		return nodeid;
	}
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
	public String getHistoryStr() {
		return historyStr;
	}
	public void setHistoryStr(String historyStr) {
		this.historyStr = historyStr;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
