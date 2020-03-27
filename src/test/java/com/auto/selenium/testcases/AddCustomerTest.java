package com.auto.selenium.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.auto.selenium.base.TestBase;
import com.auto.selenium.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")	
	public void addCustomerTest(Hashtable <String, String> data) throws InterruptedException {
		
		if(!TestUtil.isTestRunnable("addCustomerTest", excel)) {
			throw new SkipException("Skipping the test "+"addCustomerTest".toUpperCase()+" as the RunMode is NO");
		}
		
		if(!data.get("runMode").equals("Y") ) {
			throw new SkipException("Skipping the test as the RunMode is set as NO");
		}
		click("addCustBtn_CSS");
		type("firstName_CSS", data.get("firstName"));
		type("lastName_CSS",data.get("lastName"));
		type("postCode_CSS",data.get("postCode"));
		click("addBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent()); 
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		alert.accept();
		
//		Assert.fail("Customer not added successfully");
	
		
	}
	
}
