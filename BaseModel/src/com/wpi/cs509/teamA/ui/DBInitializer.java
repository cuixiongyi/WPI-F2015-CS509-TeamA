package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.util.Database;

/**
 * We would like to initialize database in a different thread
 * 
 * @author CS 509-Team A
 *
 */
public class DBInitializer implements Runnable {

	Thread t;

	/**
	 * The method to initialize all the resources
	 */
	public DBInitializer() {

		// TODO: We may use mutli-thread here..

		t = new Thread(this, "initialize data thread");
		t.start();
		try {
			/// TODO CXY : this is a hack, to get database ready before
			/// UserScreen start
			t.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		Database.InitFromDatabase();

	}

}
