package com.wpi.cs509.teamA.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.wpi.cs509.teamA.util.Database;

public class AdminUserState extends UserState implements ActionListener {

	private JButton btnSynchronize;
	private JToggleButton btnMngNode;
	private JToggleButton btnMngEdge;

	private static int numNodeBtn;
	private static int numEdgeBtn;

	public AdminUserState(StateContext pStateContext) {
		super(pStateContext);
		numNodeBtn = 0;
		numEdgeBtn = 0;

		btnMngNode = new JToggleButton("Mangage Node");
		btnMngNode.addActionListener(this);
		// btnMngNode.setVisible(true);
		btnMngNode.setBounds(80, 420, 75, 30);
		System.out.println(inputPanel);
		inputPanel.add(btnMngNode);

		btnMngEdge = new JToggleButton("Manage Edge");
		btnMngEdge.addActionListener(this);
		// btnMngEdge.setVisible(true);
		btnMngEdge.setBounds(155, 420, 75, 30);
		inputPanel.add(btnMngEdge);

		btnSynchronize = new JButton("Sync");
		btnSynchronize.addActionListener(this);
		btnSynchronize.setVisible(true);
		btnSynchronize.setBounds(80, 380, 150, 30);
		inputPanel.add(btnSynchronize);

		inputPanel.repaint();

	}

	@Override
	public boolean cleanup() {
		// TODO Auto-generated method stub
		this.btnMngEdge.setVisible(false);
		this.btnMngNode.setVisible(false);
		this.btnSynchronize.setVisible(false);
		return false;
	}

	public void clickSync() {
		Database.InitFromDatabase();
		this.imageComponent.repaint();
		/*
		 * comboSourceModel.removeAllElements();
		 * comboDesModel.removeAllElements(); Map<Integer, List<Node>> allNodes
		 * = UIDataBuffer.getAllNodes(); if (allNodes != null &&
		 * allNodes.get(1).size() != 0) { for (int i = 0; i <
		 * allNodes.get(1).size(); i++) { if
		 * (allNodes.get(1).get(i).getName().toString().equals("Location"))
		 * continue;
		 * comboSourceModel.addElement(allNodes.get(1).get(i).getName().toString
		 * ());
		 * comboDesModel.addElement(allNodes.get(1).get(i).getName().toString())
		 * ; } }
		 */
	}

	private void clickMngEdge() {

        if (numEdgeBtn % 2 == 1) {
			btnMngEdge.setSelected(false);
			stateContext.switchToState(new MouseActionSelectNode(stateContext));
		} else {
			stateContext.switchToState(new MouseActionEditEdge(stateContext));
			btnMngNode.setSelected(false);
		}
        numEdgeBtn++;

    }

	private void clickMngNode() {

        if (numNodeBtn % 2 == 1) {
			// System.out.println(stateContext.getMyState().getClass());
			btnMngNode.setSelected(false);
			stateContext.switchToState(new MouseActionSelectNode(stateContext));
		} else {
			stateContext.switchToState(new MouseActionEditNode(stateContext));
			btnMngEdge.setSelected(false);
		}
        numNodeBtn++;

    }

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnMngNode) {
			clickMngNode();
		}
		if (e.getSource() == btnMngEdge) {
			clickMngEdge();
		}

	}

	@Override
	public boolean execute(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON3) {
			System.out.println("hhe");
			stateContext.switchToState(new MouseActionSelectNode(stateContext));
			btnMngNode.setSelected(false);
			btnMngEdge.setSelected(false);
			inputPanel.repaint();
			if(numNodeBtn%2==1){
				numNodeBtn++;
			}
			if(numEdgeBtn%2==1){
				numEdgeBtn++;
			}
			
			
			return true;
		}
		return false;
	}

}
