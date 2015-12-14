package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import com.wpi.cs509.teamA.bean.Node;

public class SuggestionProfessorPanel extends SuggestionBasicPanel {

	public SuggestionProfessorPanel(String string, AutoSuggestor autoSuggestor, Node node, String displayName) {
		super(string, autoSuggestor, node);
		// TODO Auto-generated constructor stub
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Professor,this);
		// TODO Auto-generated constructor stub
		this.initComponent();
	}

}
