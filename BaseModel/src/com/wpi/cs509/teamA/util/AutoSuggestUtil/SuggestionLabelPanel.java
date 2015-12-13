package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.wpi.cs509.teamA.bean.Node;

public class SuggestionLabelPanel extends SuggestionBasicPanel {

	public SuggestionLabelPanel(String string, AutoSuggestor autoSuggestor, Node node) {
		super(string, autoSuggestor, node);
		
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Labels,this);
		// TODO Auto-generated constructor stub
		this.initComponent();
	}
	
	
	

}
