package com.wpi.cs509.teamA.ui.Dialog;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by xiongkuang on 12/11/15.
 */
public class PopupPanel  extends JPanel implements ActionListener {

    private JLabel picLabel;
    private JLabel informationLabel;
    private Node node;
    private BufferedImage logo;

	public PopupPanel()
    {
        this.setSize(300,250);
        this.setLayout(null);
        
        informationLabel=new JLabel("Badly Control terminate!");
        this.add(informationLabel);
        informationLabel.setBounds(210, 20, 80, 200);
        
        
        try {
            logo = ImageIO.read(new File(PaintHelperBasics.getUserDir()+ "com/wpi/cs509/teamA/resource/logo_iteration1.png"));
            picLabel = new JLabel(new ImageIcon(logo));
            picLabel.setBounds(0,20,200, 200);
            this.add(picLabel);
            picLabel.setOpaque(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
