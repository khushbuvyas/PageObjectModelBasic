package com.practice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.practice.utilities.ExcelReader;
import com.practice.utilities.ExtentReportManager;
import com.practice.utilities.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page{
	
	/*
	 * LoginPage ISA Page
	 * HomePage ISA Page
	 * ZohoAppPage ISA Page
	 * 
	 * LoginPage HasA TopMenu
	 * HomePage HasA TopMenu
	 * ZohoAppPage HasA TopMenu
	 * 
	 */
	
	public static WebDriver driver;
	public static TopMenu menu;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();;
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\Excel\\Testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentReportManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
	public Page() {
		
		if(driver==null) {
			
			if (driver == null) {
				try {
					fis = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\Properties\\Config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					config.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				log.debug("Config file loaded !!!");

				try {
					fis = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\com\\practice\\Properties\\OR.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					OR.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.debug("OR file loaded !!!");
			}
			
			//Jenkins Browser filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			//Execute testcases based on Browser selected
			if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				
				//Setup ChromeBrowser using ChromeOptions class
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver = new ChromeDriver(options);
				log.debug("Chrome Launched!!!");

			} else if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("Firefox Launched!!!");

			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.debug("IE Launched!!!");
			}
			
			/*WebDriverManager.chromedriver().setup();
			
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");*/

			driver.get(config.getProperty("testurl"));
			log.debug("Navigated to : " + config.getProperty("testurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 10);
			menu = new TopMenu(driver);
			
		}
		
		
	}
	
	// Common functionality to execute testcases
	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "Click on the - " + locator);
	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Enter value in - " + locator + " as " + value);
	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			test.log(LogStatus.INFO, "inside CSS if condition");
		} else if (locator.endsWith("_XPATH")) {

			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {

			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		// test.log(LogStatus.INFO, "outside CSS if condition");
		Select select = new Select(dropdown);

		select.selectByVisibleText(value);

		// test.log(LogStatus.INFO, "After value select");

		test.log(LogStatus.INFO, "Selected value in - " + locator + " as " + value);
	}

	public static void verifyEquals(String actual, String expected) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtils.CaptureScreenshot();

			test.log(LogStatus.FAIL, "Verification is failed-" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotName));

			Reporter.log("<br>" + "Verification is failed-" + t.getMessage() + "<br>");
			Reporter.log("<a href=" + TestUtils.screenshotName + " target=\"_blank\"><img src="
					+ TestUtils.screenshotName + " height=200 width=200></img></a>");
			Reporter.log("<br>");
		}
	}
	
	public static void quit() {
		driver.quit();
	}

}
