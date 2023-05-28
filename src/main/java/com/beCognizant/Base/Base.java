package com.beCognizant.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.beCognizant.Utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	String userDir = System.getProperty("user.dir");
	
	public void invokeBrowser() throws InterruptedException {
		prop = new Properties();

		try {
			prop.load(new FileInputStream(userDir+
				"\\src\\main\\java\\com\\beCognizant\\Config\\config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (prop.getProperty("browserName").matches("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); // Initializing the new Chrome driver
		}

		if (prop.getProperty("browserName").matches("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		if (prop.getProperty("browserName").matches("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	public void navigateToURL(String URL) {
		driver.get(prop.getProperty(URL));
	}

	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
	}

	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}
	
	public void reportInfo(String report) {
		logger.log(Status.INFO, report);
	}
	
	public void wait(int sec, By locator) {
		wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void endReport() {
		report.flush();
	}

	public void closeBrowser() {
		driver.quit();
	}
	
	public void TakeScreenshot() {
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,new File(userDir+"//Screenshots//Screenshot_"+System.currentTimeMillis()+".png"));
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
		
}
