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

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")		
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {
		
		if(!TestUtil.isTestRunnable("openAccountTest", excel)) {
			throw new SkipException("Skipping the test "+"openAccountTest".toUpperCase()+" as the RunMode is NO");
		}
		if(!data.get("runMode").equals("Y") ) {
			throw new SkipException("Skipping the test as the RunMode is set as NO");
		}
		click("openAccountBtn_CSS");
		select("customer_CSS", data.get("customerName"));
		select("currency_CSS", data.get("currency"));		
		click("processBtn_CSS");
		Thread.sleep(1000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
			
	}
	

}
