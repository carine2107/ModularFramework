package com.guru99.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class LoginTests extends BaseTests{
	
	@Parameters({"username", "userPassword"})
	@Test
	public void verifyUserLoginWithCorrectCredentials(String username, String password) {
		
		reportUtils.createAtestCase("Verify User Login With Correct Credentials");
		
		reportUtils.addTestsLog(Status.INFO, "Perfoming Login");
		loginpage.loginToApplication(username, password);
		
		String excepedTitle = "MDZ-institut - Cours intensifs d'allemand";
		String actualTitle = cmnDriver.getTitleOfThePage();
		
		reportUtils.addTestsLog(Status.INFO, "Comparing expected and actual title");
		
		Assert.assertEquals(actualTitle, excepedTitle);
	}
	
/*	@Parameters({"username", "userPassword"})
	@Test
	public void verifyUserLoginWithCorrectUsernameAndWrongPassword(String username, String password) {
		
		reportUtils.createAtestCase("Verify User Login With Correct Username And Wrong Password");
		
		reportUtils.addTestsLog(Status.INFO, "Perfoming Login");
		loginpage.loginToApplication(username, password);
		
		String excepedTitle = "MDZ-institut - Cours intensifs d'allemand";
		String actualTitle = cmnDriver.getTitleOfThePage();
		
		reportUtils.addTestsLog(Status.INFO, "Comparing expected and actual title");
		
		Assert.assertEquals(actualTitle, excepedTitle);
	} */

}
