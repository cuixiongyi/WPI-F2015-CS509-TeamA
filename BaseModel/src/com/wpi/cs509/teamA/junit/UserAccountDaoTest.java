package com.wpi.cs509.teamA.junit;

//import static org.junit.Assert.*;

//import org.junit.Test;

import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.UserAccountDao;
import com.wpi.cs509.teamA.dao.impl.UserAccountDaoImpl;

public class UserAccountDaoTest {

	//@Test
	public void test() {
		UserAccount ua = new UserAccount();
		ua.setUsername("yizhouyan");
		ua.setPassword("hammer");
		ua.setAdmin(true);
		ua.setEmail("yyan2@wpi.edu");
		UserAccountDao uad = new UserAccountDaoImpl();
		uad.addAccountToDatabase(ua);
		}

}
