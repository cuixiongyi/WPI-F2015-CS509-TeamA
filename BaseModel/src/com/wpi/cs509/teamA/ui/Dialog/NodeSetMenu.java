package com.wpi.cs509.teamA.ui.Dialog;

import javax.swing.JPopupMenu;

import com.wpi.cs509.teamA.model.DataModel;
import com.wpi.cs509.teamA.persistence.bean.GeneralMap;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.persistence.bean.Path;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintImageHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
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
	
	private DataModel model = null;
	private JMenuItem printRoute;

	private final static String SOURCE = "Add as source";
	private final static String rmSOURCE = "Remove source";
	private final static String DES = "Add as destination";
	private final static String rmDES = "Remove destination";
	private final static String CLEAN = "Clean up route";
	private final static String PRINT = "Print Route";

	/**
	 * Create the Menu.
	 */
	public NodeSetMenu(InputPanel inputPanel,
					   DataModel pModel,
					   Node node) {
		this.node = node;
		this.inputPanel = inputPanel;
		this.model = pModel;

		setBounds(100, 100, 450, 300);

		// set up menu item
		if (null != node && model.getStartNode() == node) {
			mntmSrc = new JMenuItem(rmSOURCE);
		}
		else {
			mntmSrc = new JMenuItem(SOURCE);

		}
		mntmSrc.addActionListener(this);
		add(mntmSrc);

		if (model.getEndNode().contains(node)) {
			mntmDes = new JMenuItem(rmDES);
		}
		else {
			mntmDes = new JMenuItem(DES);
		}
		add(mntmDes);
		mntmDes.addActionListener(this);
		
		cleanupMap = new JMenuItem(CLEAN);
		add(cleanupMap);
		cleanupMap.addActionListener(this);
		
		printRoute = new JMenuItem(PRINT);
		add(printRoute);
		printRoute.addActionListener(this);

		if (null == node) {
			mntmDes.setEnabled(false);
			mntmSrc.setEnabled(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == mntmSrc) {

			model.setStartNode(node);
			
				this.inputPanel.getFromText().setText(node.getMap().getMapName()+" "+node.getName());
				this.inputPanel.getAutoSuggestorFrom().getAutoSuggestionPopUpWindow().setVisible(false);

		} else if (e.getSource()==mntmDes){

			model.addOneEndNode(node);
			
				this.inputPanel.getToText().setText(node.getMap().getMapName()+" "+node.getName());
				this.inputPanel.getAutoSuggestorTo().getAutoSuggestionPopUpWindow().setVisible(false);
		} else if(e.getSource()==cleanupMap){
			inputPanel.getFromText().setText("");
			inputPanel.getToText().setText("");
			model.cleanUpRoute();

//			ViewManager.updateView();
		} else if(e.getSource()==printRoute){
			try {

				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = filechooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedfile = filechooser.getSelectedFile();
					File file = new File(selectedfile.getPath());
					if (null == model.getPaths())
						return;
					ArrayList<Path> paths = model.getPaths();
					for (int i = 0; i< model.getPaths().size();i++) {
						GeneralMap map = model.getMultiMapLists().get(i);
						// System.out.println(mp.getMapName());
						LinearTransform lt = new LinearTransform();
						lt.setScale(1.2);
						PaintImageHelper.printRoute(paths.get(i), lt, file, i);


					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();

			}
		}
		

	}

}
