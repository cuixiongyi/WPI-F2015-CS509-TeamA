package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.wpi.cs509.teamA.bean.Node;

public class SuggestionLabelPanel extends SuggestionBasicPanel {

	public SuggestionLabelPanel(String string, AutoSuggestor autoSuggestor, Node node) {
		super(string, autoSuggestor, node);
		
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Labels,this);
		// TODO Auto-generated constructor stub
		this.initComponent();
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
