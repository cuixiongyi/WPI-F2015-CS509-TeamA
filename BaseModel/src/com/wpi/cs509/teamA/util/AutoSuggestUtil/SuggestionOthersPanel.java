package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.wpi.cs509.teamA.persistence.bean.Node;

public class SuggestionOthersPanel extends SuggestionBasicPanel {

	public SuggestionOthersPanel(String string, AutoSuggestor autoSuggestor, Node node, String displayName) {
		super(string, autoSuggestor, node);
		
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Others,this);
		// TODO Auto-generated constructor stub
		this.initComponent();
	}
	
	
	

}
