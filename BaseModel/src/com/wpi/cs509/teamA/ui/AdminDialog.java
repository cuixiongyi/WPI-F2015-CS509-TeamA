package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This is the class that administrators uses to log in
 * 
 * @author CS 509-Team A
 *
 */
public class AdminDialog extends JDialog implements ActionListener {

	private JPanel contentPanel;
	private JTextField userName;
	private JPasswordField passwordField;
	private JLabel lbUserName;
	private JLabel lbPassword;
	private JButton okButton;
	private JButton cancelButton;
	private ImageComponent imgPanel;
	private InputPanel inputPanel;

    public StateContext getStateContext() {
        return stateContext;
    }

    public void setStateContext(StateContext stateContext) {
        this.stateContext = stateContext;
    }

    private StateContext stateContext;

	private final static String OK = "OK";
	private final static String CANCEL = "Cancel";
	private final static String LOG = "Login";
	private final static String LOGOUT = "Log out";

	// private JFrame controllingFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the dialog.
	 */
	public AdminDialog(ImageComponent imageComponent, InputPanel inputPanel) {
		this.imgPanel = imageComponent;
		this.inputPanel = inputPanel;
		setTitle(LOG);
		
		contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbUserName = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        contentPanel.add(lbUserName, cs);
        
        userName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        contentPanel.add(userName, cs);
        
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        contentPanel.add(lbPassword, cs);

        passwordField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        contentPanel.add(passwordField, cs);
        contentPanel.setBorder(new LineBorder(Color.GRAY));
		
		
//		// OK and CANCEL button block
		JPanel buttonPane = new JPanel();
		okButton = new JButton(OK);
		okButton.setActionCommand(OK);
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton(CANCEL);
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
        

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocation(100,100);

	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(CANCEL))
			AdminDialog.this.setVisible(false);
		if (e.getActionCommand().equals(OK)) {
			// Check password
			if (isPasswordCorrect(getUsername(),getPassword())) {
				successfulLogin();			
				dispose();
				}
		}
	}

	public void successfulLogin() {
		AdminDialog.this.setVisible(false);
		inputPanel.incrementAdminClicked();
		inputPanel.getBtnNeighborManage().setVisible(true);
		inputPanel.getAdminLogin().setText(LOGOUT);
		inputPanel.getBtnSynchronize().setVisible(true);
		inputPanel.getBtnMngEdge().setVisible(true);
		inputPanel.getBtnMngNode().setVisible(true);	
		stateContext.switchToState(new MouseActionAdminUser(stateContext));
		imgPanel.repaint();
	}

	/**
	 * Checks the passed-in array against the correct password. After this
	 * method returns, you should invoke eraseArray on the passed-in array.
	 */
	private static boolean isPasswordCorrect(String username,String password) {
		if (username.equals("wpi") && password.equals("goodjob")) {
            return true;	
		}
		else 
			return false;

	}

	
	public String getUsername() {
        return userName.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

	protected void resetFocus() {
		passwordField.requestFocusInWindow();
	}
}