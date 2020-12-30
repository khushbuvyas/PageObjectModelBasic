package com.practice.rough;

import com.practice.base.Page;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.pages.ZohoAppPage;
import com.practice.pages.crm.CRMHomePage;
import com.practice.pages.crm.accounts.AccountHomePage;
import com.practice.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {

		
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zpage= lp.doLogin("seleniumpractice3@gmail.com", "Selenium@1234");
		
		CRMHomePage cp = zpage.gotoCRM();
		
		AccountHomePage ap = Page.menu.goToAccounts();
		
		CreateAccountPage cap =ap.goToCreateAccoutPage();
		
		cap.createAccount("abc");
		
		//driver.close();

	}

}
