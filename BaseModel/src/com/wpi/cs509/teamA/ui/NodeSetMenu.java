package com.wpi.cs509.teamA.ui;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import com.wpi.cs509.teamA.bean.Node;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * This is the class that administrators uses to add nodes to database
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class NodeSetMenu extends JPopupMenu implements ActionListener {
	private Node node;
	private InputPanel inputPanel;
	private JMenuItem mntmSrc;
	private JMenuItem mntmDes;
	private JMenuItem cleanupMap;
	
	private StateContext stateContext;

	private final static String SOURCE = "Add as source";
	private final static String DES = "Add as destination";
	private final static String CLEAN = "Clean up route";

	/**
	 * Create the Menu.
	 */
	public NodeSetMenu(InputPanel inputPanel, Node node, StateContext stateContext) {
		this.node = node;
		this.inputPanel = inputPanel;
		this.stateContext = stateContext;

		setBounds(100, 100, 450, 300);

		// set up menu item
		mntmSrc = new JMenuItem(SOURCE);
		mntmSrc.addActionListener(this);
		add(mntmSrc);

		mntmDes = new JMenuItem(DES);
		add(mntmDes);
		mntmDes.addActionListener(this);
		
		cleanupMap = new JMenuItem(CLEAN);
		add(cleanupMap);
		cleanupMap.addActionListener(this);

		if (null == node) {
			mntmDes.setEnabled(false);
			mntmSrc.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == mntmSrc) {

			this.inputPanel.getFromText().setText(node.getMap().getMapName()+" "+node.getName());
			this.stateContext.setStartNode(node);
			this.inputPanel.getAutoSuggestorFrom().getAutoSuggestionPopUpWindow().setVisible(false);
					
		} else if (e.getSource()==mntmDes){

			this.inputPanel.getToText().setText(node.getMap().getMapName()+" "+node.getName());
			this.stateContext.setEndNode(node);
			this.inputPanel.getAutoSuggestorTo().getAutoSuggestionPopUpWindow().setVisible(false);
		} else if(e.getSource()==cleanupMap){
			stateContext.cleanUpRoute();
		}
		
		stateContext.repaint();
	}

}
