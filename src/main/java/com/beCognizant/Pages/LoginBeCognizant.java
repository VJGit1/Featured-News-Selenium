package com.beCognizant.Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import com.beCognizant.Base.Base;

public class LoginBeCognizant extends Base {
	
	By email = By.xpath("//input[@type='email']");
	By pass = By.name("passwd");
	By next = By.xpath("//input[@type='submit']");
	By staySigninBtn = By.id("idSIButton9");
	
	public void msLogin() throws IOException, InterruptedException {
		
		logger = report.createTest("Login Functionality");
		System.out.println("Microsoft SignIn");
		try {
			navigateToURL("URL");
			Thread.sleep(2000);			 
			driver.findElement(email).sendKeys(prop.getProperty("EmailId"));
			Thread.sleep(2000);
			TakeScreenshot();
			driver.findElement(next).click();
			System.out.println("Email Entered");
			reportPass("Email entered in the text field");
			
			driver.findElement(pass).sendKeys(prop.getProperty("Password"));
			Thread.sleep(2000);
			TakeScreenshot();
			driver.findElement(next).click();
			System.out.println("Password Entered");
			reportPass("Password entered in the text field");
			Thread.sleep(20000);
			
			driver.findElement(staySigninBtn).click();
			reportPass("LogIn Successfull");			
			System.out.println("LogIn Successfull\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
