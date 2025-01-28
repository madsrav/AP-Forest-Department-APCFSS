package com.APFD.pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.APFD.utils.WebDriverUtilities;


public class LandingPage {

    WebDriverUtilities utils = new WebDriverUtilities();
   
    WebDriverWait wait = new WebDriverWait(APFDFrameworkDriverManager.getDriver(), Duration.ofSeconds(60));
    
 // Locate elements using multiple criteria to avoid stale element reference exception.
	
 	@FindAll({@FindBy (xpath="//i[@class='fa fa-times']"),	
 		@FindBy(xpath = "//i[contains(@style,'cursor: pointer;')]")	 
 	})  
	private WebElement Alert;
 	
 	@FindBy(xpath="//button[text()=' Close ']")
 	private WebElement MultipleSessionsAlert;
	
	@FindBy(xpath="//span[text()='Fire Management']")
	private WebElement ClickFireManagementButton;

	@FindAll({@FindBy (xpath="//a[text()='Add Fire Point']"),
		@FindBy(xpath="//a[@href='/AddFirePoint']")
	})
	private WebElement ClickAddFirePointTile;
	

	public WebElement getAlert() {
		return Alert;
	}

	public WebElement getMultipleSessionsAlert() {
		return MultipleSessionsAlert;
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
	                //wait.until(ExpectedConditions.elementToBeClickable(getAlert())).click();
	            	getAlert().click();
	            	if(!MultipleSessionsAlert.isDisplayed())
	            	{
	            		utils.scrollToElement(MultipleSessionsAlert);
	            		getMultipleSessionsAlert().click();
	            	}
					//wait.until(ExpectedConditions.elementToBeClickable(MultipleSessionsAlert)).click();
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