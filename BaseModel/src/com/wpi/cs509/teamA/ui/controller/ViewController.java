package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseListener;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseWheelListener;
import com.wpi.cs509.teamA.util.Database;

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
    public ViewController() {
        mouseListener = new ImageMouseListener(imageComponent, model);
        wheelListener = new ImageMouseWheelListener(imageComponent, model);
        impl = new ViewControllerImpl();
        addButtonSearch();
        addButtonSignup();
        addListSelectionListener();
        addComboBoxMapChanged();
        addButtonLogin();


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
