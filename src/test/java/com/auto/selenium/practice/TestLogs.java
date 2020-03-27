package com.auto.selenium.practice;

import org.apache.log4j.PropertyConfigurator;
import org.testng.log4testng.Logger;

public class TestLogs {
	
	public static Logger log = Logger.getLogger(TestLogs.class.getName().getClass());
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		log.info("this is information log");
		
		log.error("this is error");
	}

}
