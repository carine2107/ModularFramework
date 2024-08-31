package com.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	
	@CacheLookup
	@FindBy(xpath="/html/body/app-root/app-login/div[2]/div/div/div[1]/div/form/div[1]/input")
	private WebElement userId;
	
	@CacheLookup
	@FindBy(xpath="/html/body/app-root/app-login/div[2]/div/div/div[1]/div/form/div[2]/input")
	private WebElement userPassword;
	
	@CacheLookup
	@FindBy(xpath="/html/body/app-root/app-login/div[2]/div/div/div[1]/div/form/button")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String username, String password)
	{
		elementControl.setText(userId, username);
		elementControl.setText(userPassword, password);
		elementControl.clickElement(loginButton);
	}

}
