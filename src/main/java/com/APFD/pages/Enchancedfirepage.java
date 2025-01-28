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
import org.openqa.selenium.support.FindAll;
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

	// Locate elements using multiple criteria to avoid stale element reference exception.

	@FindBy(xpath = "//select[@name='year']")
	private WebElement SelectYear;

	@FindAll({@FindBy(xpath = "//input[@name='fireDataId']"),
		@FindBy(xpath="//input[@maxlength='6']")
	})
	private WebElement FireDataID;

	@FindAll({@FindBy(xpath = "//div[contains(text(), 'Fire Id Already Exists')]"),
		@FindBy(xpath="//div[@class='swal2-html-container']")
	})
	private WebElement FireIDAlreadyExists;

	@FindAll({@FindBy(xpath = "//button[text()='Okay']"), 
		@FindBy(xpath="//button[@class='swal2-confirm swal2-styled']"),
		@FindBy(xpath="//div[@class='swal2-actions']")
	})
	private WebElement FireIDAlreadyEXistsClickOK;

	@FindBy(xpath = "//select[@name='stype']")
	private WebElement Stype;

	@FindBy(xpath = "//option[text()='Visakhapatnam']")
	private WebElement Circle;

	@FindBy(xpath = "//option[text()='DFO(T),Chintapalli']")
	private WebElement Division;

	@FindBy(xpath = "//option[text()='Chintapalli']")
	private WebElement Range;

	@FindBy(xpath = "//option[@value='61' and text()='Chintapalli']")
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

	@FindAll({@FindBy(xpath = "//input[@name='forestFireDate']"),
		@FindBy(xpath="//input[@type='datetime-local']")
	})
	private WebElement ForestFireDateandTime;

	@FindAll({@FindBy(xpath = "//button[@type='submit']"),
		@FindBy(xpath="//button[contains(@class,'btn btn-success float-')]")
	})
	private WebElement SubmitButton;

	@FindAll({@FindBy(xpath="//button[contains(@class,'Toastify__close-button Toastify')]"),
	@FindBy(xpath = "//*[local-name()='svg' and @aria-hidden='true']")
	})
	private WebElement SuccessMessage;

	@FindBy(xpath = "//i[@class='fa fa-power-off']")
	private WebElement ClickPowerIcon;

	@FindAll({@FindBy(xpath = "//div[@id='sbmt_btn' and text()=' Logout']"),
		@FindBy(xpath="//div[@class='btn btn-danger pull-right']")
	})
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

	public WebElement getFireIDAlreadyEXistsClickOK() {
		return FireIDAlreadyEXistsClickOK;
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
		//Used 60 seconds to wait until the Toast message(successful message) got vanished.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Random r = new Random();
		List<String> inputValues = new ArrayList<>();


		String timeanddate= new Date().toString().replace(":","_").replace(" ","_");

		// Generate a random date and time
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime randomDateTime = now.minusDays(r.nextInt(30)).withHour(r.nextInt(24)).withMinute(r.nextInt(60));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String formattedDateTime = randomDateTime.format(formatter);

		boolean isUnique = false;
		while (!isUnique) {
			// Generate a random integer between 100 and 999
			int lowerBound = 100;
			int upperBound = 1000; // upper bound is exclusive in nextInt
			int randomInt = r.nextInt(upperBound - lowerBound) + lowerBound;

			fireDataID = Integer.toString(randomInt);

			//From here entering data for creating new fire ID.
			inputValues.add(utils.selectRandomDropdownOption(SelectYear));
			getFireDataID().sendKeys(fireDataID);

			// Check for duplicate Fire Data ID message
			try {
				//WebElement duplicateMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Fire Id Already Exists')]")));
				WebElement duplicateMessage= driver.findElement(By.xpath("//div[contains(text(), 'Fire Id Already Exists')]"));
				if (duplicateMessage.isDisplayed()) {
					getFireIDAlreadyEXistsClickOK().click();
					System.out.println("Duplicate Fire Data ID detected: " + fireDataID);

					// Clear the previous input for Fire Data ID and adding new ID.
					getFireDataID().clear();
					getFireDataID().sendKeys(fireDataID);
				}
			} catch (Exception e) {
				// No duplicate message found, assume ID is unique
				isUnique = true;
			}
		}

		// After verifying FireID Duplication then saving the data into respective text file.
		inputValues.add(fireDataID);
		inputValues.add(utils.selectRandomDropdownOption(Stype));
		getCircle().click();
		inputValues.add(getCircle().getText());
		getDivision().click();
		inputValues.add(getDivision().getText());

		getRange().click();
		inputValues.add(getRange().getText());
		
		getSection().click();
		inputValues.add(getSection().getText());

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

		wait.until(ExpectedConditions.elementToBeClickable(getSuccessMessage())).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(getClickPowerIcon())).click();
		wait.until(ExpectedConditions.elementToBeClickable(getClickLogoutButton())).click();
	}
}