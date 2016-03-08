package com.wpi.cs509.teamA.controller;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.service.ViewComponentListenerImpl;
import com.wpi.cs509.teamA.ui.view.renderer.InputPanelRenderer;
import com.wpi.cs509.teamA.ui.view.renderer.ViewComponent;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.NodeType;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;

/**
 * 
 * This class behaves like a controller
 * 
 * Based on the button, it decides which listener will be binded to
 * 
 * The implementation class is in the service layer
 * 
 * @author teama
 *
 */
public class ViewInputPanelListenerController {

	private static ViewInputPanelListenerController viewListenerController;

	private static MainModel model = ViewComponent.getModel();
	private static InputPanelRenderer inputPanel = ViewComponent.getInputPanel();

	private ViewComponentListenerImpl impl;

	/**
	 * 
	 * This singleton makes sure that the listeners will be only binded once
	 * 
	 * @return true the first time the listeners are binded
	 */
	public static synchronized boolean bindListeners() {
		if (viewListenerController == null) {
			viewListenerController = new ViewInputPanelListenerController();
			return true;
		}

		return false;
	}

	private ViewInputPanelListenerController() {

		impl = new ViewComponentListenerImpl();
		addButtonSearch();
		addButtonSignup();
		addListSelectionListener();
		addComboBoxMapChanged();
		addButtonLogin();
		addFilterButtons();
		addOpenFile();
		addButtonEditNode();
		addButtonEditEdge();
		addLabelRotate();
		addButtonEditNodeInfo();
	}

	private void addOpenFile() {
		// TODO Auto-generated method stub
		inputPanel.getOpenMap().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickOpenMap();

			}
		});

	}

	private void addButtonEditNodeInfo() {
		inputPanel.getBtnEditNodeInfo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickEditNodeInfo();
			}
		});
	}

	private void addLabelRotate() {
		inputPanel.getLblSwapStartEnd().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				impl.clickOnSwapStartEnd();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	private void addButtonEditNode() {
		inputPanel.getBtnMngNode().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickEditNode();

			}
		});
	}

	private void addButtonEditEdge() {
		inputPanel.getBtnMngEdge().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickEditEdge();

			}
		});
	}

	private void addButtonLogin() {
		inputPanel.getBtnLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickLogin();

			}
		});
	}

	private void addButtonSearch() {
		inputPanel.getBtnSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickSearch();
			}
		});
	}

	private void addButtonSignup() {
		inputPanel.getBtnSignUp().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickSignup();
			}
		});
	}

	public void addFilterButtons() {
		inputPanel.getAllFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickAllFilter();
			}
		});
		inputPanel.getClassroomFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickFilter(NodeType.CLASSROOM);
			}
		});
		inputPanel.getOfficeFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickFilter(NodeType.OFFICE);
			}
		});
		inputPanel.getParkingFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickFilter(NodeType.PARKING);
			}
		});
		inputPanel.getRestroomFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickFilter(NodeType.RESTROOM);
			}
		});
		inputPanel.getLabFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickFilter(NodeType.LAB);
			}
		});
		inputPanel.getClearFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickClearFilter();
			}
		});
	}

	public void addListSelectionListener() {
		inputPanel.getMapList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Object value = inputPanel.getMapList().getSelectedValue();
				int idx = inputPanel.getMapList().getSelectedIndex();
				model.setCurrentPath(idx);

				// boolean tmp = matchAndSetMapIDFromString(value);
			}
		});
	}

	public void addComboBoxMapChanged() {
		inputPanel.getComboBoxMap().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// model.setCurrentPath(inputPanel.getComboBoxMap().getSelectedIndex());
				Object value = inputPanel.getComboBoxMap().getSelectedItem();
				matchAndSetMapIDFromString(value);
			}
		});
	}

	private boolean matchAndSetMapIDFromString(Object value) {
		if (value != null) {
			List<GeneralMap> maps = Database.getAllMapFromDatabase();
			for (GeneralMap map : maps) {
				if (value.equals(map.getMapAbbrName()) || value.equals(map.getMapName())) {
					model.setCurrentMapID(map.getMapId());
					return true;
				}
			}
		}
		return false;
	}

}