package com.auto.selenium.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	public static void main(String[] args) throws IOException {
		
		
		//Add a class called Properties. Inbuilt in java library.
		//Config is just a name. Whichever file you want to read. Take the path of the file and give it here
		Properties config = new Properties();
		Properties OR = new Properties();
		//fis is inbuilt java properties that accepts a path
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis); //config property will not load configuration file. so whatever is there it is loaded
		System.out.println(config.getProperty("browser"));
		
		
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		System.out.println(OR.getProperty("bmlBtn"));
		
		
		
	}

}
