package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by cuixi on 12/13/2015.
 */
public class NodeInfoDIalog extends JDialog implements ActionListener {

    private JButton okButton;
    private JButton cancelButton;
    private JLabel lbMajor;
    private JTextField major;
    private JLabel lbProfessor;
    private JTextField professor;
    private JLabel lbFeature;
    private JTextField feature;
    private JLabel lbActivity;
    private JTextField activity;

    private Node node = null;


    public NodeInfoDIalog(Node pnode){
        this.node=pnode;

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.CENTER;

        lbMajor = new JLabel("Major: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        contentPanel.add(lbMajor, cs);


        major = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        contentPanel.add(major, cs);

        lbProfessor = new JLabel("Information2: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        contentPanel.add(lbProfessor, cs);

        professor = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        contentPanel.add(professor, cs);

        lbFeature = new JLabel("OtherFeature: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        contentPanel.add(lbFeature, cs);


        feature = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        contentPanel.add(feature, cs);

        lbActivity = new JLabel("MapScale: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        contentPanel.add(lbActivity, cs);

        activity = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        contentPanel.add(activity, cs);



        JPanel buttonPane = new JPanel();

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        buttonPane.add(cancelButton);

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocation(100,100);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(e.getSource()==okButton)
        {

        }

    }


    public NodeInformation infoWrapper() {

        List<Major> majors =new ArrayList<Major>();
        List<Professor> professors=new ArrayList<Professor>();
        List<OtherFeature> labels=new ArrayList<OtherFeature>();
        List<Activity> activities=new ArrayList<Activity>();

        for(int i=0;i<major.getText().split(";").length-1;i=i+2)
        {
            Major m=new Major(major.getText().split(";")[i],major.getText().split(";")[i+1],node.getId());
            majors.add(m);
        }

        for(int j=0;j<professor.getText().split(";").length-1;j++)
        {
            Professor p=new Professor(professor.getText().split(";")[j],node.getId());
            professors.add(p);
        }

        for(int k=0;k<feature.getText().split(";").length-1;k++)
        {
            OtherFeature f=new OtherFeature(feature.getText().split(";")[k],node.getId());
            labels.add(f);
        }

        for(int ii=0;ii<activity.getText().split(";").length-1;ii++)
        {
            Activity a=new Activity();
            activities.add(a);

        }

        NodeInformation nodeInfo =new NodeInformation(node,majors,professors,labels,activities);
        return nodeInfo;






    }
    
}
