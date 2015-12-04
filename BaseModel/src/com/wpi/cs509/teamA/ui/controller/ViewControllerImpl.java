package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseListener;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseWheelListener;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Database;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by cuixi on 12/4/2015.
 */
public class ViewControllerImpl extends ViewControllerBase{

    private ImageMouseListener mouseListener = null;
    private ImageMouseWheelListener wheelListener = null;


    public ViewControllerImpl () {
        mouseListener = new ImageMouseListener(imageComponent, model);
        wheelListener = new ImageMouseWheelListener(imageComponent, model);
        addSearchButtonAction();
        addListSelectionListener();
        addComboBoxMapChanged();

    }

    private void addSearchButtonAction() {
        inputPanel.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSearch();
            }
        });
    }
    public void clickSearch() {
            if(model.getStartNode()==null || model.getEndNode()==null)
                return;
//                    inputPanel.picLabel.setVisible(false);
            inputPanel.getMapList().setVisible(true);
            ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();
            inputPanel.getMapList().removeAll();


            AlgoController algoController = new AlgoController(model.getStartNode(),
                    model.getEndNode());


            Stack<Node> path = algoController.getRoute();

            ArrayList<Node> singleMapPath = new ArrayList<Node>();
            ArrayList<String> mapNameList=new ArrayList<String>();
            int tmpMapId=path.peek().getMap().getMapId();
            mapNameList.add(path.peek().getMap().getMapName());
            while (path.size() > 0)
            {
                Node node = path.pop();
                if(node.getMap().getMapId()==tmpMapId)
                {
                    singleMapPath.add(node);
                }
                else {
                    multiMapPathLists.add(singleMapPath);
                    singleMapPath=new ArrayList<Node>();
                    singleMapPath.add(node);
                    tmpMapId=node.getMap().getMapId();
                    mapNameList.add(node.getMap().getMapName());
                }
            }
            multiMapPathLists.add(singleMapPath);

            //reset and initiate the Jlist

            DefaultListModel<String> mapListModel = new DefaultListModel<>();
            for(String name:mapNameList)
            {
                mapListModel.addElement(name);
            }
            inputPanel.getMapList().setModel(mapListModel);
            model.setMultiMapPathLists(multiMapPathLists);
            model.setCurrentMap(multiMapPathLists.get(0).get(0).getMap());
            ViewManager.updateView();


        }


        public void addListSelectionListener() {
        inputPanel.getMapList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                    Object value = inputPanel.getMapList().getSelectedValue();
                    boolean tmp = matchMapID(value);
            }
        });
    }

    public void addComboBoxMapChanged() {
        inputPanel.getComboBoxMap().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object value = inputPanel.getComboBoxMap().getSelectedItem();
                boolean tmp = matchMapID(value);

                }
        });


    }

    private boolean matchMapID(Object value) {
        if (value != null) {
            List<GeneralMap> maps = Database.getAllMapFromDatabase();
            for (GeneralMap map : maps) {
                if (value.equals(map.getMapAbbrName()) || value.equals(map.getMapName())) {
                    model.setCurrentMapID(map.getMapId());
                    ViewManager.updateView();
                    return true;
                }
            }
        }
        return false;
    }

}
