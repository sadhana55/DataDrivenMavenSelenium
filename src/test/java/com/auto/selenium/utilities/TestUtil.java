package com.auto.selenium.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.auto.selenium.base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException {
		
		Date d = new Date();
				
		
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		screenshotName = d.toString().replace(":", "_").replace(" ", "_");
		FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" +screenshotName));
		
	}
	
		@DataProvider(name = "dp")
		public Object[][] getData(Method m){
			
			String sheetName = m.getName();
			int rows = excel.getRowCount(sheetName);
			int cols = excel.getColumnCount(sheetName);
//			System.out.println("Rows" + rows);
//			System.out.println("Columns" + cols);
			
			
			Object [] [] data = new Object[rows-1][1];
			
			Hashtable<String, String> table = null;
			
			for(int rowNum =2; rowNum <= rows; rowNum++) {
				
				table = new Hashtable<String,String>();
				
				for(int colNum = 0; colNum < cols; colNum++) {
					
					table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
					data[rowNum-2][0] = table;
					
					}
				
			}
			
			
			return data;
			
			
		}
		
		public static boolean isTestRunnable(String testName, ExcelReader excel) {
			
			String sheetName = "testSuite";
			int rows = excel.getRowCount(sheetName);
			
			for(int rowNum=2;rowNum<=rows;rowNum++) {
				String testCase = excel.getCellData(sheetName, "TCID", rowNum);
				if (testCase.equalsIgnoreCase(testName)){
					String runMode = excel.getCellData(sheetName, "RunMode", rowNum);
					
					if(runMode.equalsIgnoreCase("Y"))
						return true;
					else
						return false;
				}
			}
						
			return false;
			
		}
	

}
