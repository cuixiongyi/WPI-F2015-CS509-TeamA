package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import com.wpi.cs509.teamA.bean.Node;

public class SuggestionLocationPanel extends SuggestionBasicPanel {

	public SuggestionLocationPanel(String string, AutoSuggestor autoSuggestor, Node node) {
		super(string, autoSuggestor, node);
		// TODO Auto-generated constructor stub
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Location,this);
		// TODO Auto-generated constructor stub
		this.initComponent();
	}

}
