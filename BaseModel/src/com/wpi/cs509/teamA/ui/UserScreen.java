package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.dao.impl.InitAllMatrixImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This is the class that construct the main user interface of the application
 * @author JLou
 *
 */

public class UserScreen extends JFrame{
	
	private static UserScreen userScreen;

	private Container container;
	
	private ImageComponent imgPanel;
	private JScrollPane imgSp;
	/*
	 * A JPanel that have input text fields and buttons
	 * which will be shown on the top of the UI
	 */
	private InputPanel inputPanel;

	/**
	 * Initialize the user screen, constructor
	 */
	private UserScreen() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		inputPanel = new InputPanel();

		// the panel to show image
		imgPanel = new ImageComponent(inputPanel);
		
		// display the image
		imgPanel.setImagePath(System.getProperty("user.dir") + "\\src\\CSP.jpg");
		imgPanel.setPreferredSize(new Dimension(imgPanel.getImgWidth(), imgPanel.getImgHeight()));
		imgPanel.setVisible(true);
		// add listener
		imgPanel.addMouseListener(imgPanel);
		
		// scroll panel
		imgSp = new JScrollPane();
		imgSp.setPreferredSize(new Dimension(imgPanel.getImgWidth(), imgPanel.getImgHeight()));
		// for scroll panel
		imgSp.setViewportView(imgPanel);
		imgSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		container.add(imgSp, BorderLayout.CENTER);
		container.add(inputPanel, BorderLayout.NORTH);

		setTitle("Path Finding");
		setLocation(0, 0);
		setSize(1200, 900);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static UserScreen launchUserScreen(){
		if( userScreen == null ){
			userScreen = new UserScreen();
		}
		
		return userScreen;
	}

	/**
	 * The start of the program
	 * @param args command line..
	 */
	public static void main(String[] args) {
		
		// initialize all the matrix for the map
		InitAllMatrixImpl.getInitAllMatrixImpl();
		// singleton
		UserScreen.launchUserScreen();
	}
	
}