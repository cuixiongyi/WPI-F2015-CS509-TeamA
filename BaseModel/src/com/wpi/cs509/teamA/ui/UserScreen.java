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

import com.wpi.cs509.teamA.controller.AlogController;
import com.wpi.cs509.teamA.entities.Node;

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

public class UserScreen extends JFrame{

	private Container container;
	
	private ImagePanel imgPanel;
	private JScrollPane imgSp;
	
	private JPanel inputPanel;
	private JTextField startPoint;
	private JTextField endPoint;
	private JButton btnSearch;
	private JButton adminLogin;

	private UserScreen() {
		container = getContentPane();
		container.setLayout(new BorderLayout());

		imgPanel = new ImagePanel();
		imgPanel.setImagePath(System.getProperty("user.dir") + "\\src\\CSP.jpg");
		imgPanel.setPreferredSize(new Dimension(imgPanel.getImgWidth(), imgPanel.getImgHeight()));
		imgPanel.setVisible(true);
		
		// add an implementation class
		// imgPanel.addMouseListener(new MouseEventImpl());
		imgPanel.addMouseListener(imgPanel);
		
		imgSp = new JScrollPane();
		imgSp.setPreferredSize(new Dimension(imgPanel.getImgWidth(), imgPanel.getImgHeight()));
		// for scroll panel
		imgSp.setViewportView(imgPanel);
		imgSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// button panel
		startPoint = new JTextField("Start");
		endPoint = new JTextField("End");
		btnSearch = new JButton("Search");
		adminLogin = new JButton("Login as Admin");
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(1, 7));
		inputPanel.add(new JLabel("From: "));
		inputPanel.add(startPoint);
		inputPanel.add(new JLabel("To: "));
		inputPanel.add(endPoint);
		inputPanel.add(btnSearch);
		inputPanel.add(adminLogin);

		container.add(imgSp, BorderLayout.CENTER);
		container.add(inputPanel, BorderLayout.NORTH);
		
		btnSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// check what kind of two places are
				// proxy design pattern?
				// do search work here?
				// let the controller decide which algorithm will be called?
				// AlogController controller = new AlogController(startPoint.getText().trim(), endPoint.getText().trim());
				// List<Node> route = new ArrayList<Node>();
				// route = controller.getRoute();
				// how to make feedback to the image? more discussion..
				
				// we draw two demon lines here
				BufferedImage sourceImage = (BufferedImage) imgPanel.getImage();
				Graphics2D g2 = (Graphics2D) sourceImage.getGraphics();
				g2.setPaint(Color.white);
				g2.draw(new Line2D.Double(10,10,600,10));
				g2.draw(new Line2D.Double(10,80,600,80));
				repaint();
				
			}
			
		});

		setTitle("Path Finding");
		setLocation(0, 0);
		setSize(1200, 900);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		// just use sigleton here
		new UserScreen();
	}
	
}