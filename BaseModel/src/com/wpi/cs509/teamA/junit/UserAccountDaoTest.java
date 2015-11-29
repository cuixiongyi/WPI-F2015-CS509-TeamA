package com.wpi.cs509.teamA.junit;

import com.wpi.cs509.teamA.bean.Node;

//import static org.junit.Assert.*;

//import org.junit.Test;

import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.UserAccountDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.dao.impl.UserAccountDaoImpl;

public class UserAccountDaoTest {

	//@Test
	public static void main(String [] args) {
	/*	UserAccount ua = new UserAccount();
		ua.setUsername("yizhouyan");
		ua.setPassword("hammer");
		ua.setAdmin(true);
		ua.setEmail("yyan2@wpi.edu");
		UserAccountDao uad = new UserAccountDaoImpl();
		uad.addAccountToDatabase(ua);*/
		
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		Node n1 = new Node();
		n1.setId(100);
		Node n2 = new Node();
		n2.setId(20);
		System.out.println(nrd.deleteEdge(n1, n2));
		}

}
