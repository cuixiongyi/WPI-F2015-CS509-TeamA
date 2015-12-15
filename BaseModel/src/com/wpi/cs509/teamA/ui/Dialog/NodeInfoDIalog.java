package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.dao.impl.NodeInformationDaoImpl;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.util.Database;

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
    private JLabel lbMajorName;
    private JTextField majorName;
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

        lbMajorName = new JLabel("MajorName: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        contentPanel.add(lbMajorName, cs);


        majorName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        contentPanel.add(majorName, cs);

        lbProfessor = new JLabel("Professor: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        contentPanel.add(lbProfessor, cs);

        professor = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        contentPanel.add(professor, cs);

        lbFeature = new JLabel("OtherFeatures: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        contentPanel.add(lbFeature, cs);


        feature = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        contentPanel.add(feature, cs);

        lbActivity = new JLabel("Activities: ");
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
            NodeInformationDaoImpl nodeInfoDao =new NodeInformationDaoImpl();
            nodeInfoDao.saveNodeInformation(infoWrapper());
            this.dispose();

        }
        if(e.getSource()==cancelButton)
            this.dispose();

    }


    public NodeInformation infoWrapper() {

        List<Major> majors =new ArrayList<Major>();
        List<Professor> professors=new ArrayList<Professor>();
        List<OtherFeature> labels=new ArrayList<OtherFeature>();
        List<Activity> activities=new ArrayList<Activity>();

        if(majorName.getText().equals("")){
            majors.add(new Major("","",node.getId()));
        }else if(majorName.getText().split(";").length>=2){
            for (int i = 0; i < majorName.getText().split(";").length - 1; i = i + 2) {
                Major m = new Major(majorName.getText().split(";")[i], majorName.getText().split(";")[i + 1], node.getId());
                majors.add(m);
            }
        }else{
            majors.add(new Major(majorName.getText(),"",node.getId()));
           
        }
    
        if(professor.getText().equals(""))
        {
            professors.add(new Professor("",node.getId()));

        }else{
        	if(professor.getText().contains(";")){
	            for(int j=0;j<professor.getText().split(";").length;j++) {
	                Professor p=new Professor(professor.getText().split(";")[j],node.getId());
	                professors.add(p);
	            }
            }
        	else{
        		Professor p=new Professor(professor.getText(),node.getId());
                professors.add(p);
        	}
        }

        if(feature.getText().equals("")){
            labels.add(new OtherFeature("",node.getId()));
        }else {
            for (int k = 0; k < feature.getText().split(";").length - 1; k++) {
                OtherFeature f = new OtherFeature(feature.getText().split(";")[k], node.getId());
                labels.add(f);
                
            }
        }

        if(activity.getText().equals("")){
            activities.add(new Activity());
        }else {
            for (int ii = 0; ii < activity.getText().split(";").length - 1; ii++) {
                Activity a = new Activity();
                activities.add(a);

            }
        }

        NodeInformation nodeInfo =new NodeInformation(node,majors,professors,labels,activities);
        return nodeInfo;






    }
    
}
