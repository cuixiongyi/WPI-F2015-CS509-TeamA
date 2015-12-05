package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;



public class SuggestorPainter {
	private static Font historyFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font locationFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font professorFont = new Font("SimSun", Font.PLAIN, 23);
	
	private static Color historyColor = Color.WHITE;
	private static Color locationColor = Color.WHITE;
	private static Color professorColor = Color.WHITE;
	
	private static Color historyTxtColor = Color.WHITE;
	private static Color locationTxtColor = Color.WHITE;
	private static Color professorTxtColor = Color.WHITE;
	
	private static Dimension preferredSize = new Dimension(100, 23);
	
	private static 
	

	public enum DrawStyleEnum {
		Location, Professor, History,
	}

	protected static void setStyle(DrawStyleEnum style, SuggestionBasicPanel suggestionPanel) {
		switch (style) {
		case Location:
			suggestionPanel.setFont(locationFont);
			suggestionPanel.setSuggestionTextColor(locationTxtColor);
			suggestionPanel.setSuggestionBorderColor(locationColor);
			suggestionPanel.setPreferredSize(preferredSize);
			
			break;
		case Professor:
			suggestionPanel.setFont(professorFont);
			suggestionPanel.setSuggestionTextColor(professorTxtColor);
			suggestionPanel.setSuggestionBorderColor(professorColor);
			suggestionPanel.setPreferredSize(preferredSize);
			break;
		case History:
			suggestionPanel.setFont(historyFont);
			suggestionPanel.setSuggestionTextColor(historyTxtColor);
			suggestionPanel.setSuggestionBorderColor(historyColor);
			suggestionPanel.setPreferredSize(preferredSize);
			break;

		}
	}

}
