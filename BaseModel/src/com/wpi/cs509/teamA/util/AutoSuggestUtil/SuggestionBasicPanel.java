package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import com.wpi.cs509.teamA.bean.Node;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by cuixi on 12/4/2015.
 */

public class SuggestionBasicPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean focused = false;
	final JWindow autoSuggestionsPopUpWindow;
	protected final JTextField textField;
	protected final AutoSuggestor autoSuggestor;
	protected Color suggestionsTextColor;
	private Color suggestionBorderColor;
	protected Color suggestionLineBorderColor;
	protected Dimension preferredSize;
	protected Font font;
	protected ArrayList<Node> nodeInformation;
	protected BufferedImage imageIcon;
	

	protected JLabel textLabel;

	public SuggestionBasicPanel(String string, AutoSuggestor autoSuggestor, Node node) {
		// super(string);
		this.setLayout(new BorderLayout());
		this.textLabel = new JLabel(string, JLabel.LEFT);

		this.autoSuggestor = autoSuggestor;
		this.textField = autoSuggestor.getTextField();

		this.autoSuggestionsPopUpWindow = autoSuggestor.getAutoSuggestionPopUpWindow();

		nodeInformation = new ArrayList<Node>();
		nodeInformation.add(node);
		

		
	}

	protected void initComponent() {
		this.add(new JLabel(new ImageIcon(imageIcon), JLabel.LEFT), BorderLayout.WEST);	
		this.add(textLabel, BorderLayout.CENTER);	
		//this.add(new JLabel(new ImageIcon(imageIcon), JLabel.LEFT), BorderLayout.WEST);
		this.setBorder(BorderFactory.createLineBorder(suggestionLineBorderColor));
	//	this.setPreferredSize(new Dimension(800,30));		
		textLabel.setFocusable(true);
		textLabel.setForeground(suggestionsTextColor);
		textLabel.setPreferredSize(preferredSize);
		textLabel.setFont(font);
		
		
		
		addMouseListener(mouseClicked());

		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
		getActionMap().put("Enter released", keyboardEnter());
	}

	

	public void setFocused(boolean focused) {
		if (focused) {
			setBorder(new LineBorder(suggestionBorderColor));
		} else {
			setBorder(null);
		}
		repaint();
		this.focused = focused;
	}

	public boolean isFocused() {
		return focused;
	}

	void replaceWithSuggestedText() {
		String suggestedWord = textLabel.getText();
		String text = textField.getText();
		if (AutoSuggestor.SetNodeOption.setStartNode == autoSuggestor.getSetNodeOption()) {
			autoSuggestor.getModel().setStartNode(nodeInformation.get(0));
		} else if (AutoSuggestor.SetNodeOption.setEndNode == autoSuggestor.getSetNodeOption()) {
//			if(nodeInformation.size()==1){
//				autoSuggestor.getModel().setOneEndNode(nodeInformation.get(0));
//			}else{
//				autoSuggestor.getModel().setEndNearestNodes(nodeInformation);
//			}
			autoSuggestor.getModel().setOneEndNode(nodeInformation.get(0));
		}

		String typedWord = autoSuggestor.getCurrentlyTypedWord();
		String t = text.substring(0, text.lastIndexOf(typedWord));
		String tmp = t + text.substring(text.lastIndexOf(typedWord)).replace(typedWord, suggestedWord);
		textField.setText(tmp);
	}

	public Color getSuggestionsTextColor() {
		return suggestionsTextColor;
	}

	public void setSuggestionsTextColor(Color suggestionsTextColor) {
		this.suggestionsTextColor = suggestionsTextColor;
	}

	public Color getSuggestionBorderColor() {
		return suggestionBorderColor;
	}

	public void setSuggestionBorderColor(Color suggestionBorderColor) {
		this.suggestionBorderColor = suggestionBorderColor;
	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public void setPreferredSize(Dimension preferredSize) {
		this.preferredSize = preferredSize;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}



	public JLabel getTextLabel() {
		return textLabel;
	}

	public void setTextLabel(JLabel textLabel) {
		this.textLabel = textLabel;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setSuggestionTextColor(Color suggestionsTextColor) {
		// TODO Auto-generated method stub
		this.suggestionsTextColor = suggestionsTextColor;
	}



	/**
	 * @return the nodeInformation
	 */
	public ArrayList<Node> getNodeInformation() {
		return nodeInformation;
	}

	/**
	 * @param nodeInformation the nodeInformation to set
	 */
	public void setNodeInformation(ArrayList<Node> nodeInformation) {
		this.nodeInformation = nodeInformation;
	}

	public void setImageIcon(BufferedImage locationIcon) {
		this.imageIcon = locationIcon;
	}

	public Color getSuggestionLineBorderColor() {
		return suggestionLineBorderColor;
	}

	public void setSuggestionLineBorderColor(Color suggestionLineBorderColor) {
		this.suggestionLineBorderColor = suggestionLineBorderColor;
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
