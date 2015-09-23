package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserScreen {

	private JFrame frame;
	private JTextField startPoint;
	private JTextField endPoint;
	private JButton btnSearch;
	private JPanel northPanel;

	public UserScreen() {
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
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new UserScreen();
	}

}
