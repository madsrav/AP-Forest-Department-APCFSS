package com.APFD.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.APFD.driver.APFDFrameworkDriverManager;

public class LoginPage {

	// Locate elements using multiple criteria to avoid stale element reference exception.
	
	@FindAll({@FindBy(xpath = "//a[text()=' Login']"),
		@FindBy(xpath="//a[@class='active nav-link']")
	})
	private WebElement ClickLoginLink;

	@FindAll({@FindBy (xpath="//div/input[@name='username']"),
		@FindBy(xpath="//input[@placeholder='USER ID']")})
	private WebElement EnterUsername;

	@FindAll({@FindBy (xpath="//div/input[@name='password']"),
		@FindBy(xpath="//input[@type='password']")}) 
	private WebElement EnterPassword;

	@FindAll({@FindBy (xpath="//input[@name='deptCaptcha']"),
		@FindBy(xpath="//input[@id='deptCaptcha']"), 
		@FindBy(xpath="//input[@placeholder='Captcha']")})
	private WebElement EnterCaptcha;

	@FindAll({@FindBy (xpath="//button[text()='Sign in']"),
		@FindBy(xpath="//button[@type='submit']"),
		@FindBy(xpath="//button[contains(@class,'btn btn-primary btn-md')]")})
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

	public void login(String username, String password) throws InterruptedException
	{
		getClickLoginLink().click();
		getEnterUsername().sendKeys(username);
        getEnterPassword().sendKeys(password);

		// Add a delay to manually solve the CAPTCHA
		Thread.sleep(10000);

		getClickSignInButton().click();
	}
}