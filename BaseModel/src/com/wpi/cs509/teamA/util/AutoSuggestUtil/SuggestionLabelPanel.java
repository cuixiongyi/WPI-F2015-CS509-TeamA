//package com.wpi.cs509.teamA.util.AutoSuggestUtil;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//
//import javax.swing.AbstractAction;
//import javax.swing.Action;
//import javax.swing.BorderFactory;
//import javax.swing.DefaultListModel;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JComponent;
//import javax.swing.JLabel;
//import javax.swing.KeyStroke;
//
//import com.wpi.cs509.teamA.bean.Node;
//import com.wpi.cs509.teamA.dao.impl.OtherFeatureDaoImpl;
//import com.wpi.cs509.teamA.ui.view.InputPanel;
//import com.wpi.cs509.teamA.util.MarioListRenderer;
//
//
//public class SuggestionLabelPanel extends SuggestionBasicPanel {
////	private InputPanel inputPanel;
//	private String displayName;
//	public SuggestionLabelPanel(String string, AutoSuggestor autoSuggestor, Node node, String displayName) {
//		super(string, autoSuggestor, node);
//	//	this.inputPanel = inputPanel;
//		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Labels,this);
//		OtherFeatureDaoImpl nodeList = new OtherFeatureDaoImpl();
//		this.displayName = displayName;
//		
//	   ArrayList<Node> labelResult = (ArrayList<Node>) nodeList.getListofNodesWithLabel(displayName);
//	   
//		this.setNodeInformation(labelResult);
//		// TODO Auto-generated constructor stub
//		this.initComponent();
//	}
//	
//	protected void initComponent() {
//		
//		this.add(textLabel);	
//		this.setBorder(BorderFactory.createLineBorder(suggestionLineBorderColor));
//		
//		textLabel.setFocusable(true);
//		textLabel.setForeground(suggestionsTextColor);
//		textLabel.setPreferredSize(preferredSize);
//		textLabel.setFont(font);
//		
//		
//		
//		addMouseListener(mouseClicked());
//
//		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
//		getActionMap().put("Enter released", keyboardEnter());
//	}
//	
//	public MouseAdapter mouseClicked(){
//		
//		return new MouseAdapter(){
//			public void mouseClicked(MouseEvent me) {
//				super.mouseClicked(me);
//
//				replaceWithSuggestedText();
//
//				autoSuggestionsPopUpWindow.setVisible(false);
//			}
//		
//		};
//	}
//	
//	private Action keyboardEnter() {
//		return new AbstractAction() {
//			/**
//			 *
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				replaceWithSuggestedText();
//				autoSuggestionsPopUpWindow.setVisible(false);
//			}
//		};
//	}
//	
////	void replaceWithSuggestedText() {
////		String suggestedWord = textLabel.getText();
////		String text = textField.getText();	
////		DefaultListModel<String> mapListModel = new DefaultListModel<>();
////		for(Node node: nodeInformation){
////				autoSuggestor.getModel().setEndNode(node);	
////				mapListModel.addElement(node.getName());
////		}
////		inputPanel.getMapList().setCellRenderer(new SameLabelListCellRenderer(SuggestorPainter.getIconFromName(displayName)));
////		
////		inputPanel.getMapList().setVisible(true);
////		inputPanel.getMapList().removeAll();
////		inputPanel.getMapList().setModel(mapListModel);
////		inputPanel.getMapList().setEnabled(false);
////		
////		String typedWord = autoSuggestor.getCurrentlyTypedWord();
////		String t = textField.getText().substring(0, textField.getText().lastIndexOf(typedWord));
////		String tmp = t + textField.getText().substring(textField.getText().lastIndexOf(typedWord)).replace(typedWord, suggestedWord);
////		textField.setText(tmp);
////	}
//
//	
//	
//
//}
