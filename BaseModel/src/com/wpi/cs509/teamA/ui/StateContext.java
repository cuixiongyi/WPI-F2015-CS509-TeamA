package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.ui.ImageComponent;
import com.wpi.cs509.teamA.ui.ImageComponentAdmin;
import jdk.nashorn.internal.runtime.ECMAException;

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
	 * The state of the context.
	 */
	private StateMouseListener mouseListenerState;

    public ImageComponent getImageComponent() {
        return imageComponent;
    }

    /**
     * Set imageComponent at the same time init
     * normal and admin MouseListener
     * @param imageComponent
     */
    public void setImageComponent(ImageComponent imageComponent) {
        this.imageComponent = imageComponent;
        normalUserMouseListener = new NormalUserMouseListener(imageComponent);
        adminMouseListener = new AdminMouseListener(imageComponent);

        // TODO: Move this part to input panel..
        // we need to add the event listener before the state pattern begins
        imageComponent.addMouseListener(normalUserMouseListener);
    }

    private ImageComponent imageComponent;
    private ImageComponentAdmin imageComponentAdmin;
    private MouseListener normalUserMouseListener;
    private MouseListener adminMouseListener;

    private Node startNode;
	private Node endNode;
	private List<Node> path;

	/**
	 * if filterNodeType[i] == 1 then display that type of node
	 * filterNodeType[i] == 0 don't display
	 */
	private List<Integer> filterNodeType;
	private List<Node> iconNodes;

    public GeneralMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(GeneralMap currentMap) {
        this.currentMap = currentMap;
    }

    //private List<GeneralMap> allMaps;
	private GeneralMap currentMap;

    public void setNormalUserMouseListener(MouseListener normalUserMouseListener) {
        this.normalUserMouseListener = normalUserMouseListener;
    }

    public void setAdminMouseListener(MouseListener adminMouseListener) {
        this.adminMouseListener = adminMouseListener;
    }

    public MouseListener getNormalUserMouseListener() {
		try {
			if (null == normalUserMouseListener)
				throw new Exception("null normalUserMouseListener");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return normalUserMouseListener;
    }

    public MouseListener getAdminMouseListener() {
		try {
			if (null == adminMouseListener)
				throw new Exception("null adminMouseListener");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return adminMouseListener;
    }


	/**
	 * Constructor. Initialize a default state.
	 */
	public StateContext() {
        normalUserMouseListener = null;
        adminMouseListener = null;

		this.filterNodeType = new ArrayList<Integer>();
		this.path = new ArrayList<Node>();
		//this.allMaps = new ArrayList<GeneralMap>();
		this.iconNodes = new ArrayList<Node>();

		this.setState(new StateNormalUser());
		this.setCurrentMap(1);

	}

	/**
	 * Changes the currently displayed map
	 *
	 * @param mapID
	 *            The map to be displayed
	 *
	 */
	public void setCurrentMap(int mapID) {
		this.currentMap = Database.getMapEntityFromMapId(mapID);

		// TODO Do some clean up

	}

	/**
	 *
	 * Set a new state.
	 * the new state the context will be.
	 */
	public void setState() {

	}

	public List<Node> getIconNodes() {
		return iconNodes;
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

	public void switchToAdminUser() {
        try{
            if (null == adminMouseListener)
                throw new Exception("Uninitlized admin user mouse listener");
        }catch(Exception E){
            E.printStackTrace();
        }

		try{
			if (null == mouseListenerState)
				throw new Exception("Uninitlized state");
		}catch(Exception E){
			E.printStackTrace();
		}

		mouseListenerState.switchMouseListener(this, getImageComponent(), getNormalUserMouseListener(), getAdminMouseListener());



    }

    public void switchToNormalUser() {
        try{
            if (null == normalUserMouseListener)
                throw new Exception("Uninitlized normal user mouse listener");
        }catch(Exception E){
            E.printStackTrace();
        }
        mouseListenerState.switchMouseListener(this, getImageComponent(), getNormalUserMouseListener(), getAdminMouseListener());



    }

    /**
     * Set a new state.
     *
     * @param newState
     *            the new state the context will be.
     */
    public void setState(StateMouseListener newState) {
        this.mouseListenerState = newState;
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
