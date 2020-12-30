package com.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.practice.base.Page;

public class LoginPage extends Page{
	
	public ZohoAppPage doLogin(String userName, String password) {
		
		type("userId_CSS", userName);
		click("nextButton_XPATH");
		/*driver.findElement(By.cssSelector("#login_id")).sendKeys(userName);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();*/
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type("password_CSS",password);
		click("submitButton_XPATH");
		/*driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//body/div[5]/div[3]/div[2]/form[1]/button[1]/span[1]")).click();*/
		
		
		System.out.println("User Loginin successfully !!!");
		return new ZohoAppPage();
	}
	
	public void goToSignUp() {
		
		driver.findElement(By.xpath("//a[contains(text(),'Sign Up Now')]")).click();
	}
	
}
