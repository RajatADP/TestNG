package com.TestFramework;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.PageObject.Registration;

public class LibraryClasses {
	static Xls_Reader reader;
	Registration reg;
	public void clickLink(WebDriver driver, WebElement ele) {
		reg = new Registration(driver);
		ele.click();		
	}
	
	public static ArrayList<Object[]> getDataFromExcel() {		
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader("C:\\Users\\Rajat-PC\\testing-framework\\TestFramework\\src\\test\\java\\com\\Resources\\datasheet.xlsx");
		}catch (Exception e) {
			e.printStackTrace();
		}		
		for(int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++) {
			String firstName = reader.getCellData("Sheet1", "FirstName", rowNum);
			String lastName = reader.getCellData("Sheet1", "LastName", rowNum);
			String postalCode = reader.getCellData("Sheet1", "Postal Code", rowNum);
			String country = reader.getCellData("Sheet1", "Country", rowNum);
			
			Object ob[] = {firstName,lastName,postalCode,country};
			myData.add(ob);
		}
		return myData;
	}
}
