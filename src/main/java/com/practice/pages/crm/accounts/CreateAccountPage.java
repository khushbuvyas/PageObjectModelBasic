package com.practice.pages.crm.accounts;

import org.openqa.selenium.By;

import com.practice.base.Page;

public class CreateAccountPage extends Page {

	public void createAccount(String accName) {
		
		driver.findElement(By.cssSelector("#Crm_Accounts_ACCOUNTNAME")).sendKeys(accName);
		
	}
}
