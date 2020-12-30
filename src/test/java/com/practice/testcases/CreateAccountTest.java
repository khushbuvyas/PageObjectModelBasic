package com.practice.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.practice.base.Page;
import com.practice.pages.ZohoAppPage;
import com.practice.pages.crm.CRMHomePage;
import com.practice.pages.crm.accounts.AccountHomePage;
import com.practice.pages.crm.accounts.CreateAccountPage;
import com.practice.utilities.TestUtils;

public class CreateAccountTest {

	@Test(dataProviderClass=TestUtils.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String,String> data) {
		
		ZohoAppPage zpage = new ZohoAppPage();
		CRMHomePage cp = zpage.gotoCRM();

		AccountHomePage ap = Page.menu.goToAccounts();

		CreateAccountPage cap = ap.goToCreateAccoutPage();

		cap.createAccount(data.get("accountname"));
		
	}
}
