package com.wpi.cs509.teamA.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JList;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.util.Database;

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

	/**
	 * The current state
	 */
	private MouseActionState myState;
	private UserState myUserState;

	private UserAccount myAccount;


	private ImageComponent imageComponent;
	private InputPanel inputPanel;

	private Node startNode;
	private Node endNode;
	private List<Node> path;
	private ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();


    private boolean addedNewNode = false;
    private Node newNode = null;

	/**
	 * if filterNodeType[i] == 1 then display that type of node
	 * filterNodeType[i] == 0 don't display
	 */
	private List<Integer> filterNodeType;
	private List<Node> iconNodes;


	private GeneralMap currentMap;

	/**
	 * Constructor. Initialize a default state.
	 */
	public StateContext() {

		this.filterNodeType = new ArrayList<Integer>();
		this.path = new ArrayList<Node>();

		this.iconNodes = new ArrayList<Node>();

		myState = new MouseActionSelectNode(this);
		myUserState = new NormalUserState(this);
		myAccount = new UserAccount();

		this.switchToState(new MouseActionSelectNode(this));
		this.switchUserState(new NormalUserState(this));
		this.setCurrentMap(1);


	}

	/**
	 * Set a new state.
	 *
	 * @param newState
	 *            the new state the context will be.
	 */
	public void switchToState(MouseActionState newState) {
		if (null != myState) {
			myState.cleanup();
		}
		this.myState = newState;
	}

	public void switchUserState(UserState newState) {
		if (null != myUserState) {
			myUserState.cleanup();
		}
		this.myUserState = newState;
	}

	public boolean execute(MouseEvent e) {
		myUserState.execute(e);
		boolean ret =  myState.execute(e);
		getImageComponent().repaint();
		return ret;
	}

    public void paintOnImage(Graphics2D g2) {

		myState.paintOnImage(g2);
    }


	/**
	 * setter and getter
	 */


	/**
	 * Changes the currently displayed map
	 *
	 * @param mapID
	 *            The map to be displayed
	 *
	 */
	public void setCurrentMap(int mapID, JComboBox<String> comboBox) {
		this.currentMap = Database.getMapEntityFromMapId(mapID);
		if(inputPanel!=null)
			inputPanel.getMapList().setSelectedValue(currentMap.getMapAbbrName(), true);

		// TODO Do some clean up

	}
	
	public void setCurrentMap(int mapID,JList<String> jList) {
		this.currentMap = Database.getMapEntityFromMapId(mapID);
		if(inputPanel!=null)
			inputPanel.getComboBoxMap().setSelectedItem(currentMap.getMapAbbrName());

		// TODO Do some clean up

	}
	
	public void setCurrentMap(int mapID) {
		this.currentMap = Database.getMapEntityFromMapId(mapID);
		if(inputPanel!=null)
		{
			inputPanel.getComboBoxMap().setSelectedItem(currentMap.getMapAbbrName());
			inputPanel.getMapList().setSelectedValue(currentMap.getMapAbbrName(), true);
		}

		// TODO Do some clean up

	}
	public GeneralMap getCurrentMap() {
		return currentMap;
	}

	public List<Node> getIconNodes() {
		return iconNodes;
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

	public void setImageComponent(ImageComponent imageComponent) {
		this.imageComponent = imageComponent;
	}

	public boolean isAdmin() {
		return myAccount.isAdmin();
	}

	public void setInputPanel(InputPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	public InputPanel getInputPanel() {
		return inputPanel;
	}

	public MouseActionState getMyState() {
		return myState;
	}

	public UserState getMyUserState() {
		return myUserState;
	}

    public ImageComponent getImageComponent() {
        return imageComponent;
    }

	public ArrayList<ArrayList<Node>> getMultiMapPathLists() {
		return multiMapPathLists;
	}

	public void setMultiMapPathLists(ArrayList<ArrayList<Node>> multiMapPathLists) {
		this.multiMapPathLists = multiMapPathLists;
	}
    
    

    public boolean isAddedNewNode() {
        return addedNewNode;
    }

    public void setAddedNewNode(boolean addedNewNode) {
        this.addedNewNode = addedNewNode;
    }

    public Node getNewNode() {
        return newNode;
    }

    public void setNewNode(Node newNode) {
        this.newNode = newNode;
    }

}
