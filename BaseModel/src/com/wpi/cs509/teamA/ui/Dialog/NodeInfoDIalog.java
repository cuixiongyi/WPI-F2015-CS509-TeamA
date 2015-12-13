package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.model.MainModel;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by cuixi on 12/13/2015.
 */
public class NodeInfoDIalog extends JDialog implements ActionListener {

    private JPanel contentPanel = new JPanel();
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteButton = null;

    private JComboBox<String> comboBoxType;
    private MainModel model = null;

    
}
