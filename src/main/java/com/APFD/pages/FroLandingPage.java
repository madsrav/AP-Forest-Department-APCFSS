package com.APFD.pages;

import java.awt.JobAttributes.MultipleDocumentHandlingType;
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

public class FroLandingPage {

	public FroLandingPage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);

	}

	WebDriverUtilities utils = new WebDriverUtilities();

	WebDriverWait wait = new WebDriverWait(APFDFrameworkDriverManager.getDriver(), Duration.ofSeconds(60));

	@FindAll({@FindBy (xpath="//i[@class='fa fa-times']"),	
		@FindBy(xpath = "//i[contains(@style,'cursor: pointer;')]")	 
	})
	private WebElement AlertFRO;
	
	@FindBy(xpath="//button[text()=' Close ']")
	private WebElement FROMultipleSessionsAlert;

	@FindBy(xpath="//span[text()='Fire Management']")
	private WebElement ClickFireManagementButton;

	@FindAll({@FindBy (xpath="//a[text()='Fire Point Ground Truthing']"),
		@FindBy(xpath="//a[@href='/FirePointGroundTruthing']")
	})
	private WebElement ClickFirePointGroundTruthingTile;

	public WebElement getAlertFRO() {
		return AlertFRO;
	}

	public WebElement getFROMultipleSessionsAlert() {
		return FROMultipleSessionsAlert;
	}

	public WebElement getClickFireManagementButton() {
		return ClickFireManagementButton;
	}

	public WebElement getClickFirePointGroundTruthingTile() {
		return ClickFirePointGroundTruthingTile;
	}

	public void froLanding(){

		try {
			if (isAlertPresent()) {
				//wait.until(ExpectedConditions.elementToBeClickable(getAlertFRO())).click();
				getAlertFRO().click();
				
				if(!FROMultipleSessionsAlert.isDisplayed())
            	{
            		utils.scrollToElement(getFROMultipleSessionsAlert());
            		getFROMultipleSessionsAlert().click();
            	}
				//wait.until(ExpectedConditions.elementToBeClickable(FROMultipleSessionsAlert)).click();
			}
		} catch (NoSuchElementException e) {

		}
		wait.until(ExpectedConditions.elementToBeClickable(getClickFireManagementButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(getClickFirePointGroundTruthingTile())).click();
	}

	private boolean isAlertPresent() {
		try {
			return getAlertFRO().isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}