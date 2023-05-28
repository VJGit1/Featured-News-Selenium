package com.beCognizant.TestSuites;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.beCognizant.Base.Base;
import com.beCognizant.Pages.BeCognizant;
import com.beCognizant.Pages.LoginBeCognizant;


public class TestCases extends Base {

	BeCognizant beCts = new BeCognizant();
	LoginBeCognizant logCts = new LoginBeCognizant();
	
	@BeforeTest
	public void invokeBrowser() throws InterruptedException{
		logger = report.createTest("Executing All Test Cases");
		beCts.invokeBrowser();
		reportPass("Browser Invoked");
	}	
	
	@Test(priority=1)
	public void login() throws IOException, InterruptedException
	{		
			logCts.msLogin();
			reportPass("Microsoft LogIn Successfully");
	}
	
	@Test(priority=2)
	public void confirmBecognizant() throws IOException, InterruptedException
	{
		beCts.confirmBeCognizant();
		reportPass("Be.Cognizant Page Title verfied successfully");
	}
	
	@Test(priority=3)
	public void displayUserName() throws IOException, InterruptedException
	{
		beCts.UserName();
		reportPass("Username printed and Extracted successfully");
	}
	
	@Test(priority=4)
	public void scrollToFeatured() throws IOException, InterruptedException
	{
		beCts.scrollDown();
	}
	
	@Test(priority=5)
	public void featuredTitleCheck() throws IOException, InterruptedException
	{
		beCts.FeaturedNews();
		reportPass("Verified the Featured News Title successfully");
	}
	
	@Test(priority=6)
	public void countNews() throws IOException, InterruptedException
	{
		beCts.countNews();
		reportPass("The Count of featured news items collected.");
	}
	
	@Test(priority=7)
	public void newsContent() throws IOException, InterruptedException
	{
		beCts.featuredNewsContent();
		reportPass("Featured News contents collected and stored in Excel Sheet");
	}
	
	@AfterTest
	public void closeBrowser() {
		beCts.endReport();
		beCts.closeBrowser();
	}

}
