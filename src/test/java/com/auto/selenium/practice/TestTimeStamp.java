package com.auto.selenium.practice;

import java.util.Date;

public class TestTimeStamp {
	
	public static void main(String[] args) {
		Date d = new Date();
		String screenshotName = d.toString().replace(":", "_").replace(" ", "_");
		System.out.println(d);
		System.out.println(screenshotName);
	}

}
