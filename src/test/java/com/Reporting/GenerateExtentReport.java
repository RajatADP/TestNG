package com.Reporting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TestFramework.LibraryClasses;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateExtentReport {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;

	@BeforeTest
	public void startReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/MyReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// extent.setSystemInfo("OS", "Windows");
		htmlReporter.config().setDocumentTitle("Automation Status");
		htmlReporter.config().setReportName("My Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Test
	public void demoTestPass() {
		test = extent.createTest("Demo Pass Test", "This is to test the demo true report");
		Assert.assertTrue(true);
	}

	@Test
	public void demoTestFail() {
		test = extent.createTest("Demo Fail Test", "This is to test the demo fail report");
		Assert.assertTrue(false);
	}

	@Test
	public void demoTestSkip() {
		test = extent.createTest("Demo Skip Test", "This is to test the demo skip report");
		throw new SkipException("This test case is skipped");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test Case failed due to below issue",
					ExtentColor.RED));
			test.fail(result.getThrowable());
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			String screenShotPath = LibraryClasses.capture(driver, "Google Page");
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test Case passed", ExtentColor.GREEN));
			test.pass("Test Case PASSED");
			test.pass("Screenshot below: " +test.addScreenCaptureFromPath(screenShotPath));
		}

		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test Case skipped", ExtentColor.YELLOW));
			test.skip("Test Case SKIPPED");
		}
	}

	@Test
	public void logGeneration() {
		test = extent.createTest("Demo logging Test", "This is to test the log Generation report");
		test.log(Status.INFO, "Just logging something");
		test.log(Status.INFO, "Just logging something else");

		// using labels
		test.info(MarkupHelper.createLabel("******Using Color*******", ExtentColor.BLUE));
	}	
	
	@Test
	public void captureScreenshot() {
		test = extent.createTest("Capture ScreenShot", "This is to test the ScreenShot report");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Rajat-PC\\Downloads\\Softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");		
		Assert.assertTrue(true);
	}

	@AfterTest
	public void flushReport() {
		extent.flush();
	}
}
