package com.wpi.cs509.teamA.model;

/**
 * 
 * This model contains all the models of the application
 * 
 * data model and state model
 * 
 * This is a singleton
 * 
 * @author JLou
 *
 */
public class MainModel {

	private static MainModel mainModel;
	private static DataModel dataModel;
	private static StateContextModel stateContextModel;

	private MainModel() {
		dataModel = DataModel.getDataModel();
		stateContextModel = StateContextModel.getStateContextModel();
	}

	public static synchronized MainModel getMainModel() {
		if (mainModel == null) {
			mainModel = new MainModel();
		}

		return mainModel;
	}

	public static DataModel getDataModel() {
		return dataModel;
	}

	public static void setDataModel(DataModel pModel) {
		// change that instance directly
		DataModel.setDatacModel(pModel);
	}

	public static StateContextModel getStateContextModel() {
		return stateContextModel;
	}

	public static void setStateContextModel(StateContextModel pModel) {
		// change that instance directly
		StateContextModel.setStateContextModel(pModel);
	}

}
