package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionAdminUser;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

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

    private MainModel model;

	private final static String OK = "OK";
	private final static String CANCEL = "Cancel";
	private final static String LOG = "Login";
	private final static String LOGOUT = "Log out";



	/**
	 * Create the dialog.
	 */
	public AdminDialog(MainModel pModel, InputPanel inputPanel) {
		this.model = pModel;
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
			else {
                //TODO add a warning box for wrong password
				JOptionPane.showMessageDialog(null, "Username or password error.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				passwordField.selectAll();
				passwordField.setText("");
				userName.setText("");
				
            }
		}
	}

	public void successfulLogin() {
		AdminDialog.this.setVisible(false);
		inputPanel.incrementAdminClicked();
	//	inputPanel.getBtnNeighborManage().setVisible(true);
		inputPanel.getAdminLogin().setText(LOGOUT);
//		inputPanel.getBtnSynchronize().setVisible(true);
//		inputPanel.getBtnMngEdge().setVisible(true);
//		inputPanel.getBtnMngNode().setVisible(true);	
		model.switchToState(new MouseActionAdminUser(model));
		//stateContext.switchUserState(new AdminUserState(stateContext));
		ViewManager.updateView();
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