package com.TestFramework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	protected static WebDriver driver;
	Properties prop;
	String url;

	@BeforeClass
	public void loadData() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\Rajat-PC\\testing-framework\\TestFramework\\src\\test\\java\\com\\Resources\\config.properties");
			prop.load(ip);
			if (getObject("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\Rajat-PC\\Downloads\\Softwares\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			url = getObject("url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getObject(String key) {
		String keyValue = prop.getProperty(key);
		return keyValue;
	}

	public void failedScreenshot(String methodName) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(
					"C:\\Users\\Rajat-PC\\testing-framework\\TestFramework\\test-output\\Screenshots\\"+ methodName +"_failed.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void waits() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);
	}

	@AfterClass
	public void logout() {
		driver.quit();
	}

}
