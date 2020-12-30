package com.practice.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.practice.base.Page;

import com.practice.utilities.MonitoringMail;
import com.practice.utilities.TestConfig;
import com.practice.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	public String messagebody;
	public void onTestStart(ITestResult result) {

		// Extent report
		test = report.startTest(result.getName().toUpperCase());

		System.out.println(result.getName() + " is " + TestUtils.isTestRunnable(result.getName(), excel));

		if (!TestUtils.isTestRunnable(result.getName(), excel)) {

			throw new SkipException("Test case: " + result.getName() + " is Skipped as runmode is NO");

		}

	}

	public void onTestSuccess(ITestResult result) {

		// Extent report
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		report.endTest(test);
		report.flush();

	}

	public void onTestFailure(ITestResult result) {

		String methodName = result.getName();
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		try {
			TestUtils.CaptureScreenshot(methodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Extent report
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " FAILED");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotName));

		// ReportNG
		Reporter.log("Click to see screenshot !!!");
		Reporter.log("<a href=" + TestUtils.screenshotName + " target=\"_blank\">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=" + TestUtils.screenshotName + " target=\"_blank\"><img src=" + TestUtils.screenshotName
				+ " height=200 width=200></img></a>");

		// Extent report
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {

		test.log(LogStatus.SKIP, result.getName() + " testcase is skipped as runmode is NO!!!");
		report.endTest(test);
		report.flush();

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {
		MonitoringMail mail = new MonitoringMail();


		try {
			messagebody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PageDrivenModel/Extent_20Report/";
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messagebody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
