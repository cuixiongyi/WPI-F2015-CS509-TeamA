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

/**
 * Created by cuixi on 12/4/2015.
 */

public class SuggestionBasicPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean focused = false;
	private final JWindow autoSuggestionsPopUpWindow;
	private final JTextField textField;
	private final AutoSuggestor autoSuggestor;
	private Color suggestionsTextColor, suggestionBorderColor;
	private Dimension preferredSize;
	private Font font;
	private Node nodeInformation;
	private BufferedImage imageIcon;

	private JLabel textLabel;

	public SuggestionBasicPanel(String string, AutoSuggestor autoSuggestor, Node node) {
		// super(string);
		this.setLayout(new BorderLayout());
		this.textLabel = new JLabel(string, JLabel.LEFT);		
		this.add(textLabel, BorderLayout.WEST);	
		this.add(new JLabel(new ImageIcon(imageIcon), JLabel.RIGHT), BorderLayout.EAST);
		this.setBorder(BorderFactory.createLineBorder(Color.blue));

		this.autoSuggestor = autoSuggestor;
		this.textField = autoSuggestor.getTextField();

		this.autoSuggestionsPopUpWindow = autoSuggestor.getAutoSuggestionPopUpWindow();

		this.nodeInformation = node;

		
	}

	protected void initComponent() {
		textLabel.setFocusable(true);
		textLabel.setForeground(suggestionsTextColor);
		textLabel.setPreferredSize(preferredSize);
		textLabel.setFont(font);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);

				replaceWithSuggestedText();

				autoSuggestionsPopUpWindow.setVisible(false);
			}
		});

		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
		getActionMap().put("Enter released", new AbstractAction() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				replaceWithSuggestedText();
				autoSuggestionsPopUpWindow.setVisible(false);
			}
		});
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

	private void replaceWithSuggestedText() {
		String suggestedWord = textLabel.getText();
		String text = textField.getText();
		if (AutoSuggestor.SetNodeOption.setStartNode == autoSuggestor.getSetNodeOption()) {
			autoSuggestor.getModel().setStartNode(nodeInformation);
		} else if (AutoSuggestor.SetNodeOption.setEndNode == autoSuggestor.getSetNodeOption()) {
			autoSuggestor.getModel().setEndNode(nodeInformation);
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

	public Node getNodeInformation() {
		return nodeInformation;
	}

	public void setNodeInformation(Node nodeInformation) {
		this.nodeInformation = nodeInformation;
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



	public void setImageIcon(BufferedImage locationIcon) {
		this.imageIcon = locationIcon;
	}

}
