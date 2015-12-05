package com.wpi.cs509.teamA.ui.Dialog;

import javax.swing.JPopupMenu;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;

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
	
	private MainModel model = null;

	private final static String SOURCE = "Add as source";
	private final static String DES = "Add as destination";
	private final static String CLEAN = "Clean up route";

	/**
	 * Create the Menu.
	 */
	public NodeSetMenu(InputPanel inputPanel,
					   MainModel pModel,
					   Node node) {
		this.node = node;
		this.inputPanel = inputPanel;
		this.model = pModel;

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
			model.setStartNode(node);
			this.inputPanel.getAutoSuggestorFrom().getAutoSuggestionPopUpWindow().setVisible(false);
					
		} else if (e.getSource()==mntmDes){

			this.inputPanel.getToText().setText(node.getMap().getMapName()+" "+node.getName());
			model.setEndNode(node);
			this.inputPanel.getAutoSuggestorTo().getAutoSuggestionPopUpWindow().setVisible(false);
		} else if(e.getSource()==cleanupMap){
			model.cleanUpRoute();
		}

	}

}
