package com.AssertCheck;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftHardAssert {
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void checkSoftAssert() {
		softAssert.assertEquals(true, false);
		System.out.println("runnnig after fail also");
	}
	
	@Test
	public void checkSoftAssert1() {
		softAssert.assertEquals(true, false);
		System.out.println("runnnig after fail also");
		softAssert.assertAll();
	}
	
	@Test
	public void checkHardAssert() {		
		Assert.assertEquals(true, false);
		System.out.println("not runnnig after fail also");		
	}
}