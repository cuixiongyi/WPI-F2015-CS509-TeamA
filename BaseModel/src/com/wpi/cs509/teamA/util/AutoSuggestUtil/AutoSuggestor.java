package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.newfeature.SearchSupply;
import com.wpi.cs509.teamA.ui.view.InputPanel;

public class AutoSuggestor {

	private final JTextField textField;
	private final JFrame container;
	private JPanel suggestionsPanel;
	private JWindow autoSuggestionPopUpWindow;
	private String typedWord;
	private final ArrayList<String> dictionary = new ArrayList<>();
	private int currentIndexOfSpace, tW, tH;
	private double windowLocationX;
	private double windowLocationY;
	private InputPanel inputPanel;
	private DocumentListener documentListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent de) {
			checkForAndShowSuggestions();
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			checkForAndShowSuggestions();
		}

		@Override
		public void changedUpdate(DocumentEvent de) {
			checkForAndShowSuggestions();
		}
	};
	private final Color suggestionsTextColor;
	private final Color suggestionFocusedColor;
	private final Dimension preferredSize = new Dimension(1000, 23);
	private final Font labelFont = new Font("SimSun", Font.PLAIN, 23);
	private int lastFocusableIndex = 0;

	/**
	 * this is a hack To differetia different to set StartNode or EndNode
	 */
	public enum SetNodeOption {
		Undefined, setStartNode, setEndNode,
	}

	private SetNodeOption setNodeOption = SetNodeOption.Undefined;

	private MainModel model = null;

	public AutoSuggestor(JTextField textField, JFrame container, ArrayList<String> words, Color popUpBackground,
						 Color textColor, Color suggestionFocusedColor, float opacity, double locationX, double locationY,
						 MainModel pModel, SetNodeOption pSetNodeOption) {
		this.textField = textField;
		this.suggestionsTextColor = textColor;
		this.container = container;
		this.suggestionFocusedColor = suggestionFocusedColor;
		this.windowLocationX = locationX;
		this.windowLocationY = locationY;

		this.textField.getDocument().addDocumentListener(documentListener);
		this.model = pModel;

		// setDictionary(words);

		typedWord = "";
		currentIndexOfSpace = 0;
		tW = 0;
		tH = 0;

		autoSuggestionPopUpWindow = new JWindow(container);

		// System.out.println(UserScreen.getUserScreen());
		autoSuggestionPopUpWindow.setOpacity(opacity);

		suggestionsPanel = new JPanel();
		suggestionsPanel.setLayout(new GridLayout(0, 1));
		suggestionsPanel.setBackground(popUpBackground);

		addKeyBindingToRequestFocusInPopUpWindow();

		setNodeOption = pSetNodeOption;
	}

	private void addKeyBindingToRequestFocusInPopUpWindow() {
		textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true),
				"ESC released");
		textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),
				"Down released");
		suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
		suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "Up released");
		suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "ESC released");
		textField.getActionMap().put("ESC released", escAction());
		suggestionsPanel.getActionMap().put("Up released", upAction());
		textField.getActionMap().put("Down released", txtDownAction());
		suggestionsPanel.getActionMap().put("Down released", downAction());
		suggestionsPanel.getActionMap().put("ESC released", escAction());

	}

	private Action txtDownAction() {
		return new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {// focuses the first
															// label on
															// popwindow
				for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
					if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
						((SuggestionLabel) suggestionsPanel.getComponent(i)).setFocused(true);
						autoSuggestionPopUpWindow.toFront();
						autoSuggestionPopUpWindow.requestFocusInWindow();
						suggestionsPanel.requestFocusInWindow();
						suggestionsPanel.getComponent(i).requestFocusInWindow();
						break;
					}
				}
			}
		};
	}

	private Action escAction() {
		// TODO Auto-generated method stub
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AutoSuggestor.this.getAutoSuggestionPopUpWindow().setVisible(false);
			}

		};

	}

	private void setFocusToTextField() {
		container.toFront();
		container.requestFocusInWindow();
		textField.requestFocusInWindow();
	}

	public ArrayList<SuggestionLabel> getAddedSuggestionLabels() {
		ArrayList<SuggestionLabel> sls = new ArrayList<>();
		for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
			if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
				SuggestionLabel sl = (SuggestionLabel) suggestionsPanel.getComponent(i);
				sls.add(sl);
			}
		}
		return sls;
	}

	private void checkForAndShowSuggestions() {
		typedWord = getCurrentlyTypedWord();
		if (typedWord == InputPanel.getSEARCHWORD())
		{
			return;
		}
		suggestionsPanel.removeAll();// remove previos words/jlabels that were
										// added

		// used to calcualte size of JWindow as new Jlabels are added
		tW = 0;
		tH = 0;

		boolean added = checkWord(typedWord);

		if (!added) {
			if (autoSuggestionPopUpWindow.isVisible()) {
				autoSuggestionPopUpWindow.setVisible(false);
			}
		} else {
			showPopUpWindow(windowLocationX, windowLocationY);
			setFocusToTextField();
		}
	}

	private Action upAction() {
		return new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			// int lastFocusableIndex = 0;
			int current;

			@Override
			public void actionPerformed(ActionEvent ae) {// allows scrolling of
															// labels in pop
															// window
				ArrayList<SuggestionLabel> sls = getAddedSuggestionLabels();
				int max = sls.size();
				System.out.println("up" + lastFocusableIndex);
				if (max > 1) {// more than 1 suggestion
					for (int i = lastFocusableIndex; i >= 0; i--) {
						SuggestionLabel sl = sls.get(i);
						if (sl.isFocused()) {

							if (lastFocusableIndex == 0) {
								sl.setFocused(false);
								autoSuggestionPopUpWindow.setVisible(false);
								setFocusToTextField();
								checkForAndShowSuggestions();// fire method as
																// if document
																// listener
																// change
																// occured and
																// fired it

							} else {
								sl.setFocused(false);
								lastFocusableIndex = i;
							}
						} else if (lastFocusableIndex > i) {
							if (i < max) {
								sl.setFocused(true);
								autoSuggestionPopUpWindow.toFront();
								autoSuggestionPopUpWindow.requestFocusInWindow();
								suggestionsPanel.requestFocusInWindow();
								suggestionsPanel.getComponent(i).requestFocusInWindow();
								lastFocusableIndex = i;
								break;
							}
						}
					}
				} else {// only a single suggestion was given
					autoSuggestionPopUpWindow.setVisible(false);
					setFocusToTextField();
					checkForAndShowSuggestions();// fire method as if document
													// listener change occured
													// and fired it
				}
			}
		};
	}

	private Action downAction() {
		return new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			// int lastFocusableIndex = 0;

			@Override
			public void actionPerformed(ActionEvent ae) {// allows scrolling of
															// labels in pop
															// window
				ArrayList<SuggestionLabel> sls = getAddedSuggestionLabels();
				int max = sls.size();

				if (max > 1) {// more than 1 suggestion
					for (int i = 0; i < max; i++) {
						SuggestionLabel sl = sls.get(i);
						if (sl.isFocused()) {
							if (lastFocusableIndex == max - 1) {
								lastFocusableIndex = 0;
								sl.setFocused(false);
								autoSuggestionPopUpWindow.setVisible(false);
								setFocusToTextField();
								checkForAndShowSuggestions();// fire method as
																// if document
																// listener
																// change
																// occured and
																// fired it

							} else {
								sl.setFocused(false);
								lastFocusableIndex = i;
							}
						} else if (lastFocusableIndex <= i) {
							if (i < max) {
								sl.setFocused(true);
								autoSuggestionPopUpWindow.toFront();
								autoSuggestionPopUpWindow.requestFocusInWindow();
								suggestionsPanel.requestFocusInWindow();
								suggestionsPanel.getComponent(i).requestFocusInWindow();
								lastFocusableIndex = i;
								break;
							}
						}
					}
				} else {// only a single suggestion was given
					autoSuggestionPopUpWindow.setVisible(false);
					setFocusToTextField();
					checkForAndShowSuggestions();// fire method as if document
													// listener change occured
													// and fired it
				}
			}
		};

	}

	protected void addWordToSuggestions(String word, Node nodeInformation) {
		SuggestionLabel suggestionLabel = new SuggestionLabel(word, suggestionFocusedColor, suggestionsTextColor, this,
				preferredSize, labelFont, nodeInformation);

		calculatePopUpWindowSize(suggestionLabel);

		suggestionsPanel.add(suggestionLabel);
	}

	public String getCurrentlyTypedWord() {// get newest word after last white
											// spaceif any or the first word if
											// no white spaces
		String text = textField.getText();
		String wordBeingTyped = "";

		wordBeingTyped = text;

		return wordBeingTyped.trim();
	}

	private void calculatePopUpWindowSize(JLabel label) {
		// so we can size the JWindow correctly
		if (tW < label.getPreferredSize().width) {
			tW = label.getPreferredSize().width;
		}
		tH += label.getPreferredSize().height;
	}

	private void showPopUpWindow(double windowLocationX2, double windowLocationY2) {
		autoSuggestionPopUpWindow.getContentPane().add(suggestionsPanel);
		autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 23));
		autoSuggestionPopUpWindow.setSize(tW, tH);
		autoSuggestionPopUpWindow.setVisible(true);

		int windowX = 0;
		int windowY = 0;
		// System.out.println(textField.getX());
		// System.out.println(container.getHeight());

		windowX = textField.getX() + inputPanel.getX();
		System.out.println(inputPanel.getX());
		if (suggestionsPanel.getHeight() > autoSuggestionPopUpWindow.getMinimumSize().height) {
			windowY = container.getY() + textField.getY() + textField.getHeight()
					+ autoSuggestionPopUpWindow.getMinimumSize().height +23 ;
		} else {
			windowY = container.getY() + textField.getY() + textField.getHeight()
					+ autoSuggestionPopUpWindow.getHeight() +23;
		}

		autoSuggestionPopUpWindow.setLocation(windowX, windowY);
		autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
		// autoSuggestionPopUpWindow.setBounds(windowX, windowY,
		// autoSuggestionLabel.getWidth(), autoSuggestionPopUpLabel.getWidth());
		autoSuggestionPopUpWindow.revalidate();
		autoSuggestionPopUpWindow.repaint();

	}

	// public void setDictionary(ArrayList<String> words) {
	// dictionary.clear();
	// if (words == null) {
	// return;// so we can call constructor with null value for dictionary
	// // without exception thrown
	// }
	// for (String word : words) {
	// dictionary.add(word);
	// }
	// }

	public JWindow getAutoSuggestionPopUpWindow() {
		return autoSuggestionPopUpWindow;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void addToDictionary(String word) {
		dictionary.add(word);
	}

	boolean checkWord(String typedWord) {

		if (typedWord.isEmpty()) {
			return false;
		}
		// System.out.println("Typed word: " + typedWord);
		SearchSupply dictionary = new SearchSupply();
		Map<String, Node> nodeMap = dictionary.getSearchSupply(typedWord);
		Set<Entry<String, Node>> stringSet = nodeMap.entrySet();
		Iterator<Entry<String, Node>> iter = stringSet.iterator();

		boolean suggestionAdded = false;
		while (iter.hasNext()) {
			Entry<String, Node> nodeInfo = iter.next();
			addWordToSuggestions(nodeInfo.getKey(), nodeInfo.getValue());
			suggestionAdded = true;

		}

		return suggestionAdded;
	}

	public void setInputPanel(InputPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	public MainModel getModel() {
		return model;
	}

	public SetNodeOption getSetNodeOption() {
		return setNodeOption;
	}

	private void setSetNodeOption(SetNodeOption setNodeOption) {
		this.setNodeOption = setNodeOption;
	}

}
