package com.auto.selenium.practice;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.auto.selenium.utilities.MonitoringMail;
import com.auto.selenium.utilities.TestConfig;

public class TestHost {
	
	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		
		MonitoringMail mail = new MonitoringMail();
		String messageBody = "http://"+ Inet4Address.getLocalHost().getHostAddress()+ ":8080/job/first/Extent_20Reports/";
		
		System.out.print(messageBody);
		
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	}

}
