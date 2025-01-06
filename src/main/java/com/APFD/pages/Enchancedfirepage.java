package com.APFD.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.APFD.utils.WebDriverUtilities;

public class Enchancedfirepage {

	public Enchancedfirepage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);
	}

	@FindBy(xpath = "//select[@name='year']")
	private WebElement SelectYear;

	@FindBy(xpath = "//input[@name='fireDataId']")
	private WebElement FireDataID;


	@FindBy(xpath = "//div[contains(text(), 'Fire Id Already Exists')]")
	private WebElement FireIDAlreadyExists;
	
	@FindBy(xpath = "//button[text()='Okay']")
	private WebElement FireIDAlreadyEXistsClickOK;

	@FindBy(xpath = "//select[@name='stype']")
	private WebElement Stype;

	@FindBy(xpath = "//option[text()='Rajahmundry']")
	private WebElement Circle;

	@FindBy(xpath = "//option[text()='DFO(T),Chintoor']")
	private WebElement Division;

//	@FindBy(xpath = "//select[@name='range']")
//	private WebElement Range;
	
	@FindBy(xpath = "//option[text()='Chintoor I/c']")
	private WebElement Range;

	@FindBy(xpath = "//select[@name='section']")
	private WebElement Section;

	@FindBy(xpath = "//select[@name='beat']")
	private WebElement Beat;

	@FindBy(xpath = "//select[@name='compartment']")
	private WebElement Compartment;

	@FindBy(xpath = "//select[@name='block']")
	private WebElement Block;

	@FindBy(xpath = "//input[@name='latitude']")
	private WebElement Latitude;

	@FindBy(xpath = "//input[@name='longitude']")
	private WebElement Longitude;

	@FindBy(xpath = "//input[@name='forestFireDate']")
	private WebElement ForestFireDateandTime;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement SubmitButton;

	@FindBy(xpath = "//div[@class='msg alert alert-info success']")
	private WebElement SuccessMessage;

	@FindBy(xpath = "//i[@class='fa fa-power-off']")
	private WebElement ClickPowerIcon;

	@FindBy(xpath = "//div[@id='sbmt_btn' and text()=' Logout']")
	private WebElement ClickLogoutButton;

	public WebElement getSelectYear() {
		return SelectYear;
	}

	public WebElement getFireDataID() {
		return FireDataID;
	}

	public WebElement getFireIDAlreadyExists() {
		return FireIDAlreadyExists;
	}

	public WebElement getStype() {
		return Stype;
	}

	public WebElement getCircle() {
		return Circle;
	}

	public WebElement getDivision() {
		return Division;
	}

	public WebElement getRange() {
		return Range;
	}

	public WebElement getSection() {
		return Section;
	}

	public WebElement getBeat() {
		return Beat;
	}

	public WebElement getCompartment() {
		return Compartment;
	}

	public WebElement getBlock() {
		return Block;
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

	public WebElement getSuccessMessage() {
		return SuccessMessage;
	}

	public WebElement getClickPowerIcon() {
		return ClickPowerIcon;
	}

	public WebElement getClickLogoutButton() {
		return ClickLogoutButton;
	}
	public static String fireDataID;

	public void firePageRandomValueSelect() throws InterruptedException {
		
		WebDriverUtilities utils = new WebDriverUtilities();
		WebDriver driver = APFDFrameworkDriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Random r = new Random();
		List<String> inputValues = new ArrayList<>();
		
		boolean isUnique = false;
		String timeanddate= new Date().toString().replace(":","_").replace(" ","_");

		// Generate a random date and time
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime randomDateTime = now.minusDays(r.nextInt(30)).withHour(r.nextInt(24)).withMinute(r.nextInt(60));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String formattedDateTime = randomDateTime.format(formatter);

		while (!isUnique) {
			// Generate a random integer between 100 and 999
			int lowerBound = 100;
			int upperBound = 1000; // upper bound is exclusive in nextInt
			int randomInt = r.nextInt(upperBound - lowerBound) + lowerBound;

			fireDataID = Integer.toString(randomInt);

			// Clear previous inputs if any
			getFireDataID().clear();
			getFireDataID().sendKeys(fireDataID);

			// Fill other fields
			inputValues.clear();
			// inputValues.add(DropdownUtils.selectRandomDropdownOption(getSelectYear()));
			inputValues.add(utils.selectRandomDropdownOption(SelectYear));
			inputValues.add(fireDataID);
			inputValues.add(utils.selectRandomDropdownOption(Stype));
			getCircle().click();
			inputValues.add(getCircle().getText());
			getDivision().click();
			inputValues.add(getDivision().getText());

			getRange().click();
			inputValues.add(getRange().getText());
			
			wait.until(ExpectedConditions.visibilityOf(Section));
			inputValues.add(utils.selectRandomDropdownOption(Section));

			wait.until(ExpectedConditions.elementToBeClickable(Beat));
			inputValues.add(utils.selectRandomDropdownOption(getBeat()));

			wait.until(ExpectedConditions.visibilityOf(Compartment));
			inputValues.add(utils.selectRandomDropdownOption(Compartment));

			wait.until(ExpectedConditions.elementToBeClickable(Block));
			inputValues.add(utils.selectRandomDropdownOption(Block));

			getLatitude().sendKeys(fireDataID);
			inputValues.add(fireDataID);

			getLongitude().sendKeys(fireDataID);
			inputValues.add(fireDataID);

			// Use sendKeys to set the date and time
			getForestFireDateandTime().sendKeys(formattedDateTime);
			inputValues.add(formattedDateTime);

			// Ensure the directory exists
			String directoryPath = System.getProperty("user.dir") + "/FirePointTestData";
			File directory = new File(directoryPath);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			// Write the input values to a file
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "/" + fireDataID+" "+timeanddate+" " +"TestData.txt"))) {
				for (String value : inputValues) {
					writer.write(value);
					writer.newLine();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			// Submit the form
			getSubmitButton().click();

			// Check for duplicate Fire Data ID message
			try {
				WebElement duplicateMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Fire Id Already Exists')]")));
				if (duplicateMessage.isDisplayed()) {
					getFireIDAlreadyExists().click();
					System.out.println("Duplicate Fire Data ID detected: " + fireDataID);
					// Clear the Fire Data ID field for the next attempt
					getFireDataID().clear();
					getFireDataID().sendKeys(fireDataID);
				}
			} catch (Exception e) {
				// No duplicate message found, assume ID is unique
				isUnique = true;
			}
		}

		wait.until(ExpectedConditions.elementToBeClickable(getClickPowerIcon())).click();
		wait.until(ExpectedConditions.elementToBeClickable(getClickLogoutButton())).click();
	}
}