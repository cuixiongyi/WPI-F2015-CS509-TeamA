package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageKeyboardListener;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseListener;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseWheelListener;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.NodeType;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 * Created by cuixi on 12/4/2015.
 */
public class ViewController extends ViewControllerBase{

    private ImageMouseListener mouseListener = null;
    private ImageMouseWheelListener wheelListener = null;

    private ViewControllerImpl impl = null;
	private ImageKeyboardListener kbListener;
    public ViewController() {
        mouseListener = new ImageMouseListener(imageComponent, model);
        wheelListener = new ImageMouseWheelListener(imageComponent, model);
        kbListener = new ImageKeyboardListener(model);
        impl = new ViewControllerImpl();
        addButtonSearch();
        addButtonSignup();
        addListSelectionListener();
        addComboBoxMapChanged();
        addButtonLogin();
        addFilterButtons();
        addOpenFile();
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

    public void addFilterButtons(){
    	inputPanel.getAllFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				impl.clickAllFilter();
			}
		});
    	inputPanel.getClassroomFilter().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                impl.clickFilter(NodeType.CLASSROOM);
            }
    	});
    	inputPanel.getOfficeFilter().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                impl.clickFilter(NodeType.OFFICE);
            }
    	});
    	inputPanel.getParkingFilter().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                impl.clickFilter(NodeType.PARKING);
            }
    	});
    	inputPanel.getRestroomFilter().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                impl.clickFilter(NodeType.RESTROOM);
            }
    	});
    	inputPanel.getLabFilter().addActionListener(new ActionListener(){
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
                    Object value = inputPanel.getMapList().getSelectedValue();
                    boolean tmp = matchAndSetMapIDFromString(value);
            }
        });
    }

    public void addComboBoxMapChanged() {
        inputPanel.getComboBoxMap().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object value = inputPanel.getComboBoxMap().getSelectedItem();
                boolean tmp = matchAndSetMapIDFromString(value);
                model.clearFilters();
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
