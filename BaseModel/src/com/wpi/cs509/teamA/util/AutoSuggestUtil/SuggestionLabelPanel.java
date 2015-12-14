package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.impl.OtherFeatureDaoImpl;

public class SuggestionLabelPanel extends SuggestionBasicPanel {

	public SuggestionLabelPanel(String string, AutoSuggestor autoSuggestor, Node node, String displayName) {
		super(string, autoSuggestor, node);
		
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Labels,this);
		OtherFeatureDaoImpl nodeList = new OtherFeatureDaoImpl();
		System.out.println(nodeList);
		
	   ArrayList<Node> labelResult = (ArrayList<Node>) nodeList.getListofNodesWithLabel(displayName);
	   
		this.setNodeInformation(labelResult);
		// TODO Auto-generated constructor stub
		this.initComponent();
	}
	
	protected void initComponent() {
		
		this.add(textLabel);	
		this.setBorder(BorderFactory.createLineBorder(suggestionLineBorderColor));
		
		textLabel.setFocusable(true);
		textLabel.setForeground(suggestionsTextColor);
		textLabel.setPreferredSize(preferredSize);
		textLabel.setFont(font);
		
		
		
		addMouseListener(mouseClicked());

		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
		getActionMap().put("Enter released", keyboardEnter());
	}
	
	public MouseAdapter mouseClicked(){
		
		return new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				replaceWithSuggestedText();

				autoSuggestionsPopUpWindow.setVisible(false);
			}
		
		};
	}
	
	private Action keyboardEnter() {
		return new AbstractAction() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				replaceWithSuggestedText();
				autoSuggestionsPopUpWindow.setVisible(false);
			}
		};
	}

	
	

}
