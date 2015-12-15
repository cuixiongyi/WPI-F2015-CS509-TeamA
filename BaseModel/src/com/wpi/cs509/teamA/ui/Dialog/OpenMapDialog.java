package com.wpi.cs509.teamA.ui.Dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.wpi.cs509.teamA.dao.impl.MapDaoImpl;
import com.wpi.cs509.teamA.model.MainModel;


/**
 * Created by xiongkuang on 12/12/2015.
 */
public class OpenMapDialog  extends JDialog  implements ActionListener{
	
	private JButton open;
	private MainModel model;
	private JLabel lbFileName;
	private JTextField fileName;
	private JLabel lbScale;
	private JTextField mapScale;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lbAbbrName;
	private JTextField abbrName; 
	private JFileChooser fc;
	private JLabel lbPath;
	private JTextField filePath;
	
	
	public OpenMapDialog(MainModel pModel){
		this.model=pModel;
		
		JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.CENTER;
        
        
        lbFileName = new JLabel("FileName: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        contentPanel.add(lbFileName, cs);
       
        
        fileName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        contentPanel.add(fileName, cs);
        
        lbAbbrName = new JLabel("AbbrName: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        contentPanel.add(lbAbbrName, cs);
        
        abbrName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        contentPanel.add(abbrName, cs);
        
        lbPath = new JLabel("FilePath: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        contentPanel.add(lbPath, cs);
       
        
        filePath = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        contentPanel.add(filePath, cs);
        
        lbScale = new JLabel("MapScale: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        contentPanel.add(lbScale, cs);

        mapScale = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        contentPanel.add(mapScale, cs);
        
        //
        JPanel buttonPane = new JPanel();
        
        open=new JButton("Open");
        open.addActionListener(this);
        buttonPane.add(open);
        
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		fc=new JFileChooser();
        
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocation(100,100);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==open){
			int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file =fc.getSelectedFile();
				fileName.setText(file.getName());
				filePath.setText(file.getPath());
				mapScale.setText("1.0");
			}else {
				this.dispose();
				System.out.println("Not saved");
			}
		}else if(e.getSource()==okButton){
			saveMap();
			this.dispose();
		}else if(e.getSource()==cancelButton){
			this.dispose();
		}
		
		
	}
	

	public void saveMap()
	{
        MapDaoImpl mapDaoImpl =new MapDaoImpl();
        mapDaoImpl.saveMap(fileName.getText(),abbrName.getText(),filePath.getText(), Float.parseFloat(mapScale.getText()));
	}
	
	

}
