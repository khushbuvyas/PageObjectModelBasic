package com.practice.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.practice.base.Page;

public class TestUtils extends Page {

	public static String screenshotName;

	public static void CaptureScreenshot(String method) throws IOException {

		Date d = new Date();
		screenshotName = method + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}

	public static void CaptureScreenshot() throws IOException {

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();

		int rows = excel.getRowCount(sheetName);
		int colms = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table;

		for (int rowcnt = 2; rowcnt <= rows; rowcnt++) {

			table = new Hashtable<String, String>();

			for (int colcnt = 0; colcnt < colms; colcnt++) {

				table.put(excel.getCellData(sheetName, colcnt, 1), excel.getCellData(sheetName, colcnt, rowcnt));

				data[rowcnt - 2][0] = table;
			}

		}

		return data;
	}

	public static boolean isTestRunnable(String test, ExcelReader excel) {

		String sheetName = "testsuite";
		int rows = excel.getRowCount(sheetName);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			String testcaseName = excel.getCellData(sheetName, "testcase", rowNum);

			if (testcaseName.equalsIgnoreCase(test)) {

				String runMode = excel.getCellData(sheetName, "runmodes", rowNum);

				if (runMode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;

	}

}
