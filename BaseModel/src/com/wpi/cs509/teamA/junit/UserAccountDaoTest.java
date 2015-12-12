package com.wpi.cs509.teamA.junit;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.UserAccount;

//import static org.junit.Assert.*;

//import org.junit.Test;

import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.UserAccountDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.dao.impl.UserAccountDaoImpl;
import com.wpi.cs509.teamA.exception.PwdIncorrectException;
import com.wpi.cs509.teamA.exception.UserAccountNotFoundException;

public class UserAccountDaoTest {

	//@Test
	public static void main(String [] args) {
		
		//add account to database
		UserAccount ua = new UserAccount();
		ua.setUsername("yizhouyan3");
		ua.setPassword("hammer");
		ua.setAdmin(true);
		ua.setEmail("yyan2@wpi.edu");
		UserAccountDao uad = new UserAccountDaoImpl();
		//if the username exists, return false, means cannot insert into the database
		//if the username does not exist, return true, means can insert into the database
		System.out.println(uad.addAccountToDatabase(ua));
		
		//login authorize
		try{
			UserAccountDao uad2 = new UserAccountDaoImpl();
			UserAccount new_ua = uad2.loginAuthorization("cs509", "hammer9132");
			System.out.println("the user's email:" + new_ua.getEmail());
		}catch(PwdIncorrectException pie){
			// pop up a window said incorrect password
		}catch(UserAccountNotFoundException uanfe){
			// pop up a window said cannot find the user account
		}
	/*	NodeRelationDao nrd = new NodeRelationDaoImpl();
		Node n1 = new Node();
		n1.setId(100);
		Node n2 = new Node();
		n2.setId(20);
		System.out.println(nrd.deleteOrAddEdge(n1, n2));
		}*/ 
	}

}
