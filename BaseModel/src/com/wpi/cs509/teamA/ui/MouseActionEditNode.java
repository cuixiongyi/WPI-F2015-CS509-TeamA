package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.UIDataBuffer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionEditNode extends MouseActionState {
//	private JToggleButton btnMngNode; 	
	
	public MouseActionEditNode(StateContext pStateContext) {
		super(pStateContext);

	}

	@Override
	public boolean cleanup() {

		return false;
	}

	@Override
	public boolean execute(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Node node = Database.getNodeFromCoordinate(new Coordinate(e.getX(), e.getY()),
					stateContext.getCurrentMap().getMapId());

			if (null != node) {
				JOptionPane.showMessageDialog(null, "Too close from another node.");
			} else {
				// Create a NodeInformationDialog
				NodeInformationDialog nodeInfo = new NodeInformationDialog(stateContext.getImageComponent(), e.getX(),
						e.getY());
				nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				nodeInfo.setVisible(nodeInfo.isFocusable());
			}
			return true;
		} 
		return false;
	}
	
	
}
