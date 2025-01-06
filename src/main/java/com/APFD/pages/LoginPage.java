package com.APFD.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APFD.driver.APFDFrameworkDriverManager;

public class LoginPage {

	@FindBy(xpath="//a[@class='active nav-link']")
	private WebElement ClickLoginLink;

	@FindBy (xpath="//div/input[@name='username']") 
	private WebElement EnterUsername;

	@FindBy (xpath="//div/input[@name='password']") 
	private WebElement EnterPassword;

	@FindBy (xpath="//input[@name='deptCaptcha']") 
	private WebElement EnterCaptcha;

	@FindBy (xpath="//button[text()='Sign in']") 
	private WebElement ClickSignInButton;

	public LoginPage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);

	}

	public WebElement getClickLoginLink() {
		return ClickLoginLink;
	}

	public WebElement getEnterUsername() {
		return EnterUsername;
	}

	public WebElement getEnterPassword() {
		return EnterPassword;
	}

	public WebElement getEnterCaptcha() {
		return EnterCaptcha;
	}

	public WebElement getClickSignInButton() {
		return ClickSignInButton;
	}

	public void login(String Password, String Username) throws InterruptedException
	{
		getClickLoginLink().click();
		getEnterUsername().sendKeys(Username);
		getEnterPassword().sendKeys(Password);
		
		// Add a delay to manually solve the CAPTCHA
		Thread.sleep(10000); // Wait for 30 seconds to solve CAPTCHA manually
		
//		WebDriverWait wait = new WebDriverWait(APFDFrameworkDriverManager.getDriver(), Duration.ofSeconds(60));
//	    wait.until(ExpectedConditions.visibilityOf(EnterCaptcha));
//	    wait.until(ExpectedConditions.attributeToBeNotEmpty(EnterCaptcha, "value"));
		getClickSignInButton().click();
	}
}