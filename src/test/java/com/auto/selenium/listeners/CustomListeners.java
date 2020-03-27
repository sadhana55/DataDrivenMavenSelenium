package com.auto.selenium.listeners;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.xpath.Arg;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.auto.selenium.base.TestBase;
import com.auto.selenium.utilities.MonitoringMail;
import com.auto.selenium.utilities.TestConfig;
import com.auto.selenium.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener,ISuiteListener{
	
	public String messageBody;
	
	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " FAILED with exception : "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		
		Reporter.log("Click to see Screenshot");
		//Reporter.log("<a target = \"_blank\" href = \"C:\\Users\\Sadhana\\workspace\\com.auto.selenium\\src\\test\\resources\\screenshots\\peacock girl.jpg\"> Screenshot</a>");
		Reporter.log("<a target = \"_blank\" href = "+TestUtil.screenshotName+" > Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target = \"_blank\" href = "+TestUtil.screenshotName+"> <img src = "+TestUtil.screenshotName+" height =25  wight=25></img></a>");
		
		rep.endTest(test);
		rep.flush();
	}
	
	public void onTestSkipped(ITestResult arg0) {
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " Skipped the test as Run Mode is NO");
		rep.endTest(test);
		rep.flush();
	}
	
	public void onTestStart(ITestResult arg0) {
		test=rep.startTest(arg0.getName().toUpperCase());
		//check the run modes, if the test runmode is Y , then run it else do not run
		
		
	}
	
//	public void onFinish(ISuite arg0) {
//		MonitoringMail mail = new MonitoringMail();
//		
//		try {
//			messageBody = "http://"+ Inet4Address.getLocalHost().getHostAddress()+ ":8080/job/first/Extent_20Reports/";
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.print(messageBody);
//		
//		try {
//			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void onTestSuccess(ITestResult arg0) {
		
		test.log(LogStatus.PASS, arg0.getName().toUpperCase() + " PASS");
		rep.endTest(test);
		rep.flush();
	}
}
