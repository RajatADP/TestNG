package com.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Registration {
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "REGISTER")
	@CacheLookup
	public WebElement registerLink;

	public Registration(WebDriver driver) {
		// This initElements method will create all WebElement
		PageFactory.initElements(driver, this);
	}

}
