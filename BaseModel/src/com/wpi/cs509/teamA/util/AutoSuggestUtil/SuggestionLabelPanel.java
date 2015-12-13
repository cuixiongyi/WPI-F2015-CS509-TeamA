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
import com.wpi.cs509.teamA.dao.impl.OtherFeatureDaoImpl;

public class SuggestionLabelPanel extends SuggestionBasicPanel {

	public SuggestionLabelPanel(String string, AutoSuggestor autoSuggestor, Node node) {
		super(string, autoSuggestor, node);
		
		SuggestorPainter.setStyle(SuggestorPainter.SuggestorEnum.Labels,this);
		OtherFeatureDaoImpl nodeList = new OtherFeatureDaoImpl();
	
		this.getNodeInformation().addAll(nodeList.getListofNodesWithLabel(this.getName()));
		// TODO Auto-generated constructor stub
		this.initComponent();
	}

	
	

}
