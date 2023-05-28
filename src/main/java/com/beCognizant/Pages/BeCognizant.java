package com.beCognizant.Pages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.beCognizant.Base.Base;


public class BeCognizant extends Base {
	
	By userName =By.id("userProfileToggleBtn");
	
	By featuredNewsTitle = By.xpath("//div[contains(text(),'Featured News')]");
	By featuredNewsCount = By.xpath("//div[@class='ufs-transparent-card']");
	By storyTitle = By.xpath("//h1[@class='story-page__title']");
	By storyContent = By.xpath("//div[@class='story-page__body']");
	By slideBtn = By.xpath("//*/section/div[1]/span/button[2]");

	public void confirmBeCognizant() throws IOException, InterruptedException {
		logger = report.createTest("Checking the Home Page Title");
		if (driver.getTitle().contains("Be.Cognizant")) {
			System.out.println("Page title is Be.Cognizant \nPage Title verified.\n");
		} else {
			System.out.println("Page title is not having Be.Cognizant \nPage Title not verified.\n");
		}
		Thread.sleep(2000);
		TakeScreenshot();
		System.out.println("Screenshot taken for Be.Cognizant Home page\n");
		reportPass("Be.Cognizant Home Page Title verfied");

	}

	public void UserName() throws IOException, InterruptedException {
		logger = report.createTest("Fetching User Name");
		Thread.sleep(2000);
		String username=driver.findElement(userName).getText();
		System.out.println("Username: " + username);
		reportPass("Username fetched successfully");
	}


	public void scrollDown() throws IOException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)", "");
		Thread.sleep(2000);
	}

	public void FeaturedNews() throws IOException, InterruptedException {
		logger = report.createTest("Verifying the Title of Featured News");
		Thread.sleep(2000);
		System.out.println("\n->Checking the Featured News title and its total count");
		String newsTitle = driver.findElement(featuredNewsTitle).getText();
		if (newsTitle.contains("FEATURED NEWS")) {
			System.out.println("BeCognizant contains FEATURED NEWS Title\n");
		} else {
			System.out.println("BeCognizant dosen't contains FEATURED NEWS Title\n");
		}
		TakeScreenshot();
		driver.findElement(slideBtn).click();
		Thread.sleep(1500);
		TakeScreenshot();
		driver.findElement(slideBtn).click();
		System.out.println("Screenshot taken for Featured News Title");
		reportPass("Verified the Title of Featured News");
	}

	public void countNews() throws IOException, InterruptedException {
		
		logger = report.createTest("Count of Featured News items");
		int result = driver.findElements(featuredNewsCount).size();
		System.out.println("The Count of featured news items displayed: " +result+"\n");
		reportPass("The Count of featured news items collected.");
	}	
	
	public void featuredNewsContent() throws IOException, InterruptedException {
		logger = report.createTest("Getting the Details of the Featured News");
		System.out.println("->Contents of the Featured News");
		List<WebElement> news = driver.findElements(featuredNewsCount);
		String[] data = new String[100];
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Featured News");
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("NewsHeadlines");
		sheet.getRow(0).createCell(1).setCellValue("Details");
		for(int i=1;i<=news.size();i++)
		{
			if(i<=3) 
			{
				driver.findElement(By.xpath("//section[1]/div[2]/div[1]/div[1]/div[1]/div["+i+"]/div[1]/a")).click();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				Thread.sleep(2000);
				try 
				{				
					String Title=driver.findElement(storyTitle).getText();
					sheet.createRow(i + 1);
					sheet.getRow(i + 1).createCell(0).setCellValue(Title);
					System.out.println(i+". "+Title+"\n");
					driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
					//Thread.sleep(6000);
					data[i] = driver.findElement(storyContent).getText();
					System.out.println(data[i]);
					sheet.getRow(i + 1).createCell(1).setCellValue(data[i]);
					Thread.sleep(2000);
					TakeScreenshot();
					System.out.println("Screenshot taken for "+"'"+Title+"'");
		
					System.out.println("\n----------------------------------------------------------------------");
					driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
					//Thread.sleep(4000);
					driver.navigate().back();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					//Thread.sleep(4000);
					reportPass("'"+Title+"'"+" - Contents of the news collected and is stored in Excel Sheet");
				} 
				catch (Exception e) 
				{				
					System.out.println(e.getMessage());				
				}
			}
			else 
			{			
				driver.findElement(slideBtn).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//section[1]/div[2]/div[1]/div[2]/div[1]/div["+(i-3)+"]/div[1]/a")).click();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				//Thread.sleep(6000);
				try 
				{				
					String Title=driver.findElement(storyTitle).getText();
					sheet.createRow(i + 1);
					sheet.getRow(i + 1).createCell(0).setCellValue(Title);
					System.out.println(i+". "+Title+"\n");
					//Thread.sleep(6000);
					driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
					data[i] = driver.findElement(storyContent).getText();
					System.out.println(data[i]);
					sheet.getRow(i + 1).createCell(1).setCellValue(data[i]);
					Thread.sleep(2000);
					TakeScreenshot();
					System.out.println("\nScreenshot taken for "+"'"+Title+"'");
	
					System.out.println("\n");
					//Thread.sleep(4000);
					driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
					driver.navigate().back();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					//Thread.sleep(4000);
					reportPass("'"+Title+"'"+" - Content stored in Excel Sheet");
				} 
				catch (Exception e) 
				{				
					System.out.println(e.getMessage());				
				}
			}
		
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		File file = new File(System.getProperty("user.dir")+"\\ExcelOutput\\Details.xlsx");
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();		
		reportPass("All Featured News contents are collected and stored in Excel Sheet");
	}

}
