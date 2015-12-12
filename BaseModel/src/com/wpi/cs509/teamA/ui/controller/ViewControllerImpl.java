package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.ui.Dialog.AdminDialog;
import com.wpi.cs509.teamA.ui.Dialog.SignupDialog;
import com.wpi.cs509.teamA.ui.UIConstant;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Database;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by cuixi on 12/4/2015.
 */
 class ViewControllerImpl extends ViewControllerBase{

    public void clickLogin() {
        if (model.ifLoginAdmin()) {
            AdminDialog adminDialog = new AdminDialog(model, inputPanel);
            adminDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            adminDialog.setVisible(inputPanel.isFocusable());
            // stateContext.switchToAdminUser();
            ViewManager.updateView();

        } else {

            JOptionPane.showMessageDialog(null, "You have logged out");
            Database.InitFromDatabase();
            // InputPanel.this.getBtnNeighborManage().setVisible(false);
            inputPanel.getBtnLogin().setText(UIConstant.LOGIN);
            // InputPanel.this.getBtnSynchronize().setVisible(false);
            model.switchToState(new MouseActionSelectNode(model));
            ViewManager.updateView();

        }
    }

    public void clickSignup() {
        SignupDialog signUpDialog = new SignupDialog( inputPanel);
        signUpDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        signUpDialog.setVisible(true);
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
        ArrayList<GeneralMap>mapList = new ArrayList<GeneralMap>();
        int tmpMapId=path.peek().getMap().getMapId();
        mapNameList.add(path.peek().getMap().getMapName());
        mapList.add(path.peek().getMap());
      
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
              
                mapList.add(node.getMap());
                System.out.println(node.getMap());
                System.out.println("map"+mapList.size());
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
        System.out.println("hehe"+mapListModel.getSize());
        
        model.setCurrentMap(multiMapPathLists.get(0).get(0).getMap());
        model.setMultiMapList(mapList);
        ViewManager.updateView();


    }

}
