package com.APFD.pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.APFD.utils.WebDriverUtilities;


public class LandingPage {

    WebDriverUtilities utils = new WebDriverUtilities();
   
    WebDriverWait wait = new WebDriverWait(APFDFrameworkDriverManager.getDriver(), Duration.ofSeconds(50));

	@FindBy(xpath="//span[text()='Fire Management']")
	private WebElement ClickFireManagementButton;

	@FindBy (xpath="//a[text()='Add Fire Point']") 
	private WebElement ClickAddFirePointTile;
	
	@FindBy (xpath="//i[@class='fa fa-times']") 
	private WebElement Alert;

	public WebElement getAlert() {
		return Alert;
	}

	public WebElement getClickFireManagementButton() {
		return ClickFireManagementButton;
	}

	public WebElement getClickAddFirePointTile() {
		return ClickAddFirePointTile;
	}
	
	public LandingPage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);

	}
	
	 public void landing() throws InterruptedException {
	        try {
	            if (isAlertPresent()) {
	                wait.until(ExpectedConditions.elementToBeClickable(getAlert())).click();
	            }
	        } catch (NoSuchElementException e) {
	           
	        }

	        wait.until(ExpectedConditions.elementToBeClickable(getClickFireManagementButton())).click();
	        wait.until(ExpectedConditions.elementToBeClickable(getClickAddFirePointTile())).click();
	    }

	    private boolean isAlertPresent() {
	        try {
	            return getAlert().isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
}