package com.practice.pages.crm.accounts;

import org.openqa.selenium.By;

import com.practice.base.Page;

public class AccountHomePage extends Page{

	public CreateAccountPage goToCreateAccoutPage() {

		click("createAccnt_XPATH");
		//driver.findElement(By.xpath("//lyte-button[@id='submenu_Accounts']//button[@id='']")).click();
		return new CreateAccountPage();
	}

	public void goToImportAccoutPage() {

	}
}
