package com.wpi.cs509.teamA.ui.view;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.*;
import com.wpi.cs509.teamA.ui.Dialog.AdminDialog;
import com.wpi.cs509.teamA.ui.Dialog.SignupDialog;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;
import com.wpi.cs509.teamA.util.AutoSuggestUtil.AutoSuggestor;


/**
 * JPanel that have input text fields and buttons which will be shown on the top
 * of the UI
 * 
 * TODO: we need a singleton here..
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel implements ActionListener, FocusListener {



    private MainModel model = null;

    private JButton btnSearch;
    private JButton adminLogin;
    private JButton signUp;
    private JLabel lblFrom;
    private JLabel lblTo;
    private JButton btnNeighborManage;
    private JButton btnSynchronize;
    private JComboBox<String> comboBoxMap;





    private static final String SEARCHWORD = "Search WPI Maps";
    private UserScreen userScreen;
    private AutoSuggestor autoSuggestorFrom;
    private AutoSuggestor autoSuggestorTo;
    private boolean lastSetEmptySearchWord = false;
    private boolean lastSetSearchWord = false;

    private DefaultListModel<String> mapListModel = new DefaultListModel<>();
    private JList<String> mapList;
    private ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();
    private JLabel picLabel;


    private int adminClicked = 0;

    private JTextField txtFrom;
    private JTextField txtTo;

    /**
     * Constructor. Initialize all the input panel.
     */
    public InputPanel() {
        // // User input block

        this.btnSearch = new JButton(UIConstant.SEARCH);
        this.adminLogin = new JButton(UIConstant.LOGIN);
        this.btnNeighborManage = new JButton("Edges");
        btnNeighborManage.setSize(75, 30);
        btnNeighborManage.setLocation(80, 380);
        this.getBtnLogin().setFont(new Font("Arial", Font.PLAIN, 12));
        this.btnNeighborManage.setVisible(false);

        this.setLayout(null);
        this.add(btnSearch);
        this.add(adminLogin);
        this.add(btnNeighborManage);

        this.getBtnLogin().setBounds(150, 0, 75, 30);
        this.getBtnLogin().setFont(new Font("Arial", Font.PLAIN, 12));
        this.getBtnLogin().addActionListener(this);

        this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
        //this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
        //this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));
        this.getBtnLogin().setBounds(150, 0, 75, 30);
        this.getBtnSearch().setBounds(80, 300, 150, 38);
        this.getBtnSearch().addActionListener(this);

        // this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
        // this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));

        this.signUp = new JButton("SignUp");
        this.add(signUp);
        this.signUp.addActionListener(this);
        this.signUp.setBounds(80, 0, 75, 30);

        // this.getStartPoint().setBounds(80, 150, 150, 38);
        // this.setBounds(0, 0, 1178, 516);

        lblFrom = new JLabel(UIConstant.FROM);
        lblFrom.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFrom.setBounds(15, 155, 61, 16);
        add(lblFrom);

        lblTo = new JLabel(UIConstant.TO);
        lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTo.setBounds(15, 230, 61, 16);
        add(lblTo);

        comboBoxMap = new JComboBox<String>();
        comboBoxMap.setBounds(80, 55, 150, 30);
        List<GeneralMap> allMapList =Database.getAllMapFromDatabase();
        for(GeneralMap map:allMapList) {
            comboBoxMap.addItem(map.getMapAbbrName());
        }

        comboBoxMap.setMaximumRowCount(2);
        comboBoxMap.addFocusListener(this);
        comboBoxMap.requestFocus();
        add(comboBoxMap);

        JLabel lblMap = new JLabel("Map: ");
        lblMap.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMap.setBounds(15, 60, 61, 21);
        add(lblMap);

        txtFrom = new JTextField();
        txtFrom.setBounds(80, 150, 150, 27);
        txtFrom.setText(SEARCHWORD);
        add(txtFrom);
        txtFrom.addFocusListener(this);
        txtFrom.setColumns(10);
        txtTo = new JTextField();
        txtTo.setBounds(80, 225, 150, 27);
        txtTo.addFocusListener(this);
        txtTo.setText(SEARCHWORD);
        add(txtTo);
        txtTo.setColumns(10);




        btnSynchronize = new JButton("Sync");
        btnSynchronize.addActionListener(this);
        btnSynchronize.setVisible(false);
        btnSynchronize.setBounds(155, 380, 75, 30);
        add(btnSynchronize);

        BufferedImage logo;
        try {
            logo = ImageIO.read(new File(PaintHelper.getUserDir()+ "logo_iteration1.png"));
//            picLabel = new JLabel(new ImageIcon(logo));
//            picLabel.setBounds(50, 480, 200, 200);
//            add(picLabel);
//            picLabel.setOpaque(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //result list
        mapList = new JList<>();
        mapList.setVisible(false);
        JScrollPane mapListScroll = new JScrollPane(mapList);
        mapListScroll.setBounds(50, 480, 200, 200);
        add(mapListScroll);


      
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() == txtFrom || e.getSource() == txtTo) {
            if (((JTextField) e.getSource()).getText().trim().equals("")) {
                //lastSetSearchWord = true;
                //lastSetEmptySearchWord = false;
                ((JTextField) e.getSource()).setText(SEARCHWORD);
            }

        }else if(e.getSource()==comboBoxMap){
            this.autoSuggestorFrom.getAutoSuggestionPopUpWindow().setVisible(false);
            this.autoSuggestorTo.getAutoSuggestionPopUpWindow().setVisible(false);

        }
    }

    private void processTextField(JTextField txt) {
        if (txt.getText().trim().equals(SEARCHWORD) ) {
           // if (!lastSetSearchWord) {
                //lastSetEmptySearchWord = true;
                //lastSetSearchWord = false;
                txt.setText("");
          //  }
        }
      /*  else if (lastSetSearchWord){
            lastSetSearchWord = false;
            lastSetEmptySearchWord = false;
        }*/
    }
    public void focusGained(FocusEvent e) {
        if (e.getSource() == txtFrom || e.getSource() == txtTo) {
            processTextField((JTextField)e.getSource());


        }else if(e.getSource()==comboBoxMap){
            this.autoSuggestorFrom.getAutoSuggestionPopUpWindow().setVisible(false);
            this.autoSuggestorTo.getAutoSuggestionPopUpWindow().setVisible(false);

        }

    }


	

	

	/**
	 * This is Button click event
	 * @param e
     */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TODO: we need a pop up window here to verify the admin role.
		// If it is the admin, give it the admin mouse click event. If
		// not, give it normal user
		if (e.getSource() == getBtnLogin()) {
		}
		if (e.getSource() == signUp) {
			
		}
		if(e.getSource()==getBtnSearch())
		{

		}
	

	}
	

	
	public void incrementAdminClicked() {
		this.adminClicked++;
	}



	public JButton getBtnSynchronize() {
		return btnSynchronize;
	}

	public JComboBox<String> getComboBoxMap() {
		return comboBoxMap;
	}

	/**
	 * @return the btnSearch
	 */
	public JButton getBtnSearch() {
		return btnSearch;
	}

	/**
	 * @param btnSearch
	 *            the btnSearch to set
	 */
	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	/**
	 * @return the adminLogin
	 */
	public JButton getBtnLogin() {
		return adminLogin;
	}

	/**
	 * @param adminLogin
	 *            the adminLogin to set
	 */
	public void setAdminLogin(JButton adminLogin) {
		this.adminLogin = adminLogin;
	}

	/**
	 * @return the btnNeighborManage
	 */
	public JButton getBtnNeighborManage() {
		return btnNeighborManage;
	}

	public JButton getBtnSignUp() {
		return signUp;
	}

	public void setSignUp(JButton signUp) {
		this.signUp = signUp;
	}

	public JList<String> getMapList() {
		return mapList;
	}

	public void setMapList(JList<String> mapList) {
		this.mapList = mapList;
	}

    public void setUserScreen(UserScreen userScreen2) {
        this.userScreen = userScreen2;

        autoSuggestorFrom = new AutoSuggestor(txtFrom, userScreen, null, Color.WHITE.brighter(), Color.BLACK,
                Color.GRAY, 0.75f, 0.0, 0.0, model, AutoSuggestor.SetNodeOption.setStartNode);
        autoSuggestorFrom.setInputPanel(this);
        autoSuggestorTo = new AutoSuggestor(txtTo, userScreen, null, Color.WHITE.brighter(), Color.BLACK,
                Color.GRAY, 0.75f, 0.0, 0.0, model, AutoSuggestor.SetNodeOption.setEndNode);
        autoSuggestorTo.setInputPanel(this);
    }

    public AutoSuggestor getAutoSuggestorFrom() {
        return autoSuggestorFrom;
    }
    public AutoSuggestor getAutoSuggestorTo() {
        return autoSuggestorTo;
    }
    public JTextField getToText(){
        return txtTo;
    }

    public JTextField getFromText(){
        return txtFrom;
    }


    public static String getSEARCHWORD() {
        return SEARCHWORD;
    }
    public void setModel(MainModel pmodel) {
        this.model = pmodel;
    }
};
