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

public class UserScreen {

	private JFrame frame;
	private JTextField startPoint;
	private JTextField endPoint;
	private JButton btnSearch;
	private JPanel northPanel;

	public UserScreen() {
		
		initUserScreen();
		addListeners();
		
		// maybe we can use facade pattern here?
		// https://en.wikipedia.org/wiki/Facade_pattern
		
	}

	
	/**
	 * This is the start of our program?
	 * Do we need a Login page first?
	 * Or have an button for admin Login?
	 */
	public static void main(String[] args) {
		new UserScreen();
	}
	
	private void initUserScreen(){
		startPoint = new JTextField("Start");
		endPoint = new JTextField("End");
		btnSearch = new JButton("Search");

		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 7));
		northPanel.add(new JLabel("From: "));
		northPanel.add(startPoint);
		northPanel.add(new JLabel("To: "));
		northPanel.add(endPoint);
		northPanel.add(btnSearch);
				
		frame = new JFrame("Path Finding");
		frame.setLayout(new BorderLayout());
		frame.add(northPanel, "North");
		frame.setSize(600, 400);
		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screen_width - frame.getWidth()) / 2, (screen_height - frame.getHeight()) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void addListeners(){
		
		// search
		btnSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// check what kind of two places are
				// proxy design pattern?
				// do search work here?
				// let the controller decide which algorithm will be called?
				AlogController controller = new AlogController(startPoint.getText().trim(), endPoint.getText().trim());
				List<Node> route = new ArrayList<Node>();
				route = controller.getRoute();
				// how to make feedback to the image? more discussion..
				
				
				
			}
			
		});
	}

}




