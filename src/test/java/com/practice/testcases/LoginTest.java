package com.practice.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.practice.base.Page;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.pages.ZohoAppPage;
import com.practice.pages.crm.CRMHomePage;
import com.practice.pages.crm.accounts.AccountHomePage;
import com.practice.pages.crm.accounts.CreateAccountPage;
import com.practice.utilities.TestUtils;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass=TestUtils.class,dataProvider="dp")
	public void loginTest(Hashtable<String,String> data) {
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		lp.doLogin(data.get("username"), data.get("password"));
		
	}
}
