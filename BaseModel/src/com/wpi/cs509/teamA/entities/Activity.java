package com.wpi.cs509.teamA.entities;

import java.util.Date;

public class Activity {

	private Node activityLocation;
	private Date startTime;
	private Date endTime;
	private String activityName;
	private String activityDetail;
	private String host;

	public Node getActivityLocation() {
		return activityLocation;
	}

	public void setActivityLocation(Node activityLocation) {
		this.activityLocation = activityLocation;
	}

	public Activity() {

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
