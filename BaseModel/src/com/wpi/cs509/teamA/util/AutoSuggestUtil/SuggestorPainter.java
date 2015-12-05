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

	private static Color historyTxtColor = Color.WHITE;
	private static Color locationTxtColor = Color.WHITE;
	private static Color professorTxtColor = Color.WHITE;

	private static Dimension preferredSize = new Dimension(100, 23);

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
		Location, Professor, History,
	}

	protected static void setStyle(SuggestorEnum style, SuggestionBasicPanel suggestionPanel) {
		switch (style) {
		case Location:
			suggestionPanel.setFont(locationFont);
			suggestionPanel.setSuggestionTextColor(locationTxtColor);
			suggestionPanel.setSuggestionBorderColor(locationColor);
			suggestionPanel.setPreferredSize(preferredSize);
			suggestionPanel.setImageIcon(locationIcon);
			break;
		case Professor:
			suggestionPanel.setFont(professorFont);
			suggestionPanel.setSuggestionTextColor(professorTxtColor);
			suggestionPanel.setSuggestionBorderColor(professorColor);
			suggestionPanel.setPreferredSize(preferredSize);
			suggestionPanel.setImageIcon(professorIcon);
			break;
		case History:
			suggestionPanel.setFont(historyFont);
			suggestionPanel.setSuggestionTextColor(historyTxtColor);
			suggestionPanel.setSuggestionBorderColor(historyColor);
			suggestionPanel.setPreferredSize(preferredSize);
			suggestionPanel.setImageIcon(historyIcon);
			break;

		}
	}

}
