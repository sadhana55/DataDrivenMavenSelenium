package com.auto.selenium.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.auto.selenium.utilities.ExcelReader;
import com.auto.selenium.utilities.ExtentManager;
import com.auto.selenium.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class TestBase {
	//intialize the WebDriver, Properties, Logs, ExtentReports,DB,Excel,Mail even before running the test cases
	
	//should be called before running any suite
	
	//we are calling WebDriver here calling it as Driver. Just creating reference, on the runtime I will define whether it should be Chrome or IE
	//or firefox driver. We can define that in Config.properties.
	
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis; //we create object later
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\data\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
	
	@BeforeSuite
	public void setUp(){
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
		//if driver is null, that is there is no Property file loaded, we just load it.
		if(driver==null){
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //config property will not load configuration file. so whatever is there it is loaded
			System.out.println(config.getProperty("browser"));
			
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) {
				
				browser= System.getenv("browser");
				
			}else {
				browser=config.getProperty("browser");
			}
			
			config.setProperty("browser", browser);
			
			
			if(config.getProperty("browser").equals("firefox")){
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\gecko.exe");
				driver = new FirefoxDriver();
			}else if(config.getProperty("browser").equals("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions(); 
				options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
				options.setExperimentalOption("useAutomationExtension", false);				
				options.addArguments("chrome.switches","--disable-extensions");
				driver = new ChromeDriver(options);
				log.debug("Chrome Launched !!!");
			}else if(config.getProperty("browser").equals("ie")){
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("disable-infobars");
			driver.get(config.getProperty("siteurl"));
			log.debug("Navigated to "+ config.getProperty("siteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
		
		}
		
	}
	
	
	public void click(String locator) {
		if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
	}else if(locator.endsWith("_XPATH")){
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
	}else if(locator.endsWith("_ID")){
		driver.findElement(By.id(OR.getProperty(locator))).click();
	}
		test.log(LogStatus.INFO, "Clicking on :" +locator);
	}
	
	public void type(String locator, String value) {	
		
		if(locator.endsWith("_CSS")){
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in :" +locator+ " entered value as"+value);
	}
	
	
	static WebElement dropdown;
	
	public void select(String locator, String value) {
		
		if(locator.endsWith("_CSS")){
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}else if(locator.endsWith("_XPATH")){
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}else if(locator.endsWith("_ID")){
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting from dropdown :" +locator+ " and value as"+value);
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		}catch(Throwable t) {
			
			TestUtil.captureScreenshot(); 
			
			//ReportNG
			Reporter.log("<br"+"Verification failure: " + t.getMessage() + "<br>");				
			Reporter.log("<a target = \"_blank\" href = "+TestUtil.screenshotName+"> <img src = "+TestUtil.screenshotName+" height =25  wight=25></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			//Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			
			
			
		}
	}
	
	@AfterSuite
	public void tearDown(){
		
		if(driver !=null){
			driver.quit();
		}
		log.debug("Test Execution Completed!!!");
		
	}

}
