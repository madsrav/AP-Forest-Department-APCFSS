package com.APFD.pages;


import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.APFD.driver.APFDFrameworkDriverManager;

public class AddFirePointPage {


	@FindBy(xpath="//option[@value='2024']")
	private WebElement SelectYear2024;

	@FindBy (xpath="//input[@name='fireDataId']") 
	private WebElement FireDataID;

	@FindBy (xpath="//option[text()='MODIS']") 
	private WebElement StypeModis;

	@FindBy (xpath="//option[text()='Kurnool']") 
	private WebElement Circlekurnool;

	@FindBy (xpath="//option[text()='DFO(T), Kurnool']") 
	private WebElement DivisionDFOKurnool;

	@FindBy (xpath="//option[text()='Adoni ']") 
	private WebElement RangeAdoni;

	@FindBy (xpath="//option[@value='4' and text()='Adoni']") 
	private WebElement SectionAdoni;

	@FindBy (xpath="//option[text()='Kotekal']") 
	private WebElement BeatKotekal;

	@FindBy (xpath="//option[text()='42']") 
	private WebElement Compartment42;

	@FindBy (xpath="//option[text()='KARUMANCHI-RF']") 
	private WebElement BlockKARUMANCHIF;

	@FindBy (xpath="//input[@name='latitude']") 
	private WebElement Latitude;

	@FindBy (xpath="//input[@name='longitude']") 
	private WebElement Longitude;

	@FindBy (xpath="//input[@name='forestFireDate']") 
	private WebElement ForestFireDateandTime;

	@FindBy (xpath="//button[@type='submit']") 
	private WebElement SubmitButton;

	@FindBy (xpath="//i[@class='fa fa-power-off']") 
	private WebElement ClickPowerIcon;

	@FindBy (xpath="//div[@id='sbmt_btn' and text()=' Logout']") 
	private WebElement ClickLogoutButton;

	public AddFirePointPage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);

	}
	public WebElement getSelectYear2024() {
		return SelectYear2024;
	}

	public WebElement getFireDataID() {
		return FireDataID;
	}

	public WebElement getStypeModis() {
		return StypeModis;
	}

	public WebElement getCirclekurnool() {
		return Circlekurnool;
	}

	public WebElement getDivisionDFOKurnool() {
		return DivisionDFOKurnool;
	}

	public WebElement getRangeAdoni() {
		return RangeAdoni;
	}

	public WebElement getSectionAdoni() {
		return SectionAdoni;
	}

	public WebElement getBeatKotekal() {
		return BeatKotekal;
	}

	public WebElement getCompartment42() {
		return Compartment42;
	}

	public WebElement getBlockKARUMANCHIF() {
		return BlockKARUMANCHIF;
	}

	public WebElement getLatitude() {
		return Latitude;
	}

	public WebElement getLongitude() {
		return Longitude;
	}

	public WebElement getForestFireDateandTime() {
		return ForestFireDateandTime;
	}

	public WebElement getSubmitButton() {
		return SubmitButton;
	}

	public WebElement getClickPowerIcon() {
		return ClickPowerIcon;
	}

	public WebElement getClickLogoutButton() {
		return ClickLogoutButton;
	}

	public void addNewFirePoint() throws InterruptedException
	{
		Random r = new Random();
		// Generate a random integer between 100 and 999
		int randomInt = r.nextInt(900) + 100; // 900 is the range (999 - 100 + 1)
		String FireDataID = Integer.toString(randomInt);

		getSelectYear2024().click();
		getFireDataID().sendKeys(FireDataID);
		System.out.println(FireDataID);
		//String List = getFireDataID().getText();
		getStypeModis().click();
		getCirclekurnool().click();
		getDivisionDFOKurnool().click();
		getRangeAdoni().click();
		getSectionAdoni().click();
		getBeatKotekal().click();
		getCompartment42().click();
		getBlockKARUMANCHIF().click();
		getLatitude().sendKeys(FireDataID);
		getLongitude().sendKeys(FireDataID);
		getForestFireDateandTime().sendKeys("04-12-2024 16:54");
		getSubmitButton().click();
		getClickPowerIcon().click();
		getClickLogoutButton().click();
	}
}