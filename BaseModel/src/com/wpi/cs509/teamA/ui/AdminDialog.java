package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JTextField;

/**
 * This is the class that administrators uses to log in
 * 
 * @author CS 509-Team A
 *
 */
public class AdminDialog extends JDialog implements ActionListener {

	private JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JButton okButton;
	private JButton cancelButton;
	private ImageComponent imgPanel;
	private UserScreen userScreen;
	private InputPanel inputPanel;

	private final static String OK = "OK";
	private final static String CANCEL = "Cancel";
	private final static String LOG = "Log in";
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Password block
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 22));
		lblPassword.setBounds(15, 72, 121, 32);
		contentPanel.add(lblPassword);

		passwordField = new JPasswordField(10);
		passwordField.setBounds(174, 76, 126, 28);
		passwordField.setColumns(10);
		contentPanel.add(passwordField);

		// OK and CANCEL button block
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton(OK);
		okButton.setActionCommand(OK);
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton(CANCEL);
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(CANCEL))
			AdminDialog.this.setVisible(false);
		if (e.getActionCommand().equals(OK)) {
			// Check password
			char[] input = passwordField.getPassword();
			if (isPasswordCorrect(input)) {
				JOptionPane.showMessageDialog(null, "Success! You typed the right password.");
				AdminDialog.this.setVisible(false);
				imgPanel.getStateContext().switchState(imgPanel, imgPanel.getNormalUserMouseListener(),
						imgPanel.getAdminMouseListener());
				imgPanel.incrementAdminClicked();
				inputPanel.getBtnNeighborManage().setVisible(true);
				inputPanel.getAdminLogin().setText(LOGOUT);
				ImageComponent.setIsAdmin(true);
				imgPanel.repaint();			
			} else {
				JOptionPane.showMessageDialog(null, "Invalid password. Try again.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				passwordField.selectAll();
				resetFocus();
			}
			// Zero out the possible password, for security.
			Arrays.fill(input, '0');

		}
	}

	/**
	 * Checks the passed-in array against the correct password. After this
	 * method returns, you should invoke eraseArray on the passed-in array.
	 */
	private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { '5', '0', '9' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Zero out the password.
		Arrays.fill(correctPassword, '0');

		return isCorrect;
	}

	protected void resetFocus() {
		passwordField.requestFocusInWindow();
	}
}