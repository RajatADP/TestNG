package com.TestFramework;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.PageObject.Registration;
import com.PageObject.RegistrationPOM;

public class HomePageSelenium extends BaseClass {
	
	LibraryClasses lib = new LibraryClasses();
	Registration reg;	
	
	@Test(dataProvider = "getTestData")
	public void fillRegistration(String firstName, String lastName, String postalCode, String country) {	
		reg = new Registration(driver);
		// lib.clickLink(driver, reg.registerLink);
		lib.clickLink(driver, driver.findElement(RegistrationPOM.registerLink));
		
		
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		driver.findElement(By.name("postalCode")).sendKeys(postalCode);
		Select dropDown = new Select(driver.findElement(By.name("country")));
		dropDown.selectByVisibleText(country);
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = lib.getDataFromExcel();
		return testData.iterator();
	}
}
