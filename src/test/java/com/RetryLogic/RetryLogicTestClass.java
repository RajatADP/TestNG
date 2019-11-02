package com.RetryLogic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryLogicTestClass {

	
	 @Test(retryAnalyzer = com.RetryLogic.RetryAnalyzer.class)
	public void checkFailRetryLogic()
	{
		Assert.assertEquals(true, false);
	}
	
	 @Test(retryAnalyzer = com.RetryLogic.RetryAnalyzer.class)
	public void checkFailRetryLogic1()
	{
		Assert.assertEquals(true, false);
	}
	 @Test
	 public void checkFailRetryLogic2()
		{
			Assert.assertEquals(true, true);
		}
	 @Test
	 public void checkFailRetryLogic3()
		{
			Assert.assertEquals(true, false);
		}
}
