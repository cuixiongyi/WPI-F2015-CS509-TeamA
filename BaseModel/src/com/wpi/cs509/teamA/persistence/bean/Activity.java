package com.wpi.cs509.teamA.persistence.bean;

import java.util.Date;

/**
 * Details of activities in one location
 * 
 * @author CS 509-Team A
 * @version Oct 4th
 */
public class Activity {

	/** the location of the activity */
	private Node activityLocation;

	/** the start time of the activity */
	private Date startTime;

	/** the end time of the activity */
	private Date endTime;

	/** the name of the activity */
	private String activityName;

	/** the details of the activity */
	private String activityDetail;

	/** the host of the activity */
	private String host;

	/**
	 * default constructor
	 * 
	 * @author CS 509-Team A
	 */
	public Activity() {

	}

	/**
	 * Get the location of the activity
	 * 
	 * @author CS 509-Team A
	 * @return location of the activity
	 */
	public Node getActivityLocation() {
		return activityLocation;
	}

	/**
	 * Set the location of the activity
	 * 
	 * @author CS 509-Team A
	 * @param location
	 *            of the activity
	 */
	public void setActivityLocation(Node activityLocation) {
		this.activityLocation = activityLocation;
	}

	/**
	 * Get the host of the activity
	 * 
	 * @author CS 509-Team A
	 * @return host of the activity
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Set the host of the activity
	 * 
	 * @author CS 509-Team A
	 * @param host
	 *            of the activity
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get the end time of the activity
	 * 
	 * @author CS 509-Team A
	 * @return end time of the activity
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Set the end time of the activity
	 * 
	 * @author CS 509-Team A
	 * @param end
	 *            time of the activity
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Get the name of the activity
	 * 
	 * @author CS 509-Team A
	 * @return name of the activity
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * Set the name of the activity
	 * 
	 * @author CS 509-Team A
	 * @param name
	 *            of the activity
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * Get details of the activity
	 * 
	 * @author CS 509-Team A
	 * @return details of the activity
	 */
	public String getActivityDetail() {
		return activityDetail;
	}

	/**
	 * Set details of the activity
	 * 
	 * @author CS 509-Team A
	 * @param details
	 *            of the activity
	 */
	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}

	/**
	 * Get details of the activity
	 * 
	 * @author CS 509-Team A
	 * @return start time of the activity
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Set details of the activity
	 * 
	 * @author CS 509-Team A
	 * @param start
	 *            time of the activity
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
