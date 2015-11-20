package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.DupEntranceMap;
import com.wpi.cs509.teamA.dao.impl.InitAllMatrixDaoImpl;
import com.wpi.cs509.teamA.util.Database;

/**
 * This is class is to initialize all the resources that will be used in the
 * system. Since the method used are all singleton, so the system will only
 * access database in this class (once). So later we use the resource, they are
 * already there.
 * 
 * @author CS 509-Team A
 *
 */
public class SystemFacade implements Runnable {

	Thread t;

	/**
	 * The method to initialize all the resources
	 */
	public SystemFacade() {

		// TODO: We may use mutli-thread here..

		t = new Thread(this, "initialize data thread");
		t.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		// TODO Auto-generated method stub
		// load all data from the database
		Database.InitFromDatabase();
		
		// initialize the relationship map
		DupEntranceMap.initDupEntranceMap();
		// initialize all the matrix for the map
		InitAllMatrixDaoImpl.initAllMatrix();
		
	}

}
