package com.Screenshot;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.TestFramework.BaseClass;


public class ScreenshotListner extends BaseClass implements ITestListener{
	
	public void onTestFailure(ITestResult result) {
		failedScreenshot(result.getMethod().getMethodName());
	  }
}
