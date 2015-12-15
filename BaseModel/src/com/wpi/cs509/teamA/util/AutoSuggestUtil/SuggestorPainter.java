package com.wpi.cs509.teamA.util.AutoSuggestUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;

import com.wpi.cs509.teamA.util.ImageHelper;

import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;

public class SuggestorPainter {

	private static Dimension preferredSize = new Dimension(500, 30);


	private static Font historyFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font locationFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font majorFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font activityFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font othersFont = new Font("SimSun", Font.PLAIN, 23);
	private static Font labelFont = new Font("SimSun", Font.BOLD, 23);
	private static Font professorFont = new Font("SimSun", Font.PLAIN, 23);

	private static Color historyColor = Color.WHITE;
	private static Color locationColor = Color.WHITE;
	private static Color majorColor = Color.WHITE;
	private static Color activityColor = Color.WHITE;
	private static Color othersColor = Color.WHITE;
	private static Color professorColor = Color.WHITE;
	private static Color labelColor = Color.WHITE;

	private static Color historyTxtColor = Color.BLACK;
	private static Color locationTxtColor = Color.BLACK;
	private static Color professorTxtColor = Color.BLACK;
	private static Color labelTxtColor = Color.DARK_GRAY;
	private static Color othersTxtColor = Color.BLACK;
	private static Color majorTxtColor = Color.BLACK;
	private static Color activityTxtColor = Color.BLACK;

	private static Color historyTxtBorderColor = Color.BLACK;
	private static Color locationTxtBorderColor = Color.BLACK;
	private static Color labelTxtBorderColor = Color.BLACK;
	private static Color othersTxtBorderColor = Color.BLACK;
	private static Color majorTxtBorderColor = Color.BLACK;
	private static Color activityTxtBorderColor = Color.BLACK;
	private static Color professorTxtBorderColor = Color.BLACK;

	private static String professorIconName = "suggestionicon/Professor_Icon.png";
	private static String historyIconName = "suggestionicon/History_Icon.png";
	private static String locationIconName = "suggestionicon/Location_Icon.png";
	private static String othersIconName = "suggestionicon/Others_Icon.png";
	private static String activityIconName = "suggestionicon/Activity_Icon.jpg";
	private static String majorIconName = "suggestionicon/Major_Icon.png";

	private static String professorIconFilePath = PaintHelperBasics.getUserDir() + "com/wpi/cs509/teamA/resource/suggestionicon/" + professorIconName;
	private static String historyIconFilePath = PaintHelperBasics.getUserDir() + "com/wpi/cs509/teamA/resource/suggestionicon/" + historyIconName;
	private static String locationIconFilePath = PaintHelperBasics.getUserDir() + "com/wpi/cs509/teamA/resource/suggestionicon/" + locationIconName;
	private static String majorIconFilePath = PaintHelperBasics.getUserDir() + "com/wpi/cs509/teamA/resource/suggestionicon/" + majorIconName;
	private static String activityIconFilePath = PaintHelperBasics.getUserDir() + "com/wpi/cs509/teamA/resource/suggestionicon/" + activityIconName;
	private static String othersIconFilePath = PaintHelperBasics.getUserDir() + "com/wpi/cs509/teamA/resource/suggestionicon/" + othersIconName;

	private static BufferedImage professorIcon;
	private static BufferedImage locationIcon;
	private static BufferedImage historyIcon;
	private static BufferedImage othersIcon;
	private static BufferedImage majorIcon;
	private static BufferedImage activityIcon;

	static {
		try {
			professorIcon = ImageHelper.readImage(professorIconName);
			locationIcon = ImageHelper.readImage((historyIconName));
			historyIcon = ImageHelper.readImage((locationIconName));
			othersIcon = ImageHelper.readImage((othersIconName));
			majorIcon = ImageHelper.readImage((activityIconName));
			activityIcon = ImageHelper.readImage((majorIconName));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// labels: find nearst
	// others:
	public enum SuggestorEnum {
		Location(1), Professor(2), Major(2), History(3), Activity(1), Others(1), Labels(10);
		private int part_value;

		private SuggestorEnum(int value) {
			part_value = value;
		}

		public int getValue() {
			return part_value;
		}
	}

	protected static void setStyle(SuggestorEnum style, SuggestionBasicPanel suggestionPanel) {
		switch (style) {
		case Location:
			setsuggestionPanel(locationFont, locationTxtColor, locationColor, preferredSize, locationIcon,
					locationTxtBorderColor, suggestionPanel);
			break;
		case Professor:
			setsuggestionPanel(professorFont, professorTxtColor, professorColor, preferredSize, professorIcon,
					professorTxtBorderColor, suggestionPanel);
			break;
		case History:
			setsuggestionPanel(historyFont, historyTxtColor, historyColor, preferredSize, historyIcon,
					historyTxtBorderColor, suggestionPanel);
			break;
		case Major:
			setsuggestionPanel(majorFont, majorTxtColor, majorColor, preferredSize, majorIcon, majorTxtBorderColor,
					suggestionPanel);
			break;
		case Activity:
			setsuggestionPanel(activityFont, activityTxtColor, activityColor, preferredSize, activityIcon,
					activityTxtBorderColor, suggestionPanel);
			break;
		case Labels:
			setsuggestionPanel(labelFont, labelTxtColor, labelColor, preferredSize, null, labelTxtBorderColor,
					suggestionPanel);
			break;
		case Others:
			setsuggestionPanel(othersFont, othersTxtColor, othersColor, preferredSize, othersIcon, othersTxtBorderColor,
					suggestionPanel);
			break;

			
		}

	}

	protected static void setsuggestionPanel(Font font, Color txtColor, Color borderColor, Dimension size,
			BufferedImage icon, Color txtBorderColor, SuggestionBasicPanel suggestionPanel) {
		suggestionPanel.setFont(font);
		suggestionPanel.setSuggestionTextColor(txtColor);
		suggestionPanel.setSuggestionBorderColor(borderColor);
		suggestionPanel.setPreferredSize(size);
		suggestionPanel.setImageIcon(icon);
		suggestionPanel.setSuggestionLineBorderColor(txtBorderColor);
	}

//	protected static Icon getIconFromName(String name){
//		switch (name){
//			case "RestRoom": 			
//				return new ImageIcon(NodeIcon.getRestroomIcon());
//				
//			case "ParkingLot":
//				return new ImageIcon(NodeIcon.getParkingIcon());
//			case "pizza":
//				return new ImageIcon(othersIcon);
//		}
//		return null;
//		
//	}

}
