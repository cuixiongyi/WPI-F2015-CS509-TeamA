package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.wpi.cs509.teamA.bean.Node;

public class SuggestionActivityPanel extends SuggestionBasicPanel {

	public SuggestionActivityPanel(String string, AutoSuggestor autoSuggestor, Node node, String displayName) {
		super(string, autoSuggestor, node);
		
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Activity,this);
		// TODO Auto-generated constructor stub
		this.initComponent();
	}
	
	
	

}
