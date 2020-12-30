package com.practice.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.pages.crm.accounts.AccountHomePage;

public class TopMenu {
	
	WebDriver driver;
	public TopMenu(WebDriver driver) {
		
		this.driver = driver;
	}

	public void goToHome() {

	}

	public void goToLeads() {

	}

	public void goToContacts() {

	}

	public AccountHomePage goToAccounts() {
		
		Page.click("accountMenu_XPATH");
		//driver.findElement(By.xpath("//a[contains(text(),'Accounts')]")).click();
		return new AccountHomePage();
	}

	public void goToDeals() {

	}

	public void goToActivities() {

	}

	public void goToReports() {

	}

	public void goToAnalytics() {

	}

	public void goToProducts() {

	}
	
	public void goToQuotes() {

	}

}
