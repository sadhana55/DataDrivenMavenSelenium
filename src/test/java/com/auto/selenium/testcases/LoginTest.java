package com.auto.selenium.testcases;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.auto.selenium.base.TestBase;
import com.auto.selenium.utilities.TestUtil;

public class LoginTest extends TestBase{
	
	@Test
	public void loginTest() throws InterruptedException, IOException{
		
		if(!TestUtil.isTestRunnable("loginTest", excel)) {
			throw new SkipException("Skipping the test "+"loginTest".toUpperCase()+" as the RunMode is NO");
		}
		
//		verifyEquals("abc", "xyz");		
		log.info("This is Logger Info");
		log.debug("Inside Login Test");
		click("bmlBtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
		log.debug("Found the Add Customer button!!!");
		log.debug("Login Successful!!!");
		Reporter.log("Login Successful executed!!!");
		
//		Assert.fail("Login not Successful");
		
	}

}
