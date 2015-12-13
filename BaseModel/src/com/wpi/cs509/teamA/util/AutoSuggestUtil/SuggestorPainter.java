package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.util.PaintHelper;

public class SuggestorPainter {
	private static Font historyFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font locationFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font professorFont = new Font("SimSun", Font.PLAIN, 23);

	private static Color historyColor = Color.WHITE;
	private static Color locationColor = Color.WHITE;
	private static Color professorColor = Color.WHITE;

	private static Color historyTxtColor = Color.BLACK;
	private static Color locationTxtColor = Color.BLACK;
	private static Color professorTxtColor = Color.BLACK;
	
	private static Color historyTxtBorderColor = Color.BLACK;
	private static Color locationTxtBorderColor = Color.BLACK;
	private static Color professorTxtBorderColor = Color.BLACK;

	private static Dimension preferredSize = new Dimension(115, 23);

	private static String professorIconName = "Professor_Icon.png";
	private static String historyIconName = "History_Icon.png";
	private static String locationIconName = "Location_Icon.png";

	private static String professorIconFilePath = PaintHelper.getUserDir() + professorIconName;
	private static String historyIconFilePath = PaintHelper.getUserDir() + historyIconName;
	private static String locationIconFilePath = PaintHelper.getUserDir() + locationIconName;

	private static BufferedImage professorIcon;
	private static BufferedImage locationIcon;
	private static BufferedImage historyIcon;

	static {
		try {
			professorIcon = ImageIO.read(new FileInputStream(professorIconFilePath));
			locationIcon = ImageIO.read(new FileInputStream(locationIconFilePath));
			historyIcon = ImageIO.read(new FileInputStream(historyIconFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public enum SuggestorEnum {
		Location(1), Professor(2), Major(2), History(3),Activity(1), Others(1);
		private int part_value;
		private SuggestorEnum(int value){
			part_value = value;
		}
		public int getValue(){
			return part_value;
		}
	}

	protected static void setStyle(SuggestorEnum style, SuggestionBasicPanel suggestionPanel) {
		switch (style) {
		case Location:
			suggestionPanel.setFont(locationFont);
			suggestionPanel.setSuggestionTextColor(locationTxtColor);
			suggestionPanel.setSuggestionBorderColor(locationColor);
			suggestionPanel.setPreferredSize(preferredSize);
			suggestionPanel.setImageIcon(locationIcon);
			suggestionPanel.setSuggestionLineBorderColor(locationTxtBorderColor);
			break;
		case Professor:
			suggestionPanel.setFont(professorFont);
			suggestionPanel.setSuggestionTextColor(professorTxtColor);
			suggestionPanel.setSuggestionBorderColor(professorColor);
			suggestionPanel.setPreferredSize(preferredSize);
			suggestionPanel.setImageIcon(professorIcon);
			suggestionPanel.setSuggestionLineBorderColor(professorTxtBorderColor);
			break;
		case History:
			suggestionPanel.setFont(historyFont);
			suggestionPanel.setSuggestionTextColor(historyTxtColor);
			suggestionPanel.setSuggestionBorderColor(historyColor);
			suggestionPanel.setPreferredSize(preferredSize);
			suggestionPanel.setImageIcon(historyIcon);
			suggestionPanel.setSuggestionLineBorderColor(historyTxtBorderColor);
			break;

		}
	}

}
