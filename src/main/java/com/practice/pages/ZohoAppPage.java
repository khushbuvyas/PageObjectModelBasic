package com.practice.pages;

import org.openqa.selenium.By;

import com.practice.base.Page;
import com.practice.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page{

	public CRMHomePage gotoCRM() {
		
		click("crmIcon_XPATH");
		//driver.findElement(By.xpath("//div[contains(text(),'CRM')]")).click();
		return new CRMHomePage();

	}

	public void gotoMail() {

		click("mailIcon_XPATH");
		//driver.findElement(By.xpath("//div[contains(text(),'Mail')]")).click();
	}

	public void gotoConnect() {
		
		click("connectIcon_XPATH");
		//driver.findElement(By.xpath("//div[contains(text(),'Connect')]")).click();

	}

	public void gotoDesk() {
		
		click("deskIcon_XPATH");
		//driver.findElement(By.xpath("//div[contains(text(),'Desk')]")).click();
	}
}
