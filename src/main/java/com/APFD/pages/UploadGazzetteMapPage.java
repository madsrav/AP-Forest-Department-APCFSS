package com.APFD.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.APFD.utils.WebDriverUtilities;

public class UploadGazzetteMapPage {

	WebDriverWait wait=new WebDriverWait(APFDFrameworkDriverManager.getDriver(), Duration.ofSeconds(40));
	WebDriverUtilities utils= new WebDriverUtilities();

	public UploadGazzetteMapPage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);
	}

	@FindBy(xpath="//span[text()='Upload Gazette Map']")
	private WebElement uploadGazzetteTile;

	@FindAll({@FindBy(xpath="//a[text()='Upload Data']"),
		@FindBy(xpath="//a[@href='/UploadData']")
	})
	private WebElement uploadDataTile;

	@FindBy(xpath = "//option[text()='Chintapalli']")
	private WebElement Range;

	@FindBy(xpath = "//select[@name='section']")
	private WebElement Section;

	@FindBy(xpath = "//select[@name='block']")
	private WebElement Block;

	@FindAll({@FindBy(xpath="//input[@name='blockOthers']"),
		@FindBy(xpath="//input[@maxlength='50']")
	})
	private WebElement othersRemarks;

	@FindBy(xpath="//input[@name='uploadScannedGezetteMap']")
	private WebElement scannedGazetteMap;

	@FindBy(xpath="//input[@name='uploadScannedGezetteNotification']")
	private WebElement scannedGazetteNotification;

	@FindAll({@FindBy(xpath="//input[@name='area']"),
		@FindBy(xpath="//input[@maxlength='7']")
	})
	private WebElement area;

	@FindAll({@FindBy(xpath="//button[@type='submit']"),
		@FindBy(xpath="//button[contains(@class,'btn btn-success ')]")
	})
	private WebElement uploadMapsButton;

	@FindAll({@FindBy(xpath="//button[@type='button']"),
		@FindBy(xpath="//button[@aria-label='close']")
	})
	private WebElement toastMessages;

	@FindBy(xpath = "//*[local-name()='svg' and @aria-hidden='true']")
	private List<WebElement> svgTags;

	@FindBy(xpath = "//i[@class='fa fa-power-off']")
	private WebElement ClickPowerIcon;

	@FindBy(xpath = "//div[@id='sbmt_btn' and text()=' Logout']")
	private WebElement ClickLogoutButton;

	public WebElement getUploadGazzetteTile() {
		return uploadGazzetteTile;
	}

	public WebElement getUploadDataTile() {
		return uploadDataTile;
	}

	public WebElement getRange() {
		return Range;
	}

	public WebElement getSection() {
		return Section;
	}

	public WebElement getBlock() {
		return Block;
	}

	public WebElement getOthersRemarks() {
		return othersRemarks;
	}

	public WebElement getScannedGazetteMap() {
		return scannedGazetteMap;
	}

	public WebElement getScannedGazetteNotification() {
		return scannedGazetteNotification;
	}

	public WebElement getArea() {
		return area;
	}

	public WebElement getUploadMapsButton() {
		return uploadMapsButton;
	}


	public WebElement getToastMessages() {
		return toastMessages;
	}

	public List<WebElement> getSvgTags() {
		return svgTags;
	}

	public WebElement getClickPowerIcon() {
		return ClickPowerIcon;
	}

	public WebElement getClickLogoutButton() {
		return ClickLogoutButton;
	}

	public static String Area;

	public void uploadGazetteData() throws InterruptedException {
		String timeanddate = new Date().toString().replace(":", "_").replace(" ", "_");
		Random r = new Random();
		int lowerBound = 1000;
		int upperBound = 10000; // upper bound is exclusive in nextInt
		int randomInt = r.nextInt(upperBound - lowerBound) + lowerBound;

		List<String> inputValues = new ArrayList<>();

		Area = Integer.toString(randomInt);

		getUploadGazzetteTile().click();
		getUploadDataTile().click();
		getRange().click();
		inputValues.add(getRange().getText());

		wait.until(ExpectedConditions.visibilityOf(Section));
		inputValues.add(utils.selectRandomDropdownOption(Section));

		wait.until(ExpectedConditions.elementToBeClickable(Block));
		inputValues.add(utils.selectRandomDropdownOption(Block));

		// Check if "Others" is selected
		if (getBlock().getText().equalsIgnoreCase("Others")) {
			// Locate the others field and interacting with it
			getOthersRemarks().sendKeys(Area);
		}

		getScannedGazetteMap().sendKeys(System.getProperty("user.dir") + "/src/test/resources/config/Bank Passbook Copy (3).pdf");
		getScannedGazetteNotification().sendKeys(System.getProperty("user.dir") + "/src/test/resources/config/Bank Passbook Copy (3).pdf");
		getArea().sendKeys(Area);
		inputValues.add(Area);

		// Ensure the directory exists
		String directoryPath = System.getProperty("user.dir") + "/UploadGazetteTestData";
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		// Write the input values to a file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "/" +Enchancedfirepage.fireDataID +" "+ timeanddate + " " + "UploadForestRangeTestData.txt"))) {
			for (String value : inputValues) {
				writer.write(value);
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		getUploadMapsButton().click();

		// Perform actions on the SVG elements
		Actions actions = new Actions(APFDFrameworkDriverManager.getDriver());

		// Loop to handle multiple SVG elements
		while (true) {
			try {
				// Wait for an SVG element to appear
				wait.until(ExpectedConditions.visibilityOfAllElements(svgTags));

				// Perform the necessary action to close the SVG element
				for (WebElement svgTag : getSvgTags()) {
					actions.moveToElement(svgTag).click().perform(); // Example action, adjust as needed
					wait.until(ExpectedConditions.invisibilityOf(svgTag));
				}

				// Wait for toast messages to disappear
				wait.until(ExpectedConditions.invisibilityOf(toastMessages));
			} catch (Exception e) {
				// Break the loop if no more SVG elements are found
				break;
			}
		}

		wait.until(ExpectedConditions.visibilityOf(ClickPowerIcon));
		wait.until(ExpectedConditions.elementToBeClickable(getClickPowerIcon())).click();
		wait.until(ExpectedConditions.visibilityOf(ClickLogoutButton));
		wait.until(ExpectedConditions.elementToBeClickable(getClickLogoutButton())).click();
	}
}