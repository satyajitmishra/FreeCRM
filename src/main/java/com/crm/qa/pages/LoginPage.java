package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	
	//page factory-OR:
	
	@FindBy(name = "username")
	WebElement userName;
	
	@FindBy(name = "password")
	WebElement password;
	
	//@FindBy(xpath = "//input[@type='submit']")

	@FindBy(css="input[value='Login1']")
	WebElement loginButton;
	
	
	@FindBy(xpath="//font[@color='red' and text()='Sign Up']")
	WebElement signUp;
	
	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the Page Object
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle() {
		//log.info("Get the page tile");
		return driver.getTitle();
		
	}
	
	public boolean validateCrmImage() {
		return crmLogo.isDisplayed();
	}
	public HomePage login(String un,String pw) {
		userName.sendKeys(un);
		//log.info("Entered the UserName On UserName Filed"+un);
		password.sendKeys(pw);
		
		//loginButton.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", loginButton);
		return new HomePage();
	}
}
