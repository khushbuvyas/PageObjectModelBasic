package com.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.practice.base.Page;

public class HomePage extends Page{
	
	
	public void goToSignUp() {

		click("signUp_XPATH");
		//driver.findElement(By.xpath("//a[contains(text(),'Free Sign Up')]")).click();
	}

	public LoginPage goToLogin() {

		click("login_XPATH");
		//driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		return new LoginPage();
	}

	public void goToSupport() {

		click("support_XPATH");
		//driver.findElement(By.xpath("//body/div[2]/div[2]/div[1]/a[2]")).click();
	}

	public void goToCustomer() {
		
		click("customer_XPATH");
		//driver.findElement(By.xpath("//body/div[2]/div[2]/div[1]/a[1]")).click();

	}

	public void goToContactSales() {
		
		click("contactSales_XPATH");
		//driver.findElement(By.xpath("//a[contains(text(),'Contact Sales')]")).click();

	}

	public void verifyFooterLinks() {

	}

}
