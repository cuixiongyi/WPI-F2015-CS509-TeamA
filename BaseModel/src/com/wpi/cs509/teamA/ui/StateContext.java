package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * Instead of using a lot of if and else statements to capture the state of an
 * object, a state machine design pattern is a better solution that's more
 * powerful and expandable. The state of the control class is tracked by the
 * state machine and the user input will cause a state change, with all the
 * related parameters stored in the control class. States changes will occur
 * based on the state of the user. This means that the application will respond
 * to user input differently depending on whether the user is an administrator,
 * regular user, or not logged in. The will prove the be very powerful when
 * implementing new features.
 * 
 * @author CS 509-Team A
 *
 */
// TODO: make this class singleton
public class StateContext {



	private Node startNode;
	private Node endNode;
	private List<Node> path;

	/** if filterNodeType[i] == 1 then display that type of node
	 * 	filterNodeType[i] == 0 don't display
	 */
	private List<int> filterNodeType;

    private List<GeneralMap> allMaps;
    private GeneralMap currentMap;


    /**
	 * Constructor. Initialize a default state.
	 */
	public StateContext() {
		filterNodeType = new List<int>();
		path = new List<Node>();
        allMaps = new List<GeneralMap>();

	}

	/**
     *
	 * Set a new state.
	 * 
	 * @param newState
	 *            the new state the context will be.
	 */
	public void setState() {

	}

	/**
	 * Method to change the state.
	 * 
	 * @param imageComponent
	 *            the component that we want to add mouse listener on.
	 * @param normalUserMouseListener
	 *            the listener for the normal user.
	 * @param adminMouseListener
	 *            the listener for the admin user.
	 */
	public void switchState(ImageComponent imageComponent, MouseListener normalUserMouseListener,
			MouseListener adminMouseListener) {
		mouseListenerState.switchMouseListener(this, imageComponent, normalUserMouseListener, adminMouseListener);
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}


	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}
	public List<Node> getPath() {
		return path;
	}

	public void setPath(List<Node> path) {
		this.path = path;
	}

}
