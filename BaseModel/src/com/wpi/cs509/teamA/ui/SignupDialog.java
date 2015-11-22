package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.wpi.cs509.teamA.bean.UserAccount;

public class SignupDialog extends JDialog implements ActionListener  {
	
//	private String userName;
//	private String passWord;
	private JPanel contentPanel;
	private JTextField userName;
	private JTextField email;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private boolean isAdmin;
	private JCheckBox adminCheck;
	private JLabel lbUserName;
	private JLabel lbEmail;
	private JLabel lbPassword;
	private JLabel lbPassword2;
	private JButton okButton;
	private JButton cancelButton;
	private ImageComponent imgPanel;
	private InputPanel inputPanel;
	
	public enum adminName{
		cui,zhaojun,nick,xiongkuang,yizhou,jie,stella
	}
	
	public SignupDialog(ImageComponent imageComponent, InputPanel inputPanel) 
	{
		this.imgPanel = imageComponent;
		this.inputPanel = inputPanel;
		setTitle("Sign Up");
		
		contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbUserName = new JLabel("Your Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        contentPanel.add(lbUserName, cs);
        
        userName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        contentPanel.add(userName, cs);
        
        lbEmail = new JLabel("Your Email: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        contentPanel.add(lbEmail, cs);
        
        email = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        contentPanel.add(email, cs);
        
        lbPassword = new JLabel("Your Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        contentPanel.add(lbPassword, cs);

        passwordField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        contentPanel.add(passwordField, cs);
        contentPanel.setBorder(new LineBorder(Color.GRAY));
        
        lbPassword2 = new JLabel("Verify Password: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        contentPanel.add(lbPassword2, cs);

        passwordField2 = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        contentPanel.add(passwordField2, cs);
        contentPanel.setBorder(new LineBorder(Color.GRAY));
        
//		// OK and CANCEL button block
		JPanel buttonPane = new JPanel();
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setActionCommand("CANCEL");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		adminCheck=new JCheckBox("Admin");
		buttonPane.add(adminCheck);
        
		
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocation(100,100);
	}
	
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("CANCEL"))
		{
			SignupDialog.this.setVisible(false);
			dispose();
		}
		if (e.getActionCommand().equals("OK")) {
			// Check password
			if (isPasswordSame(getPassword(passwordField),getPassword(passwordField2))) {
				SignupDialog.this.setVisible(false);
//				imgPanel.getStateContext().switchState(imgPanel, imgPanel.getNormalUserMouseListener(),
//						imgPanel.getAdminMouseListener());
//				imgPanel.incrementAdminClicked();
//				inputPanel.getBtnNeighborManage().setVisible(true);
//				inputPanel.getAdminLogin().setText("LOGOUT");
//				inputPanel.getBtnSynchronize().setVisible(true);
//				ImageComponent.setIsAdmin(true);
				saveAccount(getUsername(),getPassword(passwordField),getEmail(),isAdmin);
				imgPanel.repaint();			
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Password not the same. Try again.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				passwordField.selectAll();
				userName.setText("");
                passwordField.setText("");
				
			}
			// Zero out the possible password, for security.

		}
		
	}
	
	private void saveAccount(String username, String password, String email, boolean isAdmin)
	{
		UserAccount account=new UserAccount();
		account.setUsername(username);
		account.setPassword(password);
		account.setAdmin(isAdmin);
		
	}
	
	
	private static boolean isPasswordSame(String password1,String password2) {
		if (password1.equals(password2)) {
            return true;	
		}
		else 
			return false;

	}
	
	
	public boolean isAdmin(String username)
	{
		for(adminName admin : adminName.values())
		{
			if(username.equals(admin.toString()))
			{
				System.out.println("admin"+admin);
				return true;
				
			}
		}
		return false;
	}
	
	
	
	public String getUsername() {
        return userName.getText().trim();
    }
	
	public String getEmail() {
        return email.getText().trim();
    }

    public String getPassword(JPasswordField passwordField) {
        return new String(passwordField.getPassword());
    }

}
