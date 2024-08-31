package com.guru99.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;

public class BaseTests {
	
	CommonDriver cmnDriver;
	String url;
	String browserType;
	
	WebDriver driver;
	
	LoginPage loginpage;
	
	String configFileName;
	
//	String currentWorkingDirectory;
	
	Properties configProperty;
	
	ReportUtils reportUtils;
	String reportFilename;
	ScreenshotUtils screenshot;
	
	@BeforeSuite
	public void preSetup() throws Exception {
	//	currentWorkingDirectory = System.getProperty("user.dir");
		configFileName = "config\\config.properties";
		reportFilename = "reports\\TestReport.html";
		
		configProperty = ConfigUtils.readProperties(configFileName);
		
		reportUtils = new ReportUtils(reportFilename);
	}

	@BeforeClass
	public void setup() throws Exception {
		url= "https://mdz-institut.com/login";
	//	url = configProperty.getProperty("baseUrl");
	//	url = "http://demo.guru99.com/v4";
		
		 browserType = configProperty.getProperty("browserType");
		
		cmnDriver = new CommonDriver(browserType);
		driver = cmnDriver.getDriver();
		loginpage = new LoginPage(driver);
		
		screenshot = new ScreenshotUtils(driver);
		cmnDriver.navigateToUrl(url);
	}
	
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception {
		
		String testcasename = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFilename = "screenshots\\" + testcasename + executionTime + ".jpeg";
		
		if(result.getStatus() == ITestResult.FAILURE) {
			reportUtils.addTestsLog(Status.FAIL, "One or more steps failed");
			
			screenshot.capturedAndSaveScreenshot(screenshotFilename);
			
			reportUtils.attachScreenshotToReport(screenshotFilename);
		}
		
	}
	
		
	@AfterTest
	public void tearDownAfterTestClass() {

		System.out.println("Browser closed. WebDriver stopped.");
			cmnDriver.closeAllBrower();
	}
	
	@AfterSuite
	public void postTeardown() {
		reportUtils.flushReport();
	}
}
